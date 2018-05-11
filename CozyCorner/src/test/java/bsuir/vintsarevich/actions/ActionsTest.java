package bsuir.vintsarevich.actions;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class ActionsTest {
    private WebDriver driver;
    private String adminActionClass = "admin_action";
    private String addToBasketId = "add-button-to-basket";
    private String url = "http://localhost:8087";

    @BeforeTest
    public void before()
    {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @AfterMethod
    public void after()
    {
        try {
            driver.get(url);
            driver.findElements(By.className("cd-exit")).get(0).click();
        }
        catch (Exception ignored){

        }
    }

    private void signIn(String login, String password){
        driver.get(url);
        driver.findElements(By.className("cd-signin")).get(0).click();
        driver.findElement(By.id("signin-login")).sendKeys(login);
        driver.findElement(By.id("signin-password")).sendKeys(password);
        driver.findElement(By.id("login-form")).submit();
    }

    @Test
    public void checkAdminActions() {
        signIn("Admin","Qwe123");
        Assert.assertTrue(!driver.findElements(By.className("admin_action")).isEmpty());
        Assert.assertTrue(driver.findElements(By.id("add-button-to-basket")).isEmpty());
    }

    @Test
    public void checkNonAdminActions() {
        signIn("User","Qwe123");
        Assert.assertTrue(driver.findElements(By.className(adminActionClass)).isEmpty());
        Assert.assertTrue(!driver.findElements(By.id(addToBasketId)).isEmpty());
    }

    @Test
    public void checkUnsignedActions() {
        Assert.assertTrue(driver.findElements(By.className(adminActionClass)).isEmpty());
        Assert.assertTrue(driver.findElements(By.id(addToBasketId)).isEmpty());
    }
}
