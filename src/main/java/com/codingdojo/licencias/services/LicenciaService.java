package com.codingdojo.licencias.services;

import com.codingdojo.licencias.models.Licencia;
import com.codingdojo.licencias.repositories.LicenciaRepository;
import com.codingdojo.licencias.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaService {

    private final LicenciaRepository licenciaRepository;

    public LicenciaService(LicenciaRepository licenciaRepository){
        this.licenciaRepository = licenciaRepository;
    }

    //Obtener todas las licencias
    public List<Licencia> allLicencias(){
        return licenciaRepository.findAll();
    }

    //Crear licencia
    public Licencia createLicencia(Licencia l){
        l.setNumber(this.generarNumerLic());
        return licenciaRepository.save(l);
    }

    //Generar numero de licencia // CODIGO DE MARCELO.
    public int generarNumerLic() {
        Licencia lic = licenciaRepository.findTopByOrderByNumberDesc();
        if(lic ==null) {
            return 1;
        }
        int numeroMayorLicencia = lic.getNumber();
        numeroMayorLicencia++;
        return numeroMayorLicencia;

    }
}
