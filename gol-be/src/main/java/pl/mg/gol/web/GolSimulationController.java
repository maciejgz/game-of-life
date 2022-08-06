package pl.mg.gol.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mg.gol.domain.SimulationContext;
import pl.mg.gol.service.GolSimulationRunner;
import pl.mg.gol.service.SimulationAlreadyStartedException;

import javax.validation.Valid;

@RestController
public class GolSimulationController {
    private final GolSimulationRunner runner;
    private final StartSimulationContextMapper mapper;

    public GolSimulationController(GolSimulationRunner runner, StartSimulationContextMapper mapper) {
        this.runner = runner;
        this.mapper = mapper;
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> startSimulation(@Valid @RequestBody StartSimulationRequest dto) throws SimulationAlreadyStartedException {
        this.runner.startSimulation(mapper.mapToSimulationContext(dto));
        return ResponseEntity.ok().build();
    }

}
