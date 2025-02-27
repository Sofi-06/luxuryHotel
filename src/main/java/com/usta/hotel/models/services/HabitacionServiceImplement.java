package com.usta.hotel.models.services;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.models.dao.HabitacionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class HabitacionServiceImplement implements HabitacionService {
    //Inyeccion de datos
    @Autowired
    private HabitacionDAO habitacionDAO;

    @Override
    @Transactional
    public List<HabitacionEntity> findAll() {
        return (List<HabitacionEntity>) habitacionDAO.findAll();
    }

    @Override
    @Transactional
    public void save(HabitacionEntity habitacion) {
        habitacionDAO.save(habitacion);
    }

    @Override
    @Transactional
    public HabitacionEntity findById(Long id) {
        return habitacionDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        habitacionDAO.deleteById(id);
    }

    @Override
    @Transactional
    public HabitacionEntity actualizarHabitacion(HabitacionEntity habitacion) {
        return habitacionDAO.save(habitacion);
    }

    @Override
    @Transactional
    public void changeState(Long id) {
       habitacionDAO.changeState(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HabitacionEntity viewDetail(Long id) {
        return habitacionDAO.viewDetail(id);
    }
}
