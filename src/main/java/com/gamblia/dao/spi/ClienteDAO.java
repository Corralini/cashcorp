package com.gamblia.dao.spi;

import com.gamblia.model.Cliente;
import com.gamblia.model.criteria.ClienteCriteria;

import java.sql.Connection;
import java.util.List;

public interface ClienteDAO {

    Cliente findById (Connection connection, Integer id);

    List<Cliente> findAll (Connection connection);

    List<Cliente> findBy (Connection connection, ClienteCriteria clienteCriteria);

    Cliente update (Connection connection, Cliente cliente);

    Cliente create (Connection connection, Cliente cliente);

    void delete (Connection connection, Integer id);

}
