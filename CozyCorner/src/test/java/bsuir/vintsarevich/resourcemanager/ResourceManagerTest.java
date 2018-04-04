package bsuir.vintsarevich.resourcemanager;

import bsuir.vintsarevich.connectionpool.DBResourceManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.MissingResourceException;

public class ResourceManagerTest {
    private DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    private final String correctUser = "root";
    private final String correctPassword = "root";
    private final String correctUrl = "jdbc:mysql://localhost:3306/epamcafe";

    @Test
    public void correctGetUser() {
        Assert.assertEquals(dbResourceManager.getValue("db.user"), correctUser);
    }

    @Test
    public void correctGetPassword() {
        Assert.assertEquals(dbResourceManager.getValue("db.password"), correctPassword);
    }

    @Test
    public void correctGetUrl() {
        Assert.assertEquals(dbResourceManager.getValue("db.url"), correctUrl);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void getResourceByNullValue() throws NullPointerException{
        Assert.assertEquals(dbResourceManager.getValue(null), "");
    }

    @Test (expectedExceptions = MissingResourceException.class)
    public void getResourceByEmptyStringValue() throws MissingResourceException{
        Assert.assertEquals(dbResourceManager.getValue(""), "");
    }

    @Test (expectedExceptions = MissingResourceException.class)
    public void getUnknownResource() throws MissingResourceException{
        Assert.assertEquals(dbResourceManager.getValue("db.SomethingUnexpected"), "");
    }
}
