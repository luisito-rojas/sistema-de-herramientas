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

    //@NotNull
    @NotEmpty
    private String modelo;

    //@NotNull
    @NotEmpty
    private String marca;

    //@NotNull
    @NotEmpty
    private String terminal;

    //@NotNull
    @NotEmpty
    private String ensamble;

    //@NotNull
    @NotEmpty
    private String tipoHerramienta;


    @NotNull
    //@FutureOrPresent(message = "La fecha no puede ser pasada")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaProximoMantenimiento;


    //@NotNull
    @NotEmpty
    private String gmt;



    //==============
    private  String nombre;

    private String numeroPersona;



    //@FutureOrPresent(message = "La fecha no puede ser pasada")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaSalida;

   //@FutureOrPresent(message = "La fecha no puede ser pasada")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaRegreso;


    private String link;

    /*

    public Herramienta(String modelo, String marca, String terminal, String ensamble, String tipoHerramienta, String gmt) {
        this.modelo = modelo;
        this.marca = marca;
        this.terminal = terminal;
        this.ensamble = ensamble;
        this.tipoHerramienta = tipoHerramienta;
        this.gmt = gmt;
    }


    public Herramienta() {
    }

    public Herramienta(Long id, String modelo, String marca, String terminal, String ensamble, String tipoHerramienta, String gmt) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.terminal = terminal;
        this.ensamble = ensamble;
        this.tipoHerramienta = tipoHerramienta;
        this.gmt = gmt;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getEnsamble() {
        return ensamble;
    }

    public void setEnsamble(String ensamble) {
        this.ensamble = ensamble;
    }

    public String getTipoHerramienta() {
        return tipoHerramienta;
    }

    public void setTipoHerramienta(String tipoHerramienta) {
        this.tipoHerramienta = tipoHerramienta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroPersona() {
        return numeroPersona;
    }

    public void setNumeroPersona(String numeroPersona) {
        this.numeroPersona = numeroPersona;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    */

}
