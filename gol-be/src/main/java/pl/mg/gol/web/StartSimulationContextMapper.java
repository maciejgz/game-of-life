package pl.mg.gol.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.mg.gol.domain.SimulationContext;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface StartSimulationContextMapper {

    SimulationContext mapToSimulationContext(StartSimulationRequest request);

}
