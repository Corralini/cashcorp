package com.gamblia.dao.impl;

import com.gamblia.dao.spi.DireccionDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Direccion;
import com.gamblia.model.criteria.DireccionCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAOImpl implements DireccionDAO {

    private final Logger logger = LogManager.getLogger(DireccionDAO.class.getName());
    private final String select = " SELECT ID, DIRECCION, NUMERO, PUERTA, PISO, COD_POSTAL, ID_PAIS ";

    public DireccionDAOImpl() {
    }

    @Override
    public Direccion findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM DIRECCION WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Direccion direccion = null;
                if (resultSet.next()) {
                    direccion = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Direccion {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return direccion;
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
    public List<Direccion> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Direccion> direccions = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM DIRECCION ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Direccion direccion;

                while (resultSet.next()) {
                    direccion = loadNext(resultSet);
                    direccions.add(direccion);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return direccions;
    }

    @Override
    public List<Direccion> findBy(Connection connection, DireccionCriteria direccionCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", direccionCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Direccion> direccions = new ArrayList<>();
        if (connection != null && direccionCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM DIRECCION ");

                boolean first = true;

                if (direccionCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (direccionCriteria.getDireccion() != null) {
                    DAOUtils.addClause(query, first, " DIRECCION = ? ");
                    first = false;
                }
                if (direccionCriteria.getNumero() != null) {
                    DAOUtils.addClause(query, first, " NUMERO = ? ");
                    first = false;
                }
                if (direccionCriteria.getPuerta() != null) {
                    DAOUtils.addClause(query, first, " PUERTA = ? ");
                    first = false;
                }
                if (direccionCriteria.getPiso() != null) {
                    DAOUtils.addClause(query, first, " PISO = ? ");
                    first = false;
                }
                if (direccionCriteria.getCodPostal() != null) {
                    DAOUtils.addClause(query, first, " COD_POSTAL = ? ");
                    first = false;
                }
                if (direccionCriteria.getIdPais() != null) {
                    DAOUtils.addClause(query, first, " ID_PAIS = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (direccionCriteria.getId() != null) preparedStatement.setInt(i++, direccionCriteria.getId());
                if (direccionCriteria.getDireccion() != null)
                    preparedStatement.setString(i, direccionCriteria.getDireccion());
                if (direccionCriteria.getNumero() != null)
                    preparedStatement.setString(i, direccionCriteria.getNumero());
                if (direccionCriteria.getPuerta() != null)
                    preparedStatement.setString(i, direccionCriteria.getPuerta());
                if (direccionCriteria.getPiso() != null) preparedStatement.setString(i, direccionCriteria.getPiso());
                if (direccionCriteria.getCodPostal() != null)
                    preparedStatement.setString(i, direccionCriteria.getCodPostal());
                if (direccionCriteria.getIdPais() != null)
                    preparedStatement.setString(i, direccionCriteria.getIdPais());

                resultSet = preparedStatement.executeQuery();

                Direccion direccion;

                while (resultSet.next()) {
                    direccion = loadNext(resultSet);
                    direccions.add(direccion);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return direccions;
    }

    @Override
    public Direccion update(Connection connection, Direccion direccion) {
        if (logger.isDebugEnabled()) logger.debug("Update direccion: {}", direccion);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && direccion != null && direccion.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE DIRECCION ");

                boolean first = true;
                if (direccion.getDireccion() != null) {
                    DAOUtils.addUpdate(query, true, " DIRECCION = ? ");
                    first = false;
                }
                if (direccion.getNumero() != null) {
                    DAOUtils.addUpdate(query, first, " NUMERO = ? ");
                    first = false;
                }
                if (direccion.getPuerta() != null) {
                    DAOUtils.addUpdate(query, first, " PUERTA = ? ");
                    first = false;
                }
                if (direccion.getPiso() != null) {
                    DAOUtils.addUpdate(query, first, " PISO = ? ");
                    first = false;
                }
                if (direccion.getCodPostal() != null) {
                    DAOUtils.addUpdate(query, first, " COD_POSTAL = ? ");
                    first = false;
                }
                if (direccion.getIdPais() != null) {
                    DAOUtils.addUpdate(query, first, " ID_PAIS = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (direccion.getDireccion() != null) preparedStatement.setString(i++, direccion.getDireccion());
                if (direccion.getNumero() != null) preparedStatement.setString(i++, direccion.getNumero());
                if (direccion.getPuerta() != null) preparedStatement.setString(i++, direccion.getPuerta());
                if (direccion.getPiso() != null) preparedStatement.setString(i++, direccion.getPiso());
                if (direccion.getCodPostal() != null) preparedStatement.setString(i++, direccion.getCodPostal());
                if (direccion.getIdPais() != null) preparedStatement.setString(i++, direccion.getIdPais());
                preparedStatement.setInt(i, direccion.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Mesa no actualizada");
                    return direccion;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de mesa duplicado");
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
    public Direccion create(Connection connection, Direccion direccion) {
        if (logger.isDebugEnabled()) logger.debug("Create mesa: {}", direccion);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (direccion != null && direccion.getDireccion() != null && direccion.getNumero() != null
                && direccion.getPuerta() != null && direccion.getPiso() != null && direccion.getCodPostal() != null
                && direccion.getIdPais() != null) {
            try {
                String query = " INSERT INTO DIRECCION (DIRECCION, NUMERO, PUERTA, PISO, COD_POSTAL, ID_PAIS) values (?,?,?,?,?,?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setString(i++, direccion.getDireccion());
                preparedStatement.setString(i++, direccion.getNumero());
                preparedStatement.setString(i++, direccion.getPuerta());
                preparedStatement.setString(i++, direccion.getPiso());
                preparedStatement.setString(i++, direccion.getCodPostal());
                preparedStatement.setString(i++, direccion.getIdPais());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Direccion'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    direccion.setId(pk);
                    return direccion;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Direccion loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Direccion mesa = new Direccion();
        mesa.setId(resultSet.getInt(i++));
        mesa.setDireccion(resultSet.getString(i++));
        mesa.setNumero(resultSet.getString(i++));
        mesa.setPuerta(resultSet.getString(i++));
        mesa.setPuerta(resultSet.getString(i++));
        mesa.setCodPostal(resultSet.getString(i++));
        mesa.setIdPais(resultSet.getString(i++));
        return mesa;
    }
}
