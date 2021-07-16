package arg.com.bioparque.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PersonaDTO {
    private Integer id;
    private String nombre;
    private Set<String> especies = new HashSet<>();
}
