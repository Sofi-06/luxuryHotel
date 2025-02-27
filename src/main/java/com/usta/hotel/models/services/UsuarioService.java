package com.usta.hotel.models.services;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    public List<UsuarioEntity> findAll();

    public void save(UsuarioEntity usuario);

    public UsuarioEntity findById(Long id);

    public void deleteById(Long id);

    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario);

    public void changeState(Long id);

    public UsuarioEntity findByEmail(String email);
}
