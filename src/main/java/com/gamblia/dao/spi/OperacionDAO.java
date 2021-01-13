package com.gamblia.dao.spi;

import com.gamblia.model.Movimiento;
import com.gamblia.model.Operacion;
import com.gamblia.model.criteria.MovimientoCriteria;
import com.gamblia.model.criteria.OperacionCriteria;

import java.sql.Connection;
import java.util.List;

public interface OperacionDAO {

    Operacion findById(Connection connection, Integer id);

    List<Operacion> findAll(Connection connection);

    List<Operacion> findBy(Connection connection, OperacionCriteria operacionCriteria);

    Operacion update(Connection connection, Operacion operacion);

    Operacion create(Connection connection, Operacion operacion);

}
