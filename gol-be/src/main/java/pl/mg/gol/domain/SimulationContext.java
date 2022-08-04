package pl.mg.gol.domain;

import lombok.Value;

import java.util.Map;

@Value
public class SimulationContext {

    Map<Position, Cell> initialCellMap;

}
