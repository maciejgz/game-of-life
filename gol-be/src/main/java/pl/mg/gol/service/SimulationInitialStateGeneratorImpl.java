package pl.mg.gol.service;

import org.springframework.stereotype.Service;
import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;

import java.util.Map;

@Service
public class SimulationInitialStateGeneratorImpl implements SimulationInitialStateGenerator {


    @Override
    public Map<Position, Cell> generateInitialMap(long squareSize, long elements) {


        return null;
    }
}
