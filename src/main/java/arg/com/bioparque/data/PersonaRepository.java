package arg.com.bioparque.data;

import arg.com.bioparque.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Persona findByUserName(String userName);
}
