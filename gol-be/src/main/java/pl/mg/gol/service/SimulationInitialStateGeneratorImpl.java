package pl.mg.gol.service;

import org.springframework.stereotype.Service;
import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;

import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class SimulationInitialStateGeneratorImpl implements SimulationInitialStateGenerator {


    @Override
    public Map<Position, Cell> generateInitialMap(long squareSize, long elements) {
        if (elements > squareSize * squareSize) {
            throw new InvalidParameterException("more elements than square size");
        }
        Map<Position, Cell> board = new HashMap<>();
/*        SecureRandom random = new SecureRandom();
        for (int i = 0; i < elements; i++) {
            long x = random.nextLong(squareSize);
            long y = random.nextLong(squareSize);
            board.put(new Position(x, y), new Cell(true, false, new Position(x, y)));
        }*/


        board.put(new Position(1, 1), new Cell(true, false, new Position(1, 1)));
        board.put(new Position(1, 2), new Cell(true, false, new Position(1, 2)));
        board.put(new Position(2, 1), new Cell(true, false, new Position(2, 1)));

        return board;
    }
}
