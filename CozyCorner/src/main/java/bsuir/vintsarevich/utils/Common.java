package bsuir.vintsarevich.utils;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.entity.*;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class Common created to executing common function for command Index, FindByType and SearchProduct
 */
public class Common {
    private static final int NUMBER_OF_PRODUCT_ON_PAGE = 6;

    /**
     * @param request
     * @throws ServiceException
     */
    public static void setReview(HttpServletRequest request) throws ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IReviewService reviewService = serviceFactory.getReviewService();
        IClientService clientService = serviceFactory.getClientService();
        List<Review> reviews = reviewService.getAllReviews();
        Collections.reverse(reviews);
        for (Review review : reviews) {
            Client client = clientService.getClientById(review.getClientId());
            review.setClientName(client.getName());
            review.setClientSurname(client.getSurname());
        }
        if (reviews.size() == 0) {
            request.setAttribute("reviews", null);
        } else {
            request.setAttribute("reviews", reviews);
        }
    }

    /**
     * @param request
     * @param allProducts
     * @throws ServiceException
     */
    public static void calculatePageNumber(HttpServletRequest request, List<Product> allProducts) throws ServiceException {
        if (allProducts.size() == 0) {
            if (SessionElements.getLocale(request).equals("ru")) {
                request.setAttribute(AttributeParameterName.PRODUCT_NOT_FIND.getValue(), "Ничего не найдено");
            } else {
                request.setAttribute(AttributeParameterName.PRODUCT_NOT_FIND.getValue(), "Nothing found");
            }
            request.getSession().setAttribute("pageCount", 0);
        } else {
            Collections.reverse(allProducts);
            int pageCount;
            if (allProducts.size() % NUMBER_OF_PRODUCT_ON_PAGE == 0) {
                pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE;
            } else {
                pageCount = allProducts.size() / NUMBER_OF_PRODUCT_ON_PAGE + 1;
            }
            if (request.getSession().getAttribute("currentPage") == null ||
                    (Integer) request.getSession().getAttribute("currentPage") == 0) {
                request.getSession().setAttribute("currentPage", 1);
            }
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            int currentPage = (Integer) request.getSession().getAttribute("currentPage");
            List<Product> pageProducts = new ArrayList<>();
            for (int i = (currentPage - 1) * NUMBER_OF_PRODUCT_ON_PAGE; i < currentPage * NUMBER_OF_PRODUCT_ON_PAGE
                    && i < allProducts.size(); i++) {
                allProducts.get(i).setType(typeConverter(allProducts.get(i).getType()));
                Stock stock = serviceFactory.getStockService().getStockDateByProductId(allProducts.get(i).getId());
                if (stock != null) {
                    allProducts.get(i).setPercent(stock.getPercent());
                    allProducts.get(i).setStockDate(stock.getDate());
                } else {
                    allProducts.get(i).setPercent(0);
                }
                pageProducts.add(allProducts.get(i));
            }
            request.setAttribute("products", pageProducts);
            request.getSession().setAttribute("pageCount", pageCount);
        }
    }

    /**
     * @param type
     * @return String
     */
    public static String typeConverter(String type) {
        if (type.equals("soda") || type.equals("water") || type.equals("soup") || type.equals("hotDrink") || type.equals("juice")) {
            return "volume";
        }
        return "weight";
    }

    public static Double getCommonCost(HttpServletRequest request) {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            Integer clientId = ((User) request.getSession().getAttribute(AttributeParameterName.USER.getValue())).getId();
            Integer orderId = serviceFactory.getOrderService().getOrderIdByClientId(clientId);
            List<Product> products = serviceFactory.getProducteService().getProductByOrderId(orderId);
            Double commonCost = 0.0;
            for (Product product : products) {
                Double cost = product.getCost();
                Stock stock = serviceFactory.getStockService().getStockDateByProductId(product.getId());
                if (stock != null) {
                    cost *= (1 - (double) stock.getPercent() / 100);
                }
                commonCost += Math.rint(100 * (cost * product.getNumber())) / 100;
            }
            return commonCost;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
