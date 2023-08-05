package com.codingdojo.licencias.repositories;

import com.codingdojo.licencias.models.Licencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicenciaRepository extends CrudRepository<Licencia, Long> {
    List<Licencia> findAll();

     Licencia findTopByOrderByNumberDesc();
}
