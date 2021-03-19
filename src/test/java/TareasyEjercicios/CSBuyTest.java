package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CSBuyTest {
    static WebDriver driver;

    public static void main(String[] args) {
        //Abrir navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar
        WebElement fieldUsername=null, fieldPassword=null, buttonLoginBuy=null,
                   registerBuy=null, fieldSymbol=null, fieldQuantity=null, dataTableShares=null;

        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX",quantityValue = "2",shareValue,originalQuantityValue=null,originalQV=null;

        //Inicicar sesion
        iniciarLogin(username,password, fieldUsername,fieldPassword,buttonLoginBuy);
        originalQV = realizarNuevaCompra(symbolName, quantityValue,originalQuantityValue , registerBuy,fieldSymbol,fieldQuantity,buttonLoginBuy,dataTableShares);
        shareValue = validarNuevaCompra(dataTableShares);
        System.out.println(originalQV + " " + shareValue);

    }

    private static String validarNuevaCompra(WebElement dataTableShares) {
        dataTableShares = driver.findElement(By.xpath("//tr[contains(. , 'MX')]//td[3]"));
        return dataTableShares.getText();
    }

    private static String realizarNuevaCompra(String symbolName, String quantityName, String originalQuantityValue, WebElement ... screenElements) {
        //int randomNumber = new Random().nextInt(100000);
        screenElements[4] = driver.findElement(By.xpath("//tr[contains(. , 'MX')]//td[3]"));
        if (screenElements[4].isDisplayed()){
            originalQuantityValue = screenElements[4].getText();
        }

        screenElements[0] = driver.findElement(By.cssSelector("[href='/buy']"));
        screenElements[0].click();

        screenElements[1] = driver.findElement(By.cssSelector("[name='symbol']"));
        screenElements[2] = driver.findElement(By.cssSelector("[name='qty']"));
        screenElements[3] = driver.findElement(By.cssSelector(".btn"));
        screenElements[1].sendKeys(symbolName);
        screenElements[2].sendKeys(quantityName);
        screenElements[3].click();

        return originalQuantityValue;
    }

    private static void iniciarLogin(String username, String password, WebElement ... screenelements) {
        screenelements[0] = driver.findElement(By.cssSelector("[name='username']"));
        screenelements[1] = driver.findElement(By.cssSelector("[name='password']"));
        screenelements[2] = driver.findElement(By.cssSelector(".btn"));

        screenelements[0].sendKeys(username);
        screenelements[1].sendKeys(password);
        screenelements[2].click();
    }
}
