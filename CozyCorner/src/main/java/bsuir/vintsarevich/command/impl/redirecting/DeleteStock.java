package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.stock.service.IStockService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStock implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete stock");
        try {
            IStockService stockService = ServiceFactory.getInstance().getStockService();
            Integer productId = Integer.valueOf(request.getParameter(AttributeParameterName.PRODUCT_ID.getValue()));
            stockService.deleteStock(productId);
            response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete stock");
        return jspPageName.getPath();
    }
}
