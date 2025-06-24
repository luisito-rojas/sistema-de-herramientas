package ld.gestion_herramientas.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


//Con esta anotacion le indicamos a Spring que se tiene que mapear a la base de datos
@Entity
@Table(name = "herramientas")
@Data
@AllArgsConstructor
@NoArgsConstructor
//Esta anotacion nos permite utilizar el patron de diseño build
@Builder
public class Herramienta {

    //Con esta anotacion indicamos que va a funcionar como primaryKey
    @Id
    //Esta aotacion es una estrategia de generación de valores de ID en la que la
    // base de datos se encarga de generar automáticamente el valor,
    // usualmente con una columna AUTO_INCREMENT.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String modelo;

    @NotEmpty
    private String marca;

    @NotEmpty
    private String terminal;

    @NotEmpty
    private String ensamble;

    @NotEmpty
    private String tipoHerramienta;
    @NotEmpty
    private String gmt;

    private String nombre;
    private String numeroPersona;
    private String comentario;

    //fechas
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaProximoMantenimiento;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaSalida;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegreso;


    private String link;


}
