package pl.mg.gol.service;

import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;

import java.util.Map;

public interface SimulationInitialStateGenerator {
    Map<Position, Cell> generateInitialMap(long squareSize, long elements);
}
