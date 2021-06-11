package arg.com.bioparque.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "itinerario")
public class Itinerario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itinerario")
    private Long idItinerario;
    
    private String duracion;
    
    private String longitud;
    
    @Column(name = "max_visitantes")
    private Integer maxVisitantes;

    @Column(name = "num_especies")
    private Integer numEspecies;

    private String horario;
    
    @OneToMany(mappedBy = "itinerario")
    private List<GuiaItinerario> guiaItinerario;
    
}
