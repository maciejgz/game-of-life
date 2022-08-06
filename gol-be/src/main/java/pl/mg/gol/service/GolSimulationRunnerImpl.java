package pl.mg.gol.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;
import pl.mg.gol.domain.SimulationContext;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
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

        while (simulationStarted) {
            for (Position position : board.keySet()) {

            }
        }

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
