package com.usta.hotel.models.services;

import com.usta.hotel.entities.UsuarioEntity;
import com.usta.hotel.models.dao.UsuarioDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplement implements UsuarioService{
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    @Transactional
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) usuarioDAO.findAll();
    }

    @Override
    @Transactional
    public void save(UsuarioEntity usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional
    public UsuarioEntity findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario) {
        return usuarioDAO.save(usuario);
    }

    @Override
    @Transactional
    public void changeState(Long id) {
        usuarioDAO.changeState(id);
    }

    @Override
    @Transactional
    public UsuarioEntity findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }
}
