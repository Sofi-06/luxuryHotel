package com.usta.hotel.models.services;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.ReservaEntity;

import java.util.List;

public interface ReservaService {
    public List<ReservaEntity> findAll();

    public void save(ReservaEntity reserva);

    public ReservaEntity findById(Long id);

    public void deleteById(Long id);

    public ReservaEntity actualizarReserva(ReservaEntity reserva);

    public ReservaEntity viewDetail(Long id);
}
