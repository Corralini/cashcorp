package com.gamblia.dao.spi;

import com.gamblia.model.Prestamo;
import com.gamblia.model.criteria.PrestamoCriteria;

import java.sql.Connection;
import java.util.List;

public interface PrestamoDAO {

    Prestamo findById(Connection connection, Integer id);

    List<Prestamo> findAll(Connection connection);

    List<Prestamo> findBy(Connection connection, PrestamoCriteria prestamoCriteria);

    Prestamo update(Connection connection, Prestamo prestamo);

    Prestamo create(Connection connection, Prestamo prestamo);

}
