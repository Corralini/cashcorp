package com.gamblia.dao.impl;

import com.gamblia.dao.spi.CuentaDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Cuenta;
import com.gamblia.model.criteria.CuentaCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOImpl implements CuentaDAO {

    private final Logger logger = LogManager.getLogger(CuentaDAOImpl.class.getName());
    private final String select = " SELECT ID, ID_PAIS, DC_IBAN, ENTIDAD, OFICINA, DC, CUENTA, SALDO, FECHA_CREACION ";

    public CuentaDAOImpl() {
    }

    @Override
    public Cuenta findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM CUENTA WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Cuenta cuenta = null;
                if (resultSet.next()) {
                    cuenta = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Cuenta {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return cuenta;
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
    public List<Cuenta> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Cuenta> cuentas = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM CUENTA ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Cuenta cuenta;

                while (resultSet.next()) {
                    cuenta = loadNext(resultSet);
                    cuentas.add(cuenta);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return cuentas;
    }

    @Override
    public List<Cuenta> findBy(Connection connection, CuentaCriteria cuentaCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", cuentaCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Cuenta> cuentas = new ArrayList<>();
        if (connection != null && cuentaCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM CUENTA ");

                boolean first = true;

                if (cuentaCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (cuentaCriteria.getIdPais() != null) {
                    DAOUtils.addClause(query, first, " ID_PAIS = ? ");
                    first = false;
                }
                if (cuentaCriteria.getDcIban() != null) {
                    DAOUtils.addClause(query, first, " DC_IBAN = ? ");
                    first = false;
                }
                if (cuentaCriteria.getEntidad() != null) {
                    DAOUtils.addClause(query, first, " ENTIDAD = ? ");
                    first = false;
                }
                if (cuentaCriteria.getOficina() != null) {
                    DAOUtils.addClause(query, first, " OFICINA = ? ");
                    first = false;
                }
                if (cuentaCriteria.getDc() != null) {
                    DAOUtils.addClause(query, first, " DC = ? ");
                    first = false;
                }
                if (cuentaCriteria.getCuenta() != null) {
                    DAOUtils.addClause(query, first, " CUENTA = ? ");
                    first = false;
                }
                if (cuentaCriteria.getSaldo() != null) {
                    DAOUtils.addClause(query, first, " SALDO = ? ");
                    first = false;
                }
                if (cuentaCriteria.getFechaCreacion() != null) {
                    DAOUtils.addClause(query, first, " FECHA_CREACION = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (cuentaCriteria.getId() != null) preparedStatement.setInt(i++, cuentaCriteria.getId());
                if (cuentaCriteria.getIdPais() != null) preparedStatement.setString(i++, cuentaCriteria.getIdPais());
                if (cuentaCriteria.getDcIban() != null) preparedStatement.setInt(i++, cuentaCriteria.getDcIban());
                if (cuentaCriteria.getEntidad() != null) preparedStatement.setInt(i++, cuentaCriteria.getEntidad());
                if (cuentaCriteria.getOficina() != null) preparedStatement.setInt(i++, cuentaCriteria.getOficina());
                if (cuentaCriteria.getDc() != null) preparedStatement.setDouble(i++, cuentaCriteria.getDc());
                if (cuentaCriteria.getCuenta() != null) preparedStatement.setInt(i++, cuentaCriteria.getCuenta());
                if (cuentaCriteria.getSaldo() != null) preparedStatement.setDouble(i++, cuentaCriteria.getSaldo());
                if (cuentaCriteria.getFechaCreacion() != null) preparedStatement.setDate(i,(java.sql.Date) cuentaCriteria.getFechaCreacion());

                resultSet = preparedStatement.executeQuery();

                Cuenta cuenta;

                while (resultSet.next()) {
                    cuenta = loadNext(resultSet);
                    cuentas.add(cuenta);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return cuentas;
    }

    @Override
    public Cuenta update(Connection connection, Cuenta cuenta) {
        if (logger.isDebugEnabled()) logger.debug("Update mesa: {}", cuenta);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && cuenta != null && cuenta.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE MESA ");

                boolean first = true;
                if (cuenta.getIdPais() != null) {
                    DAOUtils.addUpdate(query, true, " ID_PAIS = ? ");
                    first = false;
                }
                if (cuenta.getDcIban() != null) {
                    DAOUtils.addUpdate(query, first, " DC_IBAN = ? ");
                    first = false;
                }
                if (cuenta.getEntidad() != null) {
                    DAOUtils.addUpdate(query, first, " ENTIDAD = ? ");
                    first = false;
                }
                if (cuenta.getOficina() != null) {
                    DAOUtils.addUpdate(query, first, " OFICINA = ? ");
                    first = false;
                }
                if (cuenta.getDc() != null) {
                    DAOUtils.addUpdate(query, first, " DC = ? ");
                    first = false;
                }
                if (cuenta.getCuenta() != null) {
                    DAOUtils.addUpdate(query, first, " CUENTA = ? ");
                    first = false;
                }
                if (cuenta.getSaldo() != null) {
                    DAOUtils.addUpdate(query, first, " SALDO = ? ");
                    first = false;
                }
                if (cuenta.getFechaCreacion() != null) {
                    DAOUtils.addUpdate(query, first, " FECHA_CREACION = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (cuenta.getIdPais() != null) preparedStatement.setString(i++, cuenta.getIdPais());
                if (cuenta.getDcIban() != null) preparedStatement.setInt(i++, cuenta.getDcIban());
                if (cuenta.getEntidad() != null) preparedStatement.setInt(i++, cuenta.getEntidad());
                if (cuenta.getOficina() != null) preparedStatement.setInt(i++, cuenta.getOficina());
                if (cuenta.getDc() != null) preparedStatement.setInt(i++, cuenta.getDc());
                if (cuenta.getCuenta() != null) preparedStatement.setInt(i++, cuenta.getCuenta());
                if (cuenta.getSaldo() != null) preparedStatement.setDouble(i++, cuenta.getSaldo());
                if (cuenta.getFechaCreacion() != null) preparedStatement.setDate(i++, (java.sql.Date) cuenta.getFechaCreacion());
                preparedStatement.setInt(i, cuenta.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Mesa no actualizada");
                    return cuenta;
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
    public Cuenta create(Connection connection, Cuenta cuenta) {
        if (logger.isDebugEnabled()) logger.debug("Create cuenta: {}", cuenta);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        if (cuenta != null && cuenta.getIdPais() != null && cuenta.getDcIban() != null && cuenta.getEntidad() != null
                && cuenta.getOficina() != null && cuenta.getDc() != null && cuenta.getCuenta() != null) {
            try {
                String query = " INSERT INTO CUENTA (ID_PAIS, DC_IBAN, ENTIDAD, OFICINA, DC, CUENTA) values (?,?,?,?,?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setString(i++, cuenta.getIdPais());
                preparedStatement.setInt(i++, cuenta.getDc());
                preparedStatement.setInt(i++, cuenta.getEntidad());
                preparedStatement.setInt(i++,cuenta.getOficina());
                preparedStatement.setInt(i++,cuenta.getDc());
                preparedStatement.setInt(i++,cuenta.getCuenta());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Cuenta'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    cuenta.setId(pk);
                    return cuenta;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Cuenta loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Cuenta cuenta = new Cuenta();
        cuenta.setId(resultSet.getInt(i++));
        cuenta.setIdPais(resultSet.getString(i++));
        cuenta.setDcIban(resultSet.getInt(i++));
        cuenta.setEntidad(resultSet.getInt(i++));
        cuenta.setOficina(resultSet.getInt(i++));
        cuenta.setDc(resultSet.getInt(i++));
        cuenta.setCuenta(resultSet.getInt(i++));
        cuenta.setSaldo(resultSet.getDouble(i++));
        cuenta.setFechaCreacion(resultSet.getDate(i));
        return cuenta;
    }
}
