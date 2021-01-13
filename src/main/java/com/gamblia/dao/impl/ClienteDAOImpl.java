package com.gamblia.dao.impl;

import com.gamblia.dao.spi.ClienteDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Cliente;
import com.gamblia.model.criteria.ClienteCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    private final Logger logger = LogManager.getLogger(ClienteDAOImpl.class.getName());
    private final String select = " SELECT ID, FECHA_CREACION ";

    public ClienteDAOImpl() {
    }

    @Override
    public Cliente findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM CLIENTE WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Cliente cliente = null;
                if (resultSet.next()) {
                    cliente = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Cliente {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return cliente;
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return null;
    }

    @Override
    public List<Cliente> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Cliente> clientes = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM CLIENTE ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Cliente cliente;

                while (resultSet.next()) {
                    cliente = loadNext(resultSet);
                    clientes.add(cliente);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return clientes;
    }

    @Override
    public List<Cliente> findBy(Connection connection, ClienteCriteria clienteCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", clienteCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Cliente> clientes = new ArrayList<>();
        if (connection != null && clienteCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM CLIENTE ");

                boolean first = true;

                if (clienteCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (clienteCriteria.getFechaCreacion() != null) {
                    DAOUtils.addClause(query, first, " FECHA_CREACION = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (clienteCriteria.getId() != null) preparedStatement.setInt(i++, clienteCriteria.getId());
                if (clienteCriteria.getNombre() != null)
                    preparedStatement.setDate(i, (java.sql.Date) clienteCriteria.getFechaCreacion());

                resultSet = preparedStatement.executeQuery();

                Cliente cliente;

                while (resultSet.next()) {
                    cliente = loadNext(resultSet);
                    clientes.add(cliente);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return clientes;
    }

    @Override
    public Cliente update(Connection connection, Cliente cliente) {
        if (logger.isDebugEnabled()) logger.debug("Update cliente: {}", cliente);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && cliente != null && cliente.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE MESA ");

                if (cliente.getFechaCreacion() != null) {
                    DAOUtils.addUpdate(query, true, " FECHA_CREACION = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (cliente.getFechaCreacion() != null)
                    preparedStatement.setDate(i++, (java.sql.Date) cliente.getFechaCreacion());
                preparedStatement.setInt(i, cliente.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Cliente no actualizada");
                    return cliente;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de cliente duplicado");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return null;
    }

    @Override
    public Cliente create(Connection connection, Cliente cliente) {
        if (logger.isDebugEnabled()) logger.debug("Create cliente: {}", cliente);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        if (cliente != null && cliente.getId() != null) {
            try {
                String query = " INSERT INTO CLIENTE (ID)" +
                        " values (?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query);

                int i = 1;
                preparedStatement.setInt(i, cliente.getId());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Cliente'");
                }

                return cliente;

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    @Override
    public void delete(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder("DELETE FROM CLIENTE WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
    }

    private Cliente loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt(i++));
        cliente.setFechaCreacion(resultSet.getDate(i++));
        return cliente;
    }
}
