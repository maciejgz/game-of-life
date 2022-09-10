package pl.mg.gol.socket;

import lombok.Value;

import java.time.Instant;

@Value
public class PingMessage {

    Instant date = Instant.now();

}
