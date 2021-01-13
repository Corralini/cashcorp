package com.gamblia.dao.spi;

import com.gamblia.model.Interno;
import com.gamblia.model.Movimiento;
import com.gamblia.model.criteria.InternoCriteria;
import com.gamblia.model.criteria.MovimientoCriteria;

import java.sql.Connection;
import java.util.List;

public interface MovimientoDAO {

    Movimiento findById(Connection connection, Integer id);

    List<Movimiento> findAll(Connection connection);

    List<Movimiento> findBy(Connection connection, MovimientoCriteria movimientoCriteria);

    Movimiento update(Connection connection, Movimiento movimiento);

    Movimiento create(Connection connection, Movimiento movimiento);

}
