package com.gamblia.dao.spi;

import com.gamblia.model.Persona;
import com.gamblia.model.Plazo;
import com.gamblia.model.criteria.PersonaCriteria;
import com.gamblia.model.criteria.PlazoCriteria;

import java.sql.Connection;
import java.util.List;

public interface PlazoDAO {

    Plazo findById(Connection connection, Integer id);

    List<Plazo> findAll(Connection connection);

    List<Plazo> findBy(Connection connection, PlazoCriteria plazoCriteria);

    Plazo update(Connection connection, Plazo plazo);

    Plazo create(Connection connection, Plazo plazo);

}
