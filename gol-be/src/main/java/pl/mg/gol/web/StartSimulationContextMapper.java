package pl.mg.gol.web;

import org.mapstruct.Mapper;
import pl.mg.gol.domain.SimulationContext;

@Mapper(componentModel = "spring")
public interface StartSimulationContextMapper {

    SimulationContext mapToSimulationContext(StartSimulationRequest request);

}
