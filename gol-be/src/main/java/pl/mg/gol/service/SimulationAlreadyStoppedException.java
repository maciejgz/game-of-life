package pl.mg.gol.service;

public class SimulationAlreadyStoppedException extends Throwable {
    public SimulationAlreadyStoppedException(String message) {
        super(message);
    }
}
