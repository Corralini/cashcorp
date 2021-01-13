package com.gamblia.dao.spi;

import com.gamblia.model.Cuenta;
import com.gamblia.model.criteria.CuentaCriteria;

import java.sql.Connection;
import java.util.List;

public interface CuentaDAO {

    Cuenta findById(Connection connection, Integer id);

    List<Cuenta> findAll(Connection connection);

    List<Cuenta> findBy(Connection connection, CuentaCriteria cuentaCriteria);

    Cuenta update(Connection connection, Cuenta cuenta);

    Cuenta create(Connection connection, Cuenta cuenta);

}
