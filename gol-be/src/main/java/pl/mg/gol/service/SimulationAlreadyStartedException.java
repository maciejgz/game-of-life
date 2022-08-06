package pl.mg.gol.service;

public class SimulationAlreadyStartedException extends Throwable {
    public SimulationAlreadyStartedException(String message) {
        super(message);
    }
}
