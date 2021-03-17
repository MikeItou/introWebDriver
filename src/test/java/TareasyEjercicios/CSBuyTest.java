package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CSBuyTest {
    static WebDriver driver;

    public static void main(String[] args) {
        //Abrir navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar
        WebElement fieldUsername=null, fieldPassword=null, buttonLoginBuy=null,
                   registerBuy=null, fieldSymbol=null, fieldQuantity=null, dataTable=null;

        String username = "Juanito.Banana", password = "Testing1234";

        //Inicicar sesion
        iniciarLogin(username,password, fieldUsername,fieldPassword,buttonLoginBuy);
        realizarCompra(registerBuy,fieldSymbol,fieldQuantity,buttonLoginBuy);

    }

    private static void realizarCompra(WebElement ... screenElements) {

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
