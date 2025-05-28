package com.usta.hotel.controllers;

import com.usta.hotel.entities.RolEntity;
import com.usta.hotel.entities.UsuarioEntity;
import com.usta.hotel.models.dao.UsuarioDAO;
import com.usta.hotel.models.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @GetMapping(value = "/register")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        model.addAttribute("title", "Register a new user");
        return "register";
    }

    // --------------------------------
    @PostMapping("/register")
    public String registro(@ModelAttribute("usuario") @Valid UsuarioEntity usuario,
                           BindingResult result,
                           @RequestParam("confirmarClave") String confirmarClave,
                           Model model, RedirectAttributes redirectAttributes,
                           SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Register a new user");
            return "register";
        }

        if (!usuario.getClave().equals(confirmarClave)) {
            result.rejectValue("clave", "error.usuario", "The passwords do not match.");
            model.addAttribute("title", "Register a new User");
            return "register";
        }

        String pass = new BCryptPasswordEncoder().encode(usuario.getClave());
        usuario.setClave(pass);
        usuario.setEstadoUsu(true);

        RolEntity rolHuesped = new RolEntity();
        rolHuesped.setIdRol(2L);
        usuario.setRol(rolHuesped);
        usuario.setFechaReg(LocalDate.now());
        usuarioService.save(usuario);
        status.setComplete();
        redirectAttributes.addFlashAttribute("success", "User registered successfully!");
        return "redirect:/login";
    }
}


