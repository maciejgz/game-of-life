package pl.mg.gol.domain;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class SimulationContext {

    @NotNull(message = "square size cannot be null")
    @Min(value = 1, message = "squareSize has to be greater than 0")
    long squareSize;

    @Min(value = 1, message = "elements has to be greater than 0")
    long elements;

}
