package pl.mg.gol.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mg.gol.service.GolSimulationRunner;
import pl.mg.gol.service.SimulationAlreadyStartedException;
import pl.mg.gol.service.SimulationAlreadyStoppedException;
import pl.mg.gol.socket.PingMessage;
import pl.mg.gol.socket.WebSocketService;

import javax.validation.Valid;

@RestController
public class GolSimulationController {
    private final GolSimulationRunner runner;
    private final StartSimulationContextMapper mapper;
    private final WebSocketService webSocketService;

    public GolSimulationController(GolSimulationRunner runner, StartSimulationContextMapper mapper, WebSocketService webSocketService) {
        this.runner = runner;
        this.mapper = mapper;
        this.webSocketService = webSocketService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> startSimulation(@Valid @RequestBody StartSimulationRequest dto) throws SimulationAlreadyStartedException {
        this.runner.startSimulation(mapper.mapToSimulationContext(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Void> startSimulation() throws SimulationAlreadyStoppedException {
        this.runner.stopSimulation();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "")
    public ResponseEntity<Void> sendPing() {
        this.webSocketService.sendPingMessage(new PingMessage());
        return ResponseEntity.ok().build();
    }

}
