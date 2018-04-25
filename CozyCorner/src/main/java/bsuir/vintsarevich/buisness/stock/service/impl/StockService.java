package bsuir.vintsarevich.buisness.stock.service.impl;

import bsuir.vintsarevich.buisness.product.service.impl.ProductService;
import bsuir.vintsarevich.buisness.stock.dao.IStockDao;
import bsuir.vintsarevich.buisness.stock.service.IStockService;
import bsuir.vintsarevich.entity.Stock;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class StockService implements IStockService {
    private static Logger LOGGER = Logger.getLogger(ProductService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Stock> getAllStocks() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Stock Service: Start get all stocks");
        try {
            LOGGER.log(Level.DEBUG, "Stock Service: Finish get all stocks");
            return daoFactory.getStockDao().getAllStocks();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean addStock(Integer productId, Integer percent, String date) throws ServiceException {
        LOGGER.log(Level.DEBUG, "StockService: addStock start");
        Stock stock = new Stock();
        IStockDao stockDao = daoFactory.getStockDao();
        try {
            Validator.matchPercent(percent);
            stock.setProductId(productId);
            stock.setPercent(percent);
            stock.setDate(date);
            LOGGER.log(Level.DEBUG, "StockService: addStock finish");
            return stockDao.addStock(stock);

        } catch (ValidatorException | NumberFormatException | DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean deleteStock(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Stock Service: Delete stock start");
        try {
            LOGGER.log(Level.DEBUG, "Stock Service: finish delete stock");
            return daoFactory.getStockDao().deleteStock(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean changeStockPercent(Integer id, Integer percent) throws ServiceException {
        LOGGER.log(Level.DEBUG, "StockService: addStock start");
        IStockDao stockDao = daoFactory.getStockDao();
        try {
            Validator.matchPercent(percent);
            LOGGER.log(Level.DEBUG, "StockService: addStock finish");
            return stockDao.changeStockPercent(id, percent);

        } catch (ValidatorException | NumberFormatException | DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean changeStockDate(Integer id, String date) throws ServiceException {
        LOGGER.log(Level.DEBUG, "StockService: addStock start");
        IStockDao stockDao = daoFactory.getStockDao();
        try {
            LOGGER.log(Level.DEBUG, "StockService: addStock finish");
            return stockDao.changeStockDate(id, date);

        } catch (NumberFormatException | DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Stock getStockDateByProductId(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "StockService: start get stock by ID");
        try {
            LOGGER.log(Level.DEBUG, "StockService: finish get stock by ID");
            return daoFactory.getStockDao().getStockDateByProductId(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
