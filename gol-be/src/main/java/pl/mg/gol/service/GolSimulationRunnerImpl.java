package pl.mg.gol.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.gol.domain.Cell;
import pl.mg.gol.domain.Position;
import pl.mg.gol.domain.SimulationContext;

import java.util.Map;

@Service
@Slf4j
public class GolSimulationRunnerImpl implements GolSimulationRunner {

    private final SimulationInitialStateGenerator generator;

    public GolSimulationRunnerImpl(SimulationInitialStateGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void startSimulation(SimulationContext context) {
        System.out.println(context);
        Map<Position, Cell> initialState = generator.generateInitialMap(context.getSquareSize(), context.getElements());


    }


}
