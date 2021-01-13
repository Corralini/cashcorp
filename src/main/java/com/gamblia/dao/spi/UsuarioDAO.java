package com.gamblia.dao.spi;

import com.gamblia.model.Prestamo;
import com.gamblia.model.Usuario;
import com.gamblia.model.criteria.PrestamoCriteria;
import com.gamblia.model.criteria.UsuarioCriteria;

import java.sql.Connection;
import java.util.List;

public interface UsuarioDAO {

    Usuario findById(Connection connection, Integer id);

    List<Usuario> findAll(Connection connection);

    List<Usuario> findBy(Connection connection, UsuarioCriteria usuarioCriteria);

    Usuario update(Connection connection, Usuario usuario);

    Usuario create(Connection connection, Usuario usuario);

}
