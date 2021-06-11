package arg.com.bioparque.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cuidador_especie")
public class CuidadorEspecie implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuidador_especie")
    private Long idCuidadorEspecie;
    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(cascade = CascadeType.ALL)
    private Persona persona;
    
    @JoinColumn(name = "id_especie", referencedColumnName = "id_especie")
    @ManyToOne(cascade = CascadeType.ALL)
    private Especie especie;
    
}
