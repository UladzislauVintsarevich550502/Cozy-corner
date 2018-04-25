package bsuir.vintsarevich.command.impl.forwarding;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Stock;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.Common;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class StockProduct implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(StockProduct.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;
    private String productName;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Stock command start");
        try {
            request.getSession().setAttribute("currentPage", 1);
            setPageProduct(request);
            Common.setReview(request);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.SEARCH_PRODUCT.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Search command finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     * @throws ServiceException
     */
    private void setPageProduct(HttpServletRequest request) throws ServiceException {
        List<Product> allProducts = new ArrayList<>();
        List<Stock> stocks = serviceFactory.getStockService().getAllStocks();
        for (Stock stock : stocks) {
            allProducts.add(serviceFactory.getProducteService().getProductById(stock.getProductId()));
        }
        Common.calculatePageNumber(request, allProducts);
    }
}
