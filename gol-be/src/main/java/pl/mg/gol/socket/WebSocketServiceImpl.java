package pl.mg.gol.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    @Override
    @SendTo(value = "/topic/ping")
    public PingMessage sendPingMessage(PingMessage pingMessage) {
        log.debug("sending ping message: {}", pingMessage);
        return pingMessage;
    }
}
