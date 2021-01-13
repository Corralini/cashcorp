package com.gamblia.dao.spi;

import com.gamblia.model.Interno;
import com.gamblia.model.criteria.InternoCriteria;

import java.sql.Connection;
import java.util.List;

public interface InternoDAO {

    Interno findById(Connection connection, Integer id);

    List<Interno> findAll(Connection connection);

    List<Interno> findBy(Connection connection, InternoCriteria internoCriteria);

    Interno update(Connection connection, Interno interno);

    Interno create(Connection connection, Interno interno);

}
