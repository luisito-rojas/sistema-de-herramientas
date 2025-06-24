package ld.gestion_herramientas.service;

import ld.gestion_herramientas.entity.Herramienta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HerramientaService {

    
    public List<Herramienta> findAll();

    public Page<Herramienta> findAll(Pageable pageable);

    public void save(Herramienta herramienta);

    public Herramienta findOne(Long id);

    public void delete(Long id);


    // HerramientaService.java
    Page<Herramienta> buscarPorTermino(String termino, Pageable pageable);




}
