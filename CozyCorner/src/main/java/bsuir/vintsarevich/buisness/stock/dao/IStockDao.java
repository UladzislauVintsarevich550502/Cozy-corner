package bsuir.vintsarevich.buisness.stock.dao;

import bsuir.vintsarevich.entity.Stock;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IStockDao {
    List<Stock> getAllStocks() throws DaoException;

    boolean addStock(Stock stock) throws DaoException;

    boolean deleteStock(Integer id) throws DaoException;

    boolean changeStockPercent(Integer id, Integer percent) throws DaoException;

    boolean changeStockDate(Integer id, String date) throws DaoException;

    Stock getStockDateByProductId(Integer id) throws  DaoException;
}
