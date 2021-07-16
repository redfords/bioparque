package arg.com.bioparque.data;

import arg.com.bioparque.domain.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecieRepository extends JpaRepository<Especie, Long>{
    public Especie findById(String especieId);
    
}
