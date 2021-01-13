package com.gamblia.dao.spi;

import com.gamblia.model.Cuenta;
import com.gamblia.model.Direccion;
import com.gamblia.model.criteria.CuentaCriteria;
import com.gamblia.model.criteria.DireccionCriteria;

import java.sql.Connection;
import java.util.List;

public interface DireccionDAO {

    Direccion findById(Connection connection, Integer id);

    List<Direccion> findAll(Connection connection);

    List<Direccion> findBy(Connection connection, DireccionCriteria direccionCriteria);

    Direccion update(Connection connection, Direccion direccion);

    Direccion create(Connection connection, Direccion direccion);

}
