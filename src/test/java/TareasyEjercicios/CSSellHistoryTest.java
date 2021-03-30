package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class CSSellHistoryTest {
    static WebDriver driver;
    static WebElement fieldUsername = null, fieldPassword = null, buttonLogin = null,
            registerSell = null, fieldQuantity = null, dataTableShares = null,
            dataTablePrice = null, dataTableTotal = null, registerHistory = null, dataTableSymbol = null,
            dataTableName = null,dataTableTOTAL = null, dataTableTransacted = null, buttonSell = null, registerFinance = null;
    static Select dropDownListElement = null;

    public static void main(String[] args) {
        //Abrir Navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar




        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX", restQty = "10";
        String[] dataTableBefore, dataTableAfter, dataTableHistory;


        iniciarLogin(username,password);
        int quantitySharesBefore = obtenerCantidad(symbolName);
        realizarVenta(symbolName,restQty,quantitySharesBefore);
        int quantitySharesAfter = obtenerCantidad(symbolName);
        if((quantitySharesBefore-Integer.parseInt(restQty)) == quantitySharesAfter)
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

    private static void realizarVenta(String symbolName, String restQty, int qtySharesBefore) {

        registerSell = driver.findElement(By.cssSelector("[href='/sell']"));
        registerSell.click();

        dropDownListElement = new Select(driver.findElement(By.cssSelector("[name='symbol']")));
        dropDownListElement.selectByVisibleText(symbolName);

        fieldQuantity = driver.findElement(By.cssSelector("[name='qty']"));
        buttonSell = driver.findElement(By.cssSelector(".btn"));

        if((qtySharesBefore-Integer.parseInt(restQty)) <= 0){
            System.out.println("No puedes restar esta cantidad, sobrepasas el numero de acciones que tienes");
            registerFinance =driver.findElement(By.xpath("//span[contains(.,'Finance')]"));
            registerFinance.click();
        }
        else{
            fieldQuantity.sendKeys(restQty);
            buttonSell.click();
        }
    }

    private static void iniciarLogin(String username, String password) {
        fieldUsername = driver.findElement(By.cssSelector("[name='username']"));
        fieldPassword = driver.findElement(By.cssSelector("[name='password']"));
        buttonLogin = driver.findElement(By.cssSelector(".btn"));

        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        buttonLogin.click();
    }
}
