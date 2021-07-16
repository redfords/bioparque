package arg.com.bioparque.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especie")
public class Especie implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie")
    private Long idEspecie;
    
    private String nombre;
    
    @Column(name = "nombre_cientifico")
    private String nombreCientifico;
    
    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "especies")
    private Set < Persona > personas = new HashSet< >();

    public Especie() {}

    public Long getId() {
        return idEspecie;
    }

    public Set < Persona > getPersonas() {
        return personas;
    }
    public void setPersonas(Set < Persona > personas) {
        this.personas = personas;
    }
}
