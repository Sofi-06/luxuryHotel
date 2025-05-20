package com.usta.hotel.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "RESERVAS")

public class ReservaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ini", nullable = false)
    private LocalDate fechaIni;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado_res", length = 20, nullable = false)
    private String estadoRes;

    @NotNull
    @Column(name = "cantidad_hab", nullable = false)
    private Integer cantidadHab;

    //conexión con tabla Usuarios
    @NotNull
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UsuarioEntity usuario;

    //conexión con Habitaciones para crear tabla de rompimiento
    //no se genera campo, es la tabla de rompimiento
    //el cascade para consistencia de los datos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "reservas_hab", joinColumns = @JoinColumn(name = "id_reserva",
            referencedColumnName = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_habitacion", referencedColumnName = "id_habitacion"))
    private Collection<HabitacionEntity> habitaciones = new ArrayList<>();


}
