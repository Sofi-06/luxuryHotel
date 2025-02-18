package com.usta.hotel.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "HABITACIONES")

//Serializable: llave primaria autoincrementable
public class HabitacionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_habitacion")
    private Long idHabitacion;

    @NotNull
    @Size(min=1, max=20)
    @Column(name="tipo_hab", length = 20, nullable = false)
    private String tipoHab;
}
