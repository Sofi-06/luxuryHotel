package com.usta.hotel.security;

import com.usta.hotel.entities.UsuarioEntity;
import com.usta.hotel.entities.RolEntity;
import com.usta.hotel.models.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioDao.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("The user doesn't exist");
        }

        System.out.println("Checking role...: " + usuario.getRol().getIdRol());

        return new User(
                usuario.getEmail(),
                usuario.getClave(),
                mapearAutoridadesRol(usuario.getRol())
        );
    }

    private Collection<? extends GrantedAuthority>
    mapearAutoridadesRol(RolEntity rol) {
        return List.of(new SimpleGrantedAuthority(rol.getRol()));
    }
}