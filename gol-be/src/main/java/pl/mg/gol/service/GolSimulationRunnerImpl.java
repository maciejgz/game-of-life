package pl.mg.gol.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;
import pl.mg.gol.domain.SimulationContext;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class GolSimulationRunnerImpl implements GolSimulationRunner {

    private final SimulationInitialStateGenerator generator;
    private final Validator validator;

    private volatile Boolean simulationStarted = false;

    public GolSimulationRunnerImpl(SimulationInitialStateGenerator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }

    @Override
    public void startSimulation(SimulationContext context) throws SimulationAlreadyStartedException {
        validateSimulationContext(context);
        checkIfSimulationIsAlreadyStarted();
        Map<Position, Cell> board = generator.generateInitialMap(context.getSquareSize(), context.getElements());
        int simulationTurn = 1;
        while (Boolean.TRUE.equals(simulationStarted)) {
            log.debug("turn {} start living cells {}", simulationTurn, board.size());
            for (Position position : board.keySet()) {
                /**
                 * Each cell with one or no neighbors dies, as if by solitude.
                 *
                 * Each cell with four or more neighbors dies, as if by overpopulation.
                 *
                 * Each cell with two or three neighbors survives.
                 * * */
                //verify living cells
                List<Cell> neighbours = findNeighbours(position, board);
                board.get(position).calculateNextState(neighbours);
                //verify their neighbours
                for (Cell neighbour : neighbours) {
                    List<Cell> secondLevelNeighbours = findNeighbours(neighbour.getPosition(), board);
                    board.getOrDefault(neighbour.getPosition(), neighbour).calculateNextState(secondLevelNeighbours);
                }
            }
            //propagate statuses to the living ones and remove dead cells
            for (Cell cell : board.values()) {
                cell.nextTurn();
            }
            //clear board
            board.entrySet().removeIf(positionCellEntry -> !positionCellEntry.getValue().isStatus());
            log.debug("simulation turn finished {} living cells", simulationTurn);
            simulationTurn++;
            if (board.isEmpty()) {
                log.debug("all cells are dead. stop simulation");
                simulationStarted = false;
                break;
            }
        }

    }

    private List<Cell> findNeighbours(Position position, Map<Position, Cell> board) {
        List<Cell> neighbours = new ArrayList<>();
        neighbours.add(board.getOrDefault(new Position(position.getX() - 1, position.getY() + 1), new Cell(false, false, new Position(position.getX() - 1, position.getY() + 1))));
        neighbours.add(board.getOrDefault(new Position(position.getX(), position.getY() + 1), new Cell(false, false, new Position(position.getX(), position.getY() + 1))));
        neighbours.add(board.getOrDefault(new Position(position.getX() + 1, position.getY() + 1), new Cell(false, false, new Position(position.getX() + 1, position.getY() + 1))));
        neighbours.add(board.getOrDefault(new Position(position.getX() - 1, position.getY()), new Cell(false, false, new Position(position.getX() - 1, position.getY()))));
        neighbours.add(board.getOrDefault(new Position(position.getX() + 1, position.getY()), new Cell(false, false, new Position(position.getX() + 1, position.getY()))));
        neighbours.add(board.getOrDefault(new Position(position.getX() - 1, position.getY() - 1), new Cell(false, false, new Position(position.getX() - 1, position.getY() - 1))));
        neighbours.add(board.getOrDefault(new Position(position.getX(), position.getY() - 1), new Cell(false, false, new Position(position.getX(), position.getY() - 1))));
        neighbours.add(board.getOrDefault(new Position(position.getX() + 1, position.getY() - 1), new Cell(false, false, new Position(position.getX() + 1, position.getY() - 1))));
        return neighbours;
    }

    @Override
    public void stopSimulation() throws SimulationAlreadyStoppedException {
        if (Boolean.FALSE.equals(simulationStarted)) {
            throw new SimulationAlreadyStoppedException("Simulation is already stopped");
        } else {
            simulationStarted = false;
        }
    }

    private void checkIfSimulationIsAlreadyStarted() throws SimulationAlreadyStartedException {
        if (Boolean.TRUE.equals(simulationStarted)) {
            throw new SimulationAlreadyStartedException("Simulation is already started");
        } else {
            simulationStarted = true;
        }
    }

    private void validateSimulationContext(SimulationContext context) {
        Set<ConstraintViolation<SimulationContext>> violations = validator.validate(context);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<SimulationContext> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }


}
