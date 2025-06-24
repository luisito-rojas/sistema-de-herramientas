package ld.gestion_herramientas.repository;

import ld.gestion_herramientas.entity.Herramienta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HerramientaRepository extends PagingAndSortingRepository<Herramienta, Long> {


    @Query("SELECT h FROM Herramienta h WHERE " +
            "h.modelo LIKE CONCAT('%', :termino, '%') OR " +
            "h.marca LIKE CONCAT('%', :termino, '%') OR " +
            "h.gmt LIKE CONCAT('%', :termino, '%') OR " +
            "h.terminal LIKE CONCAT('%', :termino, '%') OR " +
            "h.tipoHerramienta LIKE CONCAT('%', :termino, '%') OR " +
            "h.ensamble LIKE CONCAT('%', :termino, '%') OR " +
            "STR(h.fechaProximoMantenimiento) LIKE %:termino%")
    Page<Herramienta> buscarPorTermino(@Param("termino") String termino, Pageable pageable);


}

