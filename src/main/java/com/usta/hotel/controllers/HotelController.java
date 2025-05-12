package com.usta.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    @GetMapping(value = "/verHotel")
    public String verMasHotel(Model model){
        model.addAttribute("tittle", "Luxury Hotel");
        return "hotel/verDetalleHotel";
    }
}
