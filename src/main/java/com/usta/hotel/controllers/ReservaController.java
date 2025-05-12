package com.usta.hotel.controllers;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.ReservaEntity;
import com.usta.hotel.models.services.HabitacionService;
import com.usta.hotel.models.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReservaController {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/reservar/{id}")
    public String reservarHabitacion(@PathVariable Long id, Model model) {
        HabitacionEntity habitacion = habitacionService.findById(id);
        System.out.println("Habitacion encontrada: " + habitacion);
        if (habitacion == null || !habitacion.getDisponibilidad()) {
            model.addAttribute("error", "This room is no longer available");
            return "redirect:/habitacion";
        }

        model.addAttribute("habitacion", habitacion);
        model.addAttribute("reserva", new ReservaEntity());
        return "reservas/realizarReserva";
    }
}
