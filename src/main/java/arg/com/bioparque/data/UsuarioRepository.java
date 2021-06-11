package arg.com.bioparque.data;

import arg.com.bioparque.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
}
