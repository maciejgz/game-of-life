package pl.mg.gol.web;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StartSimulationRequest {

    @NotNull(message = "square size cannot be null")
    @Min(value = 1, message = "squareSize has to be greater than 0")
    private long squareSize;

    @Min(value = 1, message = "elements has to be greater than 0")
    private long elements;
}
