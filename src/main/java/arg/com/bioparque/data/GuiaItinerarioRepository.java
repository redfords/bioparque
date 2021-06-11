package arg.com.bioparque.data;

import arg.com.bioparque.domain.GuiaItinerario;
import arg.com.bioparque.domain.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuiaItinerarioRepository extends JpaRepository<GuiaItinerario, Long>{
    List<GuiaItinerario> findByPersona(Persona persona);
}
