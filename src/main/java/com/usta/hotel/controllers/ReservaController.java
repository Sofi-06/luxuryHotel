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

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservaController {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/reserva")
    public String listarReserva(Model model) {

        List<ReservaEntity> reservas = reservaService.findAllOrderByFechaIniDesc();
        LocalDate hoy = LocalDate.now();
        model.addAttribute("title", "Reservation list");
        model.addAttribute("reservas", reservas);
        model.addAttribute("hoy", hoy);
        return "reservas/listarReservas";
    }
}
