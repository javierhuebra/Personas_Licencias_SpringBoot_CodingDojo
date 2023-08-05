package com.codingdojo.licencias.controllers;

import com.codingdojo.licencias.models.Licencia;
import com.codingdojo.licencias.models.Persona;
import com.codingdojo.licencias.services.LicenciaService;
import com.codingdojo.licencias.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    private final PersonaService personaService;
    private final LicenciaService licenciaService;

    public MainController(PersonaService personaService, LicenciaService licenciaService) {
        this.personaService = personaService;
        this.licenciaService = licenciaService;
    }

    //Ruta principal para mostrar todas las personas con la licencia si la tiene vinculada
    @GetMapping("/")
    public String index(Model viewModel) {
        List<Persona> todasLasPersonas = personaService.allPersonas();
        viewModel.addAttribute("personas",todasLasPersonas);
        return "index";
    }

    //Renderizar vista de nueva persona y recibe el modelo
    @GetMapping("/persons/new")
    public String personaNew(Model model) {
        model.addAttribute("persona", new Persona());
        return "personanew";
    }

    //Capturar los datos del formulario para crear una nueva persona y redireccionar
    @PostMapping("/persons/new")
    public String crearPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult resultado) {
        if (resultado.hasErrors()) {
            System.out.println(resultado+"errores" + persona);
            return "personanew";
        } else {
            personaService.createPersona(persona);
            return "redirect:/";
        }
    }

    //Renderizar vista de nueva licencia y recibe el modelo
    @GetMapping("/licences/new")
    public String licenciaNew(@ModelAttribute("licencia") Licencia licencia, Model viewModel){
        List<Persona> todasLasPersonas = personaService.obtenerPersonasSinLic();//Aca busco personas sin licencias asignadas, podria traer todas las personas pero como es 1 a 1 no se puede asignar.
        viewModel.addAttribute("personas", todasLasPersonas);

        return "licencianew";
    }

    //Capturar los datos del formulario
    @PostMapping("/licences/new")
    public String crearLicencia(@Valid @ModelAttribute("licencia") Licencia licencia,
                                BindingResult resultado, Model viewModel){
        if(resultado.hasErrors()){
            viewModel.addAttribute("personas", personaService.obtenerPersonasSinLic());//Aca busco personas sin licencias asignadas, podria traer todas las personas pero como es 1 a 1 no se puede asignar.
            System.out.println(resultado);
            return "licencianew";
        }
        licenciaService.createLicencia(licencia);
        return "redirect:/";
    }

    @GetMapping("/persons/{id}")
    public String showPersons(@PathVariable("id") Long id, Model model){
        Persona persona = personaService.findPersona(id);
        model.addAttribute("persona", persona);
        Licencia licenciaDePersona = persona.getLicencia();
        model.addAttribute("licenciaDePersona", licenciaDePersona);
        return "detailpersona";
    }
}