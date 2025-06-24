package ld.gestion_herramientas.service;

import ld.gestion_herramientas.repository.HerramientaRepository;
import ld.gestion_herramientas.entity.Herramienta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HerramientaServiceImpl implements HerramientaService{
    @Autowired
    private HerramientaRepository herramientaRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Herramienta> findAll() {

        return (List<Herramienta>) herramientaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Herramienta> findAll(Pageable pageable) {

        return herramientaRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Herramienta empleado) {

        herramientaRepository.save(empleado);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        herramientaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Herramienta findOne(Long id) {

        return herramientaRepository.findById(id).orElse(null);
    }

    // HerramientaServiceImpl.java
    @Override
    public Page<Herramienta> buscarPorTermino(String termino, Pageable pageable) {
        return herramientaRepository.buscarPorTermino(termino, pageable);
    }


    //============================= Codigo para las pruebas unitarias =======================================
/*
    @Override
    public List<Herramienta> findAllTools() {
        return herramientaRepository.findAll();
    }

    @Override
    public Herramienta saveTool(Herramienta herramienta) {
        return herramientaRepository.save(herramienta);
    }

    @Override
    public Herramienta updateTool(Long id, Herramienta herramienta) {
        Herramienta herramienta1 = herramientaRepository.findById(id).get();
        if(Objects.nonNull(herramienta.getGmt())
                && !"".equalsIgnoreCase(herramienta.getGmt())){
            herramienta1.setGmt(herramienta.getGmt());
        }
        if(Objects.nonNull(herramienta.getMarca()) && !"".equalsIgnoreCase(herramienta.getMarca())){
            herramienta1.setMarca(herramienta.getMarca());
        }
        if(Objects.nonNull(herramienta.getModelo()) && !"".equalsIgnoreCase(herramienta.getModelo())){
            herramienta1.setModelo(herramienta.getModelo());
        }
        return herramientaRepository.save(herramienta1);


    }

    @Override
    public void deleteTool(Long id) {
        herramientaRepository.deleteById(id);
    }

    @Override
    public Optional<Herramienta> findToolByGmtWithJPQL(String gmt) {
        return herramientaRepository.findToolByGmtWithJPQL(gmt);
    }

    @Override
    public Optional<Herramienta> findByGmt(String gmt) {
        return herramientaRepository.findByGmt(gmt);
    }

    @Override
    public Optional<Herramienta>findByMarcaIgnoreCase(String marca) {
        return herramientaRepository.findByMarcaIgnoreCase(marca);
    }

    @Override
    public List<Herramienta> findByModelo(String modelo) {
        return herramientaRepository.findByModelo(modelo);
    }


    @Override
    public Herramienta findHerramientaById(Long id) throws HerramientaNotFoundException {
        Optional<Herramienta> herramienta = herramientaRepository.findById(id);
        if(!herramienta.isPresent()){
            throw new HerramientaNotFoundException("Id de la herramienta no disponible");
        }
        return herramienta.get();
    }

*/




    //===========================================================================================







}
