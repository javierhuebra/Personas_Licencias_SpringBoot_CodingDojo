package com.codingdojo.licencias.repositories;

import com.codingdojo.licencias.models.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,Long> {
    List<Persona> findAll();

    //Metodo que detecta la busqueda por sintaxis
    List<Persona> findByLicenciaIdIsNull();

    //@Query("SELECT p.columna1, p.columna2, p.columna3, ... FROM persona p LEFT OUTER JOIN licencia l ON p.id=l.persona_id WHERE l.id IS NULL")
    //List<Persona> encontrarNoLic(); //Puede ser asitambien porque con * se complica

    @Query("SELECT p FROM Persona p LEFT OUTER JOIN Licencia l ON p.id = l.persona.id WHERE l.id IS NULL")//Esto es una proyeccción para seleccionar una consulta
    List<Persona> encontrarNoLic();



}
