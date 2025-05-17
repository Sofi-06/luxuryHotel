package com.usta.hotel.models.dao;

import com.usta.hotel.entities.HabitacionEntity;
import com.usta.hotel.entities.ReservaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservaDAO extends CrudRepository<ReservaEntity, Long> {
    @Transactional
    @Query("SELECT RE FROM ReservaEntity RE WHERE RE.idReserva=?1")
    public ReservaEntity viewDetail(Long idReserva);
 
    @Transactional
    @Query("SELECT r FROM ReservaEntity r JOIN r.habitaciones h WHERE h = :habitacion")
    public List<ReservaEntity> findByHab(@Param("habitacion") HabitacionEntity habitacion);

    @Transactional
    @Query("SELECT r FROM ReservaEntity r ORDER BY r.fechaIni DESC")
    List<ReservaEntity> findAllOrderByFechaIniDesc();


}
