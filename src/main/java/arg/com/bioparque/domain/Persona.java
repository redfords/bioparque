package arg.com.bioparque.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Table(name = "persona")
public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;
    
    private String nombre;

    private String direccion;
    
    private String telefono;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
    
    @Column(name = "user_name")
    private String userName;
    
    private String rol;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "cuidador_especie", joinColumns = { @JoinColumn(name = "id_persona") }, inverseJoinColumns = {
            @JoinColumn(name = "id_especie") })
    private Set<Especie> especies;
    public void addEspecie(Especie especie) {
        this.especies.add(especie);
        especie.getPersonas().add(this);
    }
    public void removeEspecie(Especie especie) {
        this.getEspecies().remove(especie);
        especie.getPersonas().remove(this);
    }
    public void removeEspecies() {
        for (Especie especie : new HashSet<>(especies)) {
            removeEspecie(especie);
        }
    }

    public Persona() {

    }
}
