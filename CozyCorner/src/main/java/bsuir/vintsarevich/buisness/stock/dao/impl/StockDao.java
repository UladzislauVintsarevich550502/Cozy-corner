package bsuir.vintsarevich.buisness.stock.dao.impl;

import bsuir.vintsarevich.buisness.stock.dao.IStockDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Stock;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDao implements IStockDao {
    private static final Logger LOGGER = Logger.getLogger(StockDao.class);
    private static String ADD_STOCK = "INSERT INTO corner.stock (productId, percent, date) VALUES(?,?,?)";
    private static String DELETE_STOCK = "DELETE FROM corner.stock WHERE productId=?";
    private static String CHANGE_STOCK_PERCENT = "UPDATE corner.stock SET corner.stock.percent=? WHERE corner.stock.productId=?";
    private static String CHANGE_STOCK_DATE = "UPDATE corner.stock SET corner.stock.date=? WHERE corner.stock.productId=?";
    private static String GET_STOCK = "SELECT * FROM corner.stock WHERE corner.stock.productId=?";
    private static String GET_ALL_STOCKS = "SELECT * FROM corner.stock";
    private ConnectionPool connectionPool;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;


    @Override
    public List<Stock> getAllStocks() throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get all products");
        List<Stock> stocks;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_STOCKS);
            resultSet = statement.executeQuery();
            stocks = new ArrayList<>();
            while (resultSet.next()) {
                stocks.add(createStockByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: Finish get all products");
        return stocks;
    }

    @Override
    public boolean addStock(Stock stock) throws DaoException {
        LOGGER.log(Level.DEBUG, "Stock DAO: Add stock start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_STOCK);
            statement.setInt(1, stock.getProductId());
            statement.setInt(2, stock.getPercent());
            statement.setString(3, stock.getDate());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add stock success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add stock finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Stock DAO: Add stock finish");
        }
    }

    @Override
    public boolean deleteStock(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Stock DAO: Delete stock start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_STOCK);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete stock success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete stock finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Stock DAO: Delete stock finish");
        }
    }

    @Override
    public boolean changeStockPercent(Integer id, Integer percent) throws DaoException {
        LOGGER.log(Level.DEBUG, "Stock DAO: Edit stock start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_STOCK_PERCENT);
            statement.setInt(1, percent);
            statement.setInt(2, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Edit Stock Percent success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Edit Stock Percent not success");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Stock DAO: Edit stock finish");
        }
    }

    @Override
    public boolean changeStockDate(Integer id, String date) throws DaoException {
        LOGGER.log(Level.DEBUG, "Stock DAO: Edit stock start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_STOCK_DATE);
            statement.setString(1, date);
            statement.setInt(2, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Change Stock Date success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Change Stock Date not success");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Stock DAO: Edit stock finish");
        }
    }

    @Override
    public Stock getStockDateByProductId(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "product Stock: Start get stock by ID");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_STOCK);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createStockByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product Stock: get stock by ID");
        return null;
    }



    private Stock createStockByResultSet(ResultSet resultSet) throws DaoException {
        Stock stock = new Stock();
        try {
            stock.setProductId(resultSet.getInt("productId"));
            stock.setPercent(resultSet.getInt("percent"));
            stock.setDate(resultSet.getString("date"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return stock;
    }
}
