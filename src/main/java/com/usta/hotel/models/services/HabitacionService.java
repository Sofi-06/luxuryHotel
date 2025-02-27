package com.usta.hotel.models.services;

import com.usta.hotel.entities.HabitacionEntity;

import java.util.List;

public interface HabitacionService {
    public List<HabitacionEntity> findAll();

    public void save(HabitacionEntity habitacion);

    public HabitacionEntity findById(Long id);

    public void deleteById(Long id);

    public HabitacionEntity actualizarHabitacion(HabitacionEntity habitacion);

    public void changeState(Long id);

    public HabitacionEntity viewDetail(Long id);
}
