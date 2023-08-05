package com.codingdojo.licencias.services;

import com.codingdojo.licencias.models.Persona;
import com.codingdojo.licencias.repositories.LicenciaRepository;
import com.codingdojo.licencias.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;
    private final LicenciaRepository licenciaRepository;

    public PersonaService(PersonaRepository personaRepository, LicenciaRepository licenciaRepository){
        this.personaRepository = personaRepository;
        this.licenciaRepository = licenciaRepository;
    }

    //Devolver todas las personas
    public List<Persona> allPersonas() {
        return personaRepository.findAll();
    }

    //Creando una persona
    public Persona createPersona(Persona p) {
        return personaRepository.save(p);
    }

    //Devolver una persona por id
    public Persona findPersona(Long id) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if(optionalPersona.isPresent()) {
            return optionalPersona.get();
        }else {
            return null;
        }
    }

    //Obtener personas sin licencia
    public List<Persona> obtenerPersonasSinLic(){
        return personaRepository.encontrarNoLic();//Aca estoy trabajando con la consulta personalizada tranquilamente podria usar la otra: findByLicenciaIdIsNull();
    }
}
