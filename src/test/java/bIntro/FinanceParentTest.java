package bIntro;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinanceParentTest {

    protected static WebDriver driver;
    protected static WebElement registerLink;
    protected static WebElement usernameField;
    protected static WebElement passwordField;
    protected static WebElement registerButton;
    protected static WebElement loginButton;
    protected static WebElement symbolValueTable;
    protected static WebElement nameValueTable;
    protected static WebElement sharesValueTable;
    protected static WebElement priceValueTable;
    protected static WebElement totalValueTable;
    protected static double castGetTextDoubleValue;
    private static double totalDefaultValue = 10000.00;
    private  static String symbolDefaultValue = "CASH";
    protected static WebElement financeLink;
    protected static WebElement quoteLink;
    protected static WebElement buyLink;
    protected static WebElement sellLink;
    protected static WebElement historyLink;
    protected static WebElement logoutLink;
    protected static WebElement quantityField;
    protected static WebElement symbolField;
    protected static WebElement buyButton;
    protected static WebElement sellButton;
    protected static WebElement dropDownSymbolElement;
    protected  static WebDriverWait explicitWait;

    protected static void navegarPaginaPrincipal(String browser, String url) {
        switch (browser) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            case "InternetExplorer":
                driver = new InternetExplorerDriver();
                break;
            case "Opera":
                driver = new OperaDriver();
                break;
        }
        driver.get(url);
    }

    protected static void validarPantallaLogin() throws Exception {
        try{
            usernameField = driver.findElement(By.cssSelector("[name = 'username']"));
            passwordField = driver.findElement(By.cssSelector("[name = 'password']"));
            loginButton = driver.findElement(By.cssSelector(".btn"));

            if (usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed())
                System.out.println("All the elements in the login screen are visible.");
            else
                throw new Exception("Sorry but one element is not visible in the login screen.");
        }

        catch(NoSuchElementException ex){
            throw new Exception("One of the elements is not present in the page.");
        }
    }

    protected static void realizarLogin(String user, String pass) {
        usernameField.sendKeys(user);
        passwordField.sendKeys(pass);
        loginButton.click();
    }

    protected static void validarPantallaPrincipal() throws Exception {
        try{
            financeLink = driver.findElement(By.cssSelector(".navbar-brand"));
            quoteLink = driver.findElement(By.cssSelector("[href='/quote']"));
            buyLink = driver.findElement(By.cssSelector("[href='/buy']"));
            sellLink = driver.findElement(By.cssSelector("[href='/sell']"));
            historyLink = driver.findElement(By.cssSelector("[href='/history']"));
            logoutLink = driver.findElement(By.cssSelector("[href='/logout']"));

            if (financeLink.isDisplayed() && quoteLink.isDisplayed() && buyLink.isDisplayed() && sellLink.isDisplayed() && historyLink.isDisplayed()
            && logoutLink.isDisplayed())
                System.out.println("All the elements in the main screen are visible.");

            else
                throw new Exception("Sorry but one element is not visible in the login screen.");
        }
        catch(NoSuchElementException ex){
            throw new Exception("One of the elements is not present on the page.");
        }
    }

    protected static void validarUsuarioSoloCash() throws Exception {
        try {
            String symbolValue;

            symbolValueTable = driver.findElement(By.xpath("//tr/td[contains(.,'CASH')]"));
            totalValueTable = driver.findElement(By.xpath("//tr[1]/td[last()]"));

            if (symbolValueTable.isDisplayed() && totalValueTable.isDisplayed()){
                System.out.println("All the elements in the main screen are visible.");
                symbolValue = symbolValueTable.getText();
                castGetTextDoubleValue = Double.parseDouble(totalValueTable.getText());

                if (symbolValue.equals(symbolDefaultValue) && castGetTextDoubleValue == totalDefaultValue)
                    System.out.println("The symbol and the total main fields have a default value.");
                else
                    System.out.println("The symbol and the total main fields have not a default value.");
            }
        }

        catch (NoSuchElementException ex){
            throw new Exception("One of the elements is not present on the page.");
        }
    }

    protected static void clickSell() {
        sellLink.click();
    }

    protected static void validarSymbolInvisible() throws Exception {
        try {
            dropDownSymbolElement = driver.findElement(By.cssSelector("[name='symbol']"));

            if (dropDownSymbolElement.isDisplayed())
                System.out.println("There is an error, the object must not be visible.");
            else
                System.out.println("The object is hidden, you can proceed with the validation.");
        }
        catch (NoSuchElementException ex){
            throw new Exception("The element is not present on the page.");
        }
    }

    protected static void validarQtyInvisible() throws Exception {
        try {
            quantityField = driver.findElement(By.cssSelector("[name='qty']"));

            if (quantityField.isDisplayed())
                System.out.println("There is an error, the object must not be visible.");
            else
                System.out.println("The object is hidden, you can proceed with the validation.");
        }
        catch (NoSuchElementException ex){
            throw new Exception("The element is not present on the page.");
        }
    }

    protected static void validarBotonSellInvisible() throws Exception{
        try {
            sellButton = driver.findElement(By.cssSelector(".btn"));

            if (sellButton.isDisplayed())
                System.out.println("There is an error, the object must not be visible.");
            else
                System.out.println("The object is hidden, you can proceed with the validation.");
        }
        catch (NoSuchElementException ex){
            throw new Exception("The element is not present on the page.");
        }
    }

    protected static void validarLeyendaSell() throws Exception {
        WebElement nothingToSellLB = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container")));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(nothingToSellLB,"You have nothing to sell"));
    }

    protected static void validarErrorLogin(){
        WebElement errorImage;
        explicitWait = new WebDriverWait(driver,5);
        try{
            errorImage = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.border")));
            explicitWait.until(ExpectedConditions.attributeContains(errorImage,"src","invalid-username"));
            System.out.println("The error image was visualize correctly.");

            //explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),5));
        }catch (TimeoutException te){
            System.out.println("The image was not visualise correctly.");
        }
    }
}