package com.usta.hotel.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "RESERVAS")

public class ReservaEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    //cuando llave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reserva")
    private Long idReserva;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ini", nullable = false)
    private Date fechaIni;

}
