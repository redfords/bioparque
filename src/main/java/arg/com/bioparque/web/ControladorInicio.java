package arg.com.bioparque.web;

import arg.com.bioparque.data.CuidadorEspecieRepository;
import arg.com.bioparque.data.EspecieRepository;
import arg.com.bioparque.data.GuiaItinerarioRepository;
import arg.com.bioparque.data.PersonaRepository;
import arg.com.bioparque.domain.CuidadorEspecie;
import arg.com.bioparque.domain.Especie;
import arg.com.bioparque.domain.GuiaItinerario;
import arg.com.bioparque.domain.Persona;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private CuidadorEspecieRepository cuidadorEspecieRepository;
    
    @Autowired
    private EspecieRepository especieRepository;
    
    @Autowired
    private GuiaItinerarioRepository guiaItinerarioRepository;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        log.info("- Ejecutando el controlador String MVC");
        log.info("- Usuario que hizo login: " + user);
        
        Persona persona = personaRepository.findByUserName(user.getUsername());
        model.addAttribute("persona", persona);
        
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        
        List<CuidadorEspecie> cuidadorEspecies = cuidadorEspecieRepository.findAll();
        model.addAttribute("cuidadorEspecies", cuidadorEspecies);
            
        List<Especie> especies = especieRepository.findAll();
        model.addAttribute("especies", especies);
        
        List<GuiaItinerario> guiaItinerarios = guiaItinerarioRepository.findByPersona(persona);
        model.addAttribute("guiaItinerarios", guiaItinerarios);
        
        return "index";
    }
       
    @GetMapping("/eliminarCuidadorEspecie")
    public String eliminarCuidadorEspecie(CuidadorEspecie cuidadorEspecie){
        cuidadorEspecieRepository.delete(cuidadorEspecie);
        return "redirect:/";
    }
    
    @GetMapping("/editarCuidadorEspecie/{idCuidadorEspecie}")
    public String editarCuidadorEspecie(CuidadorEspecie cuidadorEspecie, Model model){
        cuidadorEspecie = cuidadorEspecieRepository.findById(cuidadorEspecie.getIdCuidadorEspecie()).orElse(null);
        model.addAttribute("cuidadorEspecie", cuidadorEspecie);
        return "modificarCuidadorEspecie";
    }
    
    @PostMapping("/guardarCuidadorEspecie")
    public String guardarEspecie(
            @Valid CuidadorEspecie cuidadorEspecie,
            Errors errores){
        if(errores.hasErrors()){
            return "modificarCuidadorEspecie";
        }
        Persona persona = personaRepository.findById(cuidadorEspecie.getPersona().getId()).orElse(null);
        especieRepository.save(cuidadorEspecie.getEspecie());
        cuidadorEspecie.setPersona(persona);
        cuidadorEspecieRepository.save(cuidadorEspecie);
        
        return "redirect:/";
    }
    
    @GetMapping("/eliminarPersona")
    public String eliminarPersona(Persona persona){
        personaRepository.delete(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editarPersona/{id}")
    public String editarPersona(Persona persona, Model model){
        persona = personaRepository.findById(persona.getId()).orElse(null);
        model.addAttribute("persona", persona);
        return "modificarPersona";
    }
    
    @GetMapping("/verPersona")
    public String verPersona(Persona persona, Model model){
        persona = personaRepository.findById(persona.getId()).orElse(null);
        if(persona.getRol().equals("cuidador")){
            //List<Especie> especies = especieRepository.findByPersonaId(persona.getId());
            //model.addAttribute("cuidadorEspecies", especies);
            model.addAttribute("especies",especieRepository.findAll());
            return "adminModificarCuidadorEspecie";
        }else if(persona.getRol().equals("guia")){
            return "redirect:/";
        }
        return "redirect:/";
    }
    
    @PostMapping("/guardarPersona")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            System.out.println(errores.getAllErrors());
            return "modificarPersona";
        }
        personaRepository.save(persona);
        return "redirect:/";
    }
    
    @GetMapping("/eliminarGuiaItinerario")
    public String eliminarGuiaItinerario(GuiaItinerario guiaItinerario){
        guiaItinerarioRepository.delete(guiaItinerario);
        return "redirect:/";
    }
    
    @GetMapping("/editarGuiaItinerario/{idGuiaItinerario}")
    public String editarGuiaItinerario(GuiaItinerario guiaItinerario, Model model){
        guiaItinerario = guiaItinerarioRepository.findById(guiaItinerario.getIdGuiaItinerario()).orElse(null);
        model.addAttribute("guiaItinerario", guiaItinerario);
        return "modificarCuidadorEspecie";
    }
}
