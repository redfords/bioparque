package arg.com.bioparque.data;

import arg.com.bioparque.domain.CuidadorEspecie;
import arg.com.bioparque.domain.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorEspecieRepository extends JpaRepository<CuidadorEspecie, Long>{
    List <CuidadorEspecie> findByPersona(Persona persona);
}
