package arg.com.bioparque.data;

import arg.com.bioparque.domain.CuidadorEspecie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorEspecieRepository extends JpaRepository<CuidadorEspecie, Long>{
    
}
