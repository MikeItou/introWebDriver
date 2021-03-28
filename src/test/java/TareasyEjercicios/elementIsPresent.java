package TareasyEjercicios;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import sun.jvm.hotspot.oops.ExceptionTableElement;

public class elementIsPresent {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        WebElement fieldUsername = null, fieldPassword = null, buttonLogin = null,
                dataTableSymbol = null, dataTableName = null, dataTableShares = null,
                dataTablePrice = null, dataTableTOTAL = null;

        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX",
                restQty = "10";
        String[] dataTableBefore, dataTableAfter, dataTableHistory;

        iniciarLogin(username,password, symbolName, fieldUsername,fieldPassword,buttonLogin);
        dataTableBefore = obtenerDatos(symbolName, dataTableSymbol, dataTableName, dataTableShares,dataTablePrice,dataTableTOTAL);
    }

    private static String[] obtenerDatos(String symbolName, WebElement ...screenElements) {
        String[] elementosExtraccion = new String[5];

        for (int i = 0; i < screenElements.length; i++) {
            try{
                screenElements[i] = driver.findElement(By.xpath("//tr[contains(.,'"+symbolName+"')]//td["+(i+1)+"]"));
            }
            catch (Exception a){
                for (int j = 0; j < screenElements.length; j++) {
                    elementosExtraccion[j] = "0";
                }
                break;
            }
            elementosExtraccion[i]=screenElements[i].getText();
        }

        /*for (int i = 0; i < elementosExtraccion.length; i++) {
            System.out.println("posicion del arreglo " +i+ " tiene el valor: " + elementosExtraccion[i]);
        }*/

        return elementosExtraccion;
    }

    private static void iniciarLogin(String username, String password, String symbolName, WebElement ... screenElements) {
        screenElements[0] = driver.findElement(By.cssSelector("[name='username']"));
        screenElements[1] = driver.findElement(By.cssSelector("[name='password']"));
        screenElements[2] = driver.findElement(By.cssSelector(".btn"));

        screenElements[0].sendKeys(username);
        screenElements[1].sendKeys(password);
        screenElements[2].click();

    }
}
