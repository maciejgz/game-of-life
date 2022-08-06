package pl.mg.gol.service;

import pl.mg.gol.domain.SimulationContext;

public interface GolSimulationRunner {
    void startSimulation(SimulationContext context) throws SimulationAlreadyStartedException;

    void stopSimulation() throws SimulationAlreadyStoppedException;
}
