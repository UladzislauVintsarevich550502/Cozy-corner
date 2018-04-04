package bsuir.vintsarevich.poolconnection;

import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.connectionpool.DBParametr;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPoolTest {

    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ResourceBundle bundle = ResourceBundle.getBundle("dao/db_test");
    private ResourceBundle wrongBundle = ResourceBundle.getBundle("dao/db_wrong");


    @Test(expectedExceptions = SQLException.class)
    public void exceptionWithGetConnection() throws SQLException{
        this.url = wrongBundle.getString(DBParametr.DB_URL);
        this.user = wrongBundle.getString(DBParametr.DB_USER);
        this.password = wrongBundle.getString(DBParametr.DB_PASSWORD);
        DriverManager.getConnection(url, user, password);
    }

    @Test
    public void correctGetConnection() throws SQLException{
        this.url = bundle.getString(DBParametr.DB_URL);
        this.user = bundle.getString(DBParametr.DB_USER);
        this.password = bundle.getString(DBParametr.DB_PASSWORD);
        DriverManager.getConnection(url, user, password);
    }

    @Test
    public void closeConnectionWithoutOpening() throws ConnectionException{
        ConnectionPool.getInstance().releasePool();
    }

    @Test
    public void closeConnectionWithOpening() throws ConnectionException, SQLException{
        correctGetConnection();
        ConnectionPool.getInstance().releasePool();
    }


}
