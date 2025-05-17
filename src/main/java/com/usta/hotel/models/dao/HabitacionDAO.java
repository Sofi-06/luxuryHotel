package com.usta.hotel.models.dao;

import com.usta.hotel.entities.HabitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

//long-id
public interface HabitacionDAO extends JpaRepository<HabitacionEntity, Long> {

    @Transactional
    @Modifying
    //toma solo un valor que sea igual
    @Query("UPDATE HabitacionEntity  SET disponibilidad=false  WHERE idHabitacion=?1")
    public void changeState(Long idHabitacion);

    @Transactional
    @Query("SELECT HA FROM HabitacionEntity HA WHERE HA.idHabitacion=?1")
    public HabitacionEntity viewDetail(Long idHabitacion);
}
