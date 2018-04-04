package bsuir.vintsarevich.servicefactory;

import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceFactoryTest {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Test
    public void CheckInnerValues()
    {
        Assert.assertEquals(serviceFactory.getClientService() != null, true);
        Assert.assertEquals(serviceFactory.getProducteService() != null, true);
        Assert.assertEquals(serviceFactory.getOrderService() != null, true);
        Assert.assertEquals(serviceFactory.getAdminService() != null, true);
        Assert.assertEquals(serviceFactory.getStaffService() != null, true);
        Assert.assertEquals(serviceFactory.getAccountService() != null, true);
        Assert.assertEquals(serviceFactory.getOrderProductService() != null, true);
        Assert.assertEquals(serviceFactory.getReviewService() != null, true);
    }
}
