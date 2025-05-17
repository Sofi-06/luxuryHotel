package com.usta.hotel.controllers;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.ReservaEntity;
import com.usta.hotel.models.services.HabitacionService;
import com.usta.hotel.models.services.ReservaService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.Closeable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
@Controller
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;
    @Autowired
    private ReservaService reservaService;

    @GetMapping(value = "/habitacion")
    public String listarHab(Model model) {
        model.addAttribute("title", "Room List");
        model.addAttribute("urlRegistro", "/crearHabitacion");
        List<HabitacionEntity> lista = habitacionService.findAll();
        lista.sort(Comparator.comparing(HabitacionEntity::getIdHabitacion));
        model.addAttribute("habitaciones", lista);
        return "/habitaciones/listarHabitaciones";
    }




    /*------------------------------------------------------------------------------------------------*/

    @PostMapping(value = "/eliminarHab/{id}")
    public String eliminarHabitacion(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        System.out.println("Intentando eliminar habitación con ID: " + id);

        if (id > 0) {
            HabitacionEntity habitacion = habitacionService.findById(id);
            if (habitacion != null) {
                System.out.println("Habitación encontrada: " + habitacion.getIdHabitacion());

                List<ReservaEntity> reservas = reservaService.findByHab(habitacion);
                if (reservas != null) {
                    System.out.println("Reservas asociadas encontradas: " + reservas.size());
                } else {
                    System.out.println("Error: el método findByHab() devolvió null");
                }

                if (reservas != null && !reservas.isEmpty()) {
                    System.out.println("No se puede eliminar, hay reservas asociadas.");
                    redirectAttributes.addFlashAttribute("error", "Cannot delete room: There are reservations associated");
                    return "redirect:/habitacion";
                }

                habitacionService.deleteById(id);
                System.out.println("Habitación eliminada correctamente.");
                redirectAttributes.addFlashAttribute("success", "Room deleted successfully");
            } else {
                System.out.println("Error: No se encontró la habitación con ID: " + id);
                redirectAttributes.addFlashAttribute("error", "Room not found");
            }
        } else {
            System.out.println("Error: ID inválido.");
            redirectAttributes.addFlashAttribute("error", "Invalid ID");
        }

        return "redirect:/habitacion";
    }

    /*------------------------------------------------------------------------------------------------*/
    @GetMapping(value = "/crearHabitacion")
    public String crearHab(Model model) {
        model.addAttribute("title", "Register a New Room");
        model.addAttribute("habitacion", new HabitacionEntity());
        return "/habitaciones/formNewHabitacion";
    }

    /*------------------------------------------------------------------------------------------------*/

    @PostMapping(value = "/crearHabitacion")
    public String guardarHabitacion(@Valid HabitacionEntity habitacion, @RequestParam(value = "foto")MultipartFile foto, BindingResult result, RedirectAttributes redirectAttributes) {
        String urlImagen = guardarImagen(foto);
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "habitaciones/crearHabitacion";

        }

        habitacion.setFotoHab(urlImagen);
        habitacion.setDisponibilidad(true);
        habitacionService.save(habitacion);
        redirectAttributes.addFlashAttribute("mensajeExito", "Room Saved Successfully");
        return "redirect:/habitacion";
    }

    private String guardarImagen(MultipartFile imagen) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", "66a84b97d5507f774decf88c11a4e1b1", ContentType.TEXT_PLAIN);

            builder.addBinaryBody("image", imagen.getInputStream(), ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(responseEntity);
                JSONObject jsonResponse = new JSONObject(responseString);
                boolean success = jsonResponse.getBoolean("success");

                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    return data.getString("url");
                }else{
                    System.err.println("Error loading image: " + jsonResponse.optString("error", "Unknown Error"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /*--------------------------------------------------------------------------------------------------*/

    @GetMapping(value = "/editarHabitacion/{id}")
    public String editarHabitacion(Model model, @PathVariable(value = "id") Long idHabitacion) {
        HabitacionEntity habitacion = habitacionService.findById(idHabitacion);
        model.addAttribute("title", "Edit Room");
        model.addAttribute("habitacionEdit", habitacion);
        model.addAttribute("imagen", habitacion.getFotoHab());
        return "habitaciones/editarRoom";
    }

    /*--------------------------------------------------------------------------------------------------*/
    @PostMapping(value = "/editarHabitacion/{id}")
    public String editHab(@ModelAttribute("habitacionEdit") HabitacionEntity habitacion, @PathVariable(value = "id") Long idHabitacion, RedirectAttributes redirectAttributes,
                          @RequestParam(value = "foto") MultipartFile foto,
                          @RequestParam(value = "imagenAnterior") String imagenAnterior,
                          BindingResult result) throws IOException {
        HabitacionEntity habitacionAux = habitacionService.findById(idHabitacion);
        habitacionAux.setIdHabitacion(idHabitacion);
        habitacionAux.setCapacidad(habitacion.getCapacidad());
        habitacionAux.setDisponibilidad(habitacion.getDisponibilidad());
        habitacionAux.setPrecioHab(habitacion.getPrecioHab());
        habitacionAux.setTipoHab(habitacion.getTipoHab());

        String nombreImagen= guardarImagen(foto);
        if (nombreImagen == null || nombreImagen.isBlank()) {
            habitacionAux.setFotoHab(imagenAnterior);
        }else {
            habitacionAux.setFotoHab(nombreImagen);
        }
        habitacionService.actualizarHabitacion(habitacionAux);
        redirectAttributes.addFlashAttribute("success", "Room Updated successfully");
        return "redirect:/habitacion";
    }

}
