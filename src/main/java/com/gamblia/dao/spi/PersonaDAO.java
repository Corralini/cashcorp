package com.gamblia.dao.spi;

import com.gamblia.model.Pais;
import com.gamblia.model.Persona;
import com.gamblia.model.criteria.PaisCriteria;
import com.gamblia.model.criteria.PersonaCriteria;

import java.sql.Connection;
import java.util.List;

public interface PersonaDAO {

    Persona findById(Connection connection, Integer id);

    List<Persona> findAll(Connection connection);

    List<Persona> findBy(Connection connection, PersonaCriteria personaCriteria);

    Persona update(Connection connection, Persona persona);

    Persona create(Connection connection, Persona persona);

}
