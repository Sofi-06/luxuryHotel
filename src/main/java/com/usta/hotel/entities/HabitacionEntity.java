package com.usta.hotel.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "HABITACIONES")

//Serializable: llave primaria autoincrementable
public class HabitacionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_hab", length = 20, nullable = false)
    private String tipoHab;

    @NotNull
    //scale es para lo decimales, precision cuantos numeros hay
    @Column(name = "precio_hab", nullable = false, scale = 2, precision = 10)
    private BigDecimal precioHab;

    @NotNull
    @Column(name = "disponibilidad", columnDefinition = "boolean", nullable = false)
    private Boolean disponibilidad;

    @NotNull
    @Size(min = 20, max = 200)
    @Column(name = "foto_hab", length = 200, nullable = false)
    private String fotoHab;

    @NotNull
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    public  Boolean getDisponibilidad() {return disponibilidad;}

    public Long getIdHabitacion() {return idHabitacion;}

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipoHab() {return tipoHab;}
    public String getFotoHab() {return fotoHab;}
    public Integer getCapacidad() {return capacidad;}
    public BigDecimal getPrecioHab() {return precioHab;}

    public void setFotoHab(String fotoHab) {
        this.fotoHab = fotoHab;
    }

    public void setCapacidad(Integer capacidad) {this.capacidad = capacidad;}
    public void setPrecioHab(BigDecimal precioHab) {this.precioHab = precioHab;}
    public void setTipoHab(String tipoHab) {this.tipoHab = tipoHab;}

    @ManyToMany(mappedBy = "habitaciones")
    private Set<ReservaEntity> reservas;
}
