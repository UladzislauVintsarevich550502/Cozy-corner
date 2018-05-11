package bsuir.vintsarevich.buisness.stock.service;

import bsuir.vintsarevich.entity.Stock;
import bsuir.vintsarevich.exception.service.ServiceException;

import java.util.List;

public interface IStockService {
    List<Stock> getAllStocks() throws ServiceException;

    boolean addStock(Integer productId, Integer percent, String date) throws ServiceException;

    boolean deleteStock(Integer id) throws ServiceException;

    boolean changeStockPercent(Integer id, Integer percent) throws ServiceException;

    boolean changeStockDate(Integer id, String date) throws ServiceException;

    Stock getStockDateByProductId(Integer id) throws  ServiceException;
}
