package bIntro;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class FinanceParentTest {

    protected static WebDriver driver;
    protected static WebElement registerLink;
    protected static WebElement usernameField;
    protected static WebElement passwordField;
    protected static WebElement registerButton;
    protected static WebElement loginButton;
    protected static WebElement totalValueTable;
    protected static double castDoubleValue;
    protected static double castGetTextDoubleValue;
    protected static WebElement buyLink;
    protected static WebElement quantityField;
    protected static WebElement symbolField;
    protected static WebElement buyButton;
    protected static WebElement sellLink;

    protected static void setup(String browser, String url) {
        switch (browser){
            case "Chrome":
                driver = new ChromeDriver();
                driver.get(url);
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                driver.get(url);
                break;
            case "InternetExplorer":
                driver = new InternetExplorerDriver();
                driver.get(url);
                break;
            case "Safari":
                driver = new SafariDriver();
                driver.get(url);
                break;
            case "Opera":
                driver = new OperaDriver();
                driver.get(url);
                break;
            case "Edge":
                driver = new EdgeDriver();
                driver.get(url);
                break;
        }
    }

    protected static void clickRegisterLink() {
        try{
            registerLink = driver.findElement(By.cssSelector("href='/register'"));
            registerLink.click();
        }catch (Exception registerLinkError){
            System.out.println("El elemento en la página no fue encontrado");
        }
    }

    protected static void inputUsernamePassword(String username, String password) {

        try{
            usernameField = driver.findElement(By.cssSelector("[name='username']"));
            usernameField.sendKeys(username);
            try {
                passwordField = driver.findElement(By.cssSelector("[name='password']"));
                passwordField.sendKeys(password);
                try {
                    passwordField = driver.findElement(By.cssSelector("[name='repeat_password']"));
                    passwordField.sendKeys(password);
                    try{
                        registerButton = driver.findElement(By.cssSelector(".btn"));
                        registerButton.click();
                    }catch (Exception registerButtonError){
                        System.out.println("El elemento buton de registro no ha sido encontrado");
                    }
                }catch (Exception repeatPasswordFieldError){
                    System.out.println("El elemento campo repeat password no ha sido encontrado");
                }
            }catch (Exception passwordFieldError){
                System.out.println("El elemento campo de password no ha sido encontrado");
            }
        }catch (Exception usernameFieldError){
            System.out.println("El elemento campo de usuario no ha sido encontrado");
        }
    }

    protected static void login(String username, String password) {
        try {
            usernameField = driver.findElement(By.cssSelector("[name='username']"));
            usernameField.sendKeys(username);
            try {
                passwordField = driver.findElement(By.cssSelector("[name='password']"));
                passwordField.sendKeys(password);
                try {
                    loginButton = driver.findElement(By.cssSelector(".btn"));
                    loginButton.click();
                }catch (Exception loginButtonError){
                    System.out.println("El boton de login no fue encontrado");
                }
            }catch (Exception passwordFieldError){
                System.out.println("El campo de password no fue encontrado");
            }
        }catch (Exception usernameFieldError){
            System.out.println("El campo de username no fue encontrado");
        }
    }

    protected static void validateAccountCash(String totalValue) {
        try{
            totalValueTable = driver.findElement(By.xpath("//tr[contains(.,'CASH')]//td[last()]"));
            castGetTextDoubleValue = Double.parseDouble(totalValueTable.getText());
            castDoubleValue = Double.parseDouble(totalValue);
            if (castDoubleValue == castGetTextDoubleValue){
                System.out.println("Los valores de tu cuenta coinciden");
            }
            else{
                System.out.println("Ha habido un error, los valores de tu cuenta no coinciden con los valores almacenados previamente");
            }

        }catch (Exception totalValueTableError){
            System.out.println("El valor buscado no fue encontrado en la página");
        }
    }

    protected static void clickBuyLink() {
        try {
            buyLink = driver.findElement(By.cssSelector("[href='/buy']"));
            buyLink.click();
        }catch (Exception buyLinkError){
            System.out.println("El link que buscas no se encuentra en la página");
        }
    }

    protected static void buyShares(String symbol, String quantity) {
        try{
            symbolField = driver.findElement(By.cssSelector("[name='symbol']"));
            symbolField.sendKeys(symbol);
            try{
                quantityField =driver.findElement(By.cssSelector("[name='qty']"));
                quantityField.sendKeys(quantity);
                try{
                    buyButton = driver.findElement(By.cssSelector(".btn"));
                    buyButton.click();
                }catch (Exception buyButtonError){
                    System.out.println("No se encontro el boton buy en la pagina");
                }
            }catch (Exception quantityFieldError){
                System.out.println("No se encontro el campo quantity en la pagina");
            }

        }catch (Exception symbolFieldError){
            System.out.println("No se encontro el campo symbol en la pagina");
        }
    }

    protected static void validateShares(String amzn, String i) {

    }

    protected static void clickSellLink() {
        try {
            sellLink = driver.findElement(By.cssSelector("[href='/sell']"));
            sellLink.click();
        }catch (Exception buyLinkError){
            System.out.println("El link que buscas no se encuentra en la página");
        }
    }

    protected static void sellShares(String amzn, String i) {
    }
}