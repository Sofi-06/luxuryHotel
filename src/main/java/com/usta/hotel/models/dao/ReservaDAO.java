package com.usta.hotel.models.dao;

import com.usta.hotel.entities.ReservaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ReservaDAO extends CrudRepository<ReservaEntity, Long> {
    @Transactional
    @Query("SELECT RE FROM ReservaEntity RE WHERE RE.idReserva=?1")
    public ReservaEntity viewDetail(Long idReserva);


}
