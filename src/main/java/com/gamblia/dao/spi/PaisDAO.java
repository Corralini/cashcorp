package com.gamblia.dao.spi;

import com.gamblia.model.Operacion;
import com.gamblia.model.Pais;
import com.gamblia.model.criteria.OperacionCriteria;
import com.gamblia.model.criteria.PaisCriteria;

import java.sql.Connection;
import java.util.List;

public interface PaisDAO {

    Pais findById(Connection connection, Integer id);

    List<Pais> findAll(Connection connection);

    List<Pais> findBy(Connection connection, PaisCriteria paisCriteria);

    Pais update(Connection connection, Pais pais);

    Pais create(Connection connection, Pais pais);

}
