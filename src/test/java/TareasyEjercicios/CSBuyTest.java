package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CSBuyTest {
    static WebDriver driver;
    static WebElement fieldUsername=null, fieldPassword=null, buttonLoginBuy=null,
            registerBuy=null, fieldSymbol=null, fieldQuantity=null, dataTableShares=null, dataTableSymbol=null,
            dataTableName=null, dataTablePrice=null, dataTableTotal=null, dataTableTotal2=null, dataTableTotal3=null,
            dataTableTOTAL=null,dataTableTransacted=null, registerHistory=null, buttonBuy=null;

    public static void main(String[] args) {
        //Abrir navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar


        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX",quantityValue = "30",
                nameValue = "MagnaChip Semiconductor Corp";

        //Inicicar sesion
        iniciarLogin(username,password);
        int quantityShares = obtenerCantidad(symbolName);
        realizarNuevaCompra(symbolName, quantityValue);
        int quantitySharesAfter = obtenerCantidad(symbolName);
        if((quantityShares + Integer.parseInt(quantityValue)) == quantitySharesAfter)
            System.out.println("Todo bien");
    }

    private static int obtenerCantidad(String symbol) {
        //revisar si el elemento tr existe que contenga el symbol
        try {
            WebElement renglon = driver.findElement(By.xpath("//tr[contains(., '"+ symbol+"')]"));
            WebElement columna = renglon.findElement(By.xpath("./td[3]"));
            int numSharesAntes = Integer.parseInt(columna.getText());
            return numSharesAntes;

        } catch(Exception e) {}
        return 0;
    }

    private static void realizarNuevaCompra(String symbolName, String quantityName) {
        //int randomNumber = new Random().nextInt(100000);


        registerBuy = driver.findElement(By.cssSelector("[href='/buy']"));
        registerBuy.click();

        fieldSymbol = driver.findElement(By.cssSelector("[name='symbol']"));
        fieldQuantity = driver.findElement(By.cssSelector("[name='qty']"));
        buttonBuy = driver.findElement(By.cssSelector(".btn"));
        fieldSymbol.sendKeys(symbolName);
        fieldQuantity.sendKeys(quantityName);
        buttonBuy.click();
    }

    private static void iniciarLogin(String username, String password) {
        fieldUsername = driver.findElement(By.cssSelector("[name='username']"));
        fieldPassword = driver.findElement(By.cssSelector("[name='password']"));
        buttonLoginBuy = driver.findElement(By.cssSelector(".btn"));

        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        buttonLoginBuy.click();
    }
}
