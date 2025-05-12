package com.usta.hotel.models.services;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.ReservaEntity;
import com.usta.hotel.models.dao.HabitacionDAO;
import com.usta.hotel.models.dao.ReservaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImplement implements ReservaService {

    @Autowired
    private ReservaDAO reservaDAO;

    @Override
    @Transactional
    public List<ReservaEntity> findAll() {
        return (List<ReservaEntity>) reservaDAO.findAll();
    }

    @Override
    @Transactional
    public void save(ReservaEntity reserva) {
        reservaDAO.save(reserva);
    }

    @Override
    @Transactional
    public ReservaEntity findById(Long id) {
        return reservaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
         reservaDAO.deleteById(id);
    }

    @Override
    @Transactional
    public ReservaEntity actualizarReserva(ReservaEntity reserva) {
        return reservaDAO.save(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaEntity viewDetail(Long id) {
        return reservaDAO.viewDetail(id);
    }

    @Override
    @Transactional
    public List<ReservaEntity> findByHab(HabitacionEntity habitacion) {
        return reservaDAO.findByHab(habitacion);
    }


}
