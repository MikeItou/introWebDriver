package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class CSSellHistoryTest {
    static WebDriver driver;

    public static void main(String[] args) {
        //Abrir Navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar
        WebElement fieldUsername = null, fieldPassword = null, buttonLoginSell = null,
                registerSell = null, fieldQuantity = null, dataTableShares = null,
                dataTablePrice = null, dataTableTotal = null, registerHistory = null, dataTableSymbol = null,
                dataTableName = null,dataTableTOTAL = null, dataTableTransacted = null;

        Select dropDownListElement = null;

        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX", restQty = "10";
        //String[] valueTableBeforeSell, valueTableAfterSell,valueTableHistory;
        String[] dataTableBefore, dataTableAfter, dataTableHistory;

        //Inicicar sesion
        //valueTableBeforeSell = iniciarLogin(username,password, symbolName, fieldUsername,fieldPassword,buttonLoginSell, dataTableShares, dataTablePrice,dataTableTotal);
        //realizarVenta(symbolName,restQty,valueTableBeforeSell[0],dropDownListElement, registerSell,fieldQuantity,buttonLoginSell);
        //validarVenta(symbolName, valueTableBeforeSell, restQty, dataTableShares,dataTablePrice,dataTableTotal);
        //valueTableAfterSell = obtenerDatosDespuesVenta(symbolName,dataTableShares,dataTablePrice,dataTableTotal);
        //valueTableHistory = validarHistorial(symbolName,restQty,dataTableSymbol, dataTableShares,dataTablePrice,registerHistory);

        iniciarLogin(username,password, fieldUsername,fieldPassword,buttonLoginSell);
        dataTableBefore = obtenerDatos(symbolName, dataTableSymbol, dataTableName, dataTableShares,dataTablePrice,dataTableTOTAL);
        realizarVenta(symbolName,restQty,dataTableBefore,dropDownListElement, registerSell,fieldQuantity,buttonLoginSell);
        dataTableAfter = obtenerDatos(symbolName, dataTableSymbol, dataTableName, dataTableShares,dataTablePrice,dataTableTOTAL);
        dataTableHistory = validarHistorial(dataTableBefore,dataTableAfter,registerHistory,dataTableSymbol,dataTableShares,dataTablePrice,dataTableTransacted);

    }

    private static String[] obtenerDatos(String symbolName, WebElement ... screenElements) {
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

    /*private static String[] validarHistorial(String symbolName, String restQty, WebElement ... screenElements) {
        String[] elementsTable = new String[3];
        int castRestQty, castSharesValue;

        screenElements[3] = driver.findElement(By.cssSelector("[href='/history']"));
        screenElements[3].click();

        for (int i = 0; i < screenElements.length-1; i++) {
            screenElements[i] = driver.findElement(By.xpath("//tr[last()]//td["+(i+1)+"]"));
            elementsTable[i] = screenElements[i].getText();
        }

        castRestQty = Integer.parseInt(restQty) * -1;
        castSharesValue = Integer.parseInt((elementsTable[1]));

        //System.out.println(symbolName.length() +"="+elementsTable[0].length());
        //System.out.println(castRestQty+castSharesValue);
        if(symbolName.equals(elementsTable[0]) && castRestQty == castSharesValue )
            System.out.println("La validación en el ultimo registro mas reciente del historial es correcta.");

        return elementsTable;
    }*/

    private static String[] validarHistorial(String[] dataTableBefore, String[] dataTableAfter, WebElement ... screenElements) {
        String[] elementosHistorial = new String[4];
        int castSharesBefore, castSharesAfter,castSharesHistory;
        double castPriceBefore, castPriceAfter,castPriceHistory;

        screenElements[0] = driver.findElement(By.cssSelector("[href='/history']"));
        screenElements[0].click();

        for (int i = 1; i < screenElements.length-1; i++) {
            screenElements[i] = driver.findElement(By.xpath("//tr[last()]//td["+i+"]"));
            elementosHistorial[i-1] = screenElements[i].getText();
        }

        castSharesAfter = Integer.parseInt(dataTableAfter[2]);
        castSharesBefore = Integer.parseInt(dataTableBefore[2]);
        castSharesHistory = Integer.parseInt(elementosHistorial[1]);
        castPriceAfter = Double.parseDouble(dataTableAfter[3]);
        //castPriceBefore = Double.parseDouble(dataTableBefore[3]);
        castPriceHistory = Double.parseDouble(elementosHistorial[2]);

        /*System.out.println("Shares after:" + castSharesAfter);
        System.out.println("Shares before:" + castSharesBefore);
        System.out.println("Shares History:" + castSharesHistory);
        System.out.println("Price after:" + castPriceAfter);
        System.out.println("Price History:" + castPriceHistory);
        System.out.println(dataTableBefore[0] + dataTableAfter[0]);*/

        if(dataTableBefore[0].equals(dataTableAfter[0]) && (castSharesAfter-castSharesBefore) == castSharesHistory && castPriceAfter == castPriceHistory)
        {
            System.out.println("La validación de la venta se ha revisado en el historial y ha sido exitosa.");
        }

        return elementosHistorial;
    }

    private static String[] obtenerDatosDespuesVenta(String symbolName, WebElement ... screenElements) {
        String[] elementsTable = new String[3];

        for (int i = 0; i < screenElements.length; i++) {
            screenElements[i] = driver.findElement(By.xpath("//tr[contains(. , '"+symbolName+"')]//td["+(i+3)+"]"));
            elementsTable[i] = screenElements[i].getText();
        }

        return elementsTable;
    }

    /*private static void validarVenta(String symbolName, String[] valueTableBeforeSell, String restQty, WebElement ... screenElements) {
        String[] elementsWebAfterSell = new String[3];
        int castSharesBefore, castSharesAfter, castRestQty;

        for (int i = 0; i < screenElements.length; i++) {
                screenElements[i] = driver.findElement(By.xpath("//tr[contains(. , '"+symbolName+"')]//td["+(i+3)+"]"));
                elementsWebAfterSell[i] = screenElements[i].getText();
        }

        castSharesBefore = Integer.parseInt(valueTableBeforeSell[0]);
        castSharesAfter = Integer.parseInt(elementsWebAfterSell[0]);
        castRestQty = Integer.parseInt(restQty);

        if((castSharesBefore-castSharesAfter) == castRestQty)
            System.out.println("La venta fue hecha de manera correcta."+'\n'+
                    "Datos antes de la venta"+'\n'+
                    "Shares:"+valueTableBeforeSell[0]+'\n'+"Price:"+valueTableBeforeSell[1]+'\n'+"Total:"+valueTableBeforeSell[2]+'\n'+
                    "Datos despues de la venta"+'\n'+
                    "Shares:"+elementsWebAfterSell[0]+'\n'+"Price:"+elementsWebAfterSell[1]+'\n'+"Total:"+elementsWebAfterSell[2]);

        else
            System.out.println("La venta hecha fue erronea");
    }*/

    private static void realizarVenta(String symbolName, String restQty, String[] dataTableBefore, Select dropDownListElement, WebElement ... screenelements) {
        int originalQtyCast, restQtyCast;

        screenelements[0] =driver.findElement(By.cssSelector("[href='/sell']"));
        screenelements[0].click();

        dropDownListElement = new Select(driver.findElement(By.cssSelector("[name='symbol']")));
        dropDownListElement.selectByVisibleText(symbolName);

        screenelements[1] = driver.findElement(By.cssSelector("[name='qty']"));
        screenelements[2] = driver.findElement(By.cssSelector(".btn"));

        originalQtyCast = Integer.parseInt(dataTableBefore[2]);
        restQtyCast = Integer.parseInt(restQty);

        if(originalQtyCast-restQtyCast <= 0){
            System.out.println("No puedes restar esta cantidad, sobrepasas el numero de acciones que tienes");
            screenelements[0] =driver.findElement(By.xpath("//span[contains(.,'Finance')]"));
            screenelements[0].click();
        }
        else{
            screenelements[1].sendKeys(restQty);
            screenelements[2].click();
        }
    }

    private static void iniciarLogin(String username, String password, WebElement ... screenelements) {
        screenelements[0] = driver.findElement(By.cssSelector("[name='username']"));
        screenelements[1] = driver.findElement(By.cssSelector("[name='password']"));
        screenelements[2] = driver.findElement(By.cssSelector(".btn"));

        screenelements[0].sendKeys(username);
        screenelements[1].sendKeys(password);
        screenelements[2].click();
    }

    /*private static String[] iniciarLogin(String username, String password, WebElement ... screenElements) {
        String[] elementsWeb = new String[3];

        screenElements[0] = driver.findElement(By.cssSelector("[name='username']"));
        screenElements[1] = driver.findElement(By.cssSelector("[name='password']"));
        screenElements[2] = driver.findElement(By.cssSelector(".btn"));

        screenElements[0].sendKeys(username);
        screenElements[1].sendKeys(password);
        screenElements[2].click();

        for (int i = 3; i < screenElements.length; i++) {
            screenElements[i] = driver.findElement(By.xpath("//tr[contains(. , '"+symbolName+"')]//td["+i+"]"));
            elementsWeb[i-3] = screenElements[i].getText();
        }

        return elementsWeb;
    }*/
}
