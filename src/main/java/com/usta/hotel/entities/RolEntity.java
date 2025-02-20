package com.usta.hotel.entities;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ROLES")

public class RolEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Rol", length = 40, nullable = false)
    private String rol;

    //va a manejar parte de seguridad y no se llena en los formularios
    public RolEntity(String Rol) {
        super();
        this.rol = Rol;
    }

    public RolEntity() {

    }
}
