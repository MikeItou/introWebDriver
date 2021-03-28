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
                   registerBuy=null, fieldSymbol=null, fieldQuantity=null, dataTableShares=null, dataTableSymbol=null,
                   dataTableName=null, dataTablePrice=null, dataTableTotal=null, dataTableTotal2=null, dataTableTotal3=null,
                   dataTableTOTAL=null,dataTableTransacted=null, registerHistory=null;

        String username = "Juanito.Banana", password = "Testing1234", symbolName = "MX",quantityValue = "30",
                nameValue = "MagnaChip Semiconductor Corp";

        String[] dataTableBefore,dataTableAfter,dataTableHistory;

        //Inicicar sesion
        iniciarLogin(username,password, fieldUsername,fieldPassword,buttonLoginBuy);
        dataTableBefore = obtenerDatos(symbolName, dataTableSymbol, dataTableName, dataTableShares,dataTablePrice,dataTableTOTAL);
        realizarNuevaCompra(symbolName, quantityValue, registerBuy,fieldSymbol,fieldQuantity,buttonLoginBuy);
        dataTableAfter = obtenerDatos(symbolName, dataTableSymbol, dataTableName, dataTableShares,dataTablePrice,dataTableTOTAL);
        dataTableHistory = validarHistorial(dataTableBefore,dataTableAfter,registerHistory,dataTableSymbol,dataTableShares,dataTablePrice,dataTableTransacted);
        //validarNuevaCompra(symbolName, nameValue, dataTableSymbol,dataTableName,dataTableShares, dataTablePrice,dataTableTotal,dataTableTotal2,dataTableTotal3);
        //validarNuevaCompraHistorial(dataTableValues, dataTableSymbol,dataTableName,dataTableShares,dataTablePrice,dataTableTotal);

    }

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
        System.out.println(dataTableBefore[0].length() +" "+ dataTableAfter[0].length());
        System.out.println((castSharesAfter-castSharesBefore) == castSharesHistory);
        System.out.println(castPriceAfter == castPriceHistory);*/


        if(dataTableBefore[0].equals(dataTableAfter[0]) && (castSharesAfter-castSharesBefore) == castSharesHistory && castPriceAfter == castPriceHistory)
        {
            System.out.println("La validación de la compra se ha revisado en el historial y ha sido exitosa.");
        }

        return elementosHistorial;
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

    /*private static void validarNuevaCompra(String symbolName, String nameValue, WebElement ... screenElements) {

        for (int i = 0; i < screenElements.length-2; i++) {
            screenElements[i] = driver.findElement(By.xpath("//tr[contains(. , 'MX')]//td["+(i+1)+"]"));
        }
        screenElements[5] = driver.findElement(By.xpath("//tr[2]//td[5]"));
        screenElements[6] = driver.findElement(By.xpath("//tr[3]//td[5]"));

        double castShares, castPrice, castTotal, castTotalCash, castTotalTotal, total, sumaTotalGeneral;
        //comparar el simbolo agregado (MX) y validar el contenido de la columna Name (MagnaChip Semiconductor Corp)
        if (screenElements[0].getText() != symbolName && screenElements[1].getText() != nameValue){
            castShares = Double.parseDouble(screenElements[2].getText());
            castPrice = Double.parseDouble(screenElements[3].getText());
            castTotal = Double.parseDouble(screenElements[4].getText());
            castTotalCash = Double.parseDouble(screenElements[5].getText());
            castTotalTotal = Double.parseDouble(screenElements[6].getText());

            total = castShares * castPrice;

            if (castTotal == total){
                System.out.println("La multiplucacion de " + castShares + " X " + castPrice + " es igual a " + total);
                System.out.println("El valor a evaluar es " + castTotal);

                //valiar la sumatoria del elemento creada y el cash restante
                sumaTotalGeneral = castTotalCash + castTotal;

                if (sumaTotalGeneral == castTotalTotal){
                    System.out.println("La sumatoria de " + castTotal + " + " + castTotalCash + " es igual a " + sumaTotalGeneral);
                    System.out.println("El valor a evaluar es " + castTotalTotal);
                }

                else{
                    System.out.println(castTotalTotal + " no es igual a " + sumaTotalGeneral);
                }


            }

            else
                System.out.println(castTotal + " no es igual a " + total);
        }
        else
            System.out.println("No hay igualdad entre los datos obtenidos y los almacenados");


    }*/

    private static void realizarNuevaCompra(String symbolName, String quantityName, WebElement ... screenElements) {
        //int randomNumber = new Random().nextInt(100000);


        screenElements[0] = driver.findElement(By.cssSelector("[href='/buy']"));
        screenElements[0].click();

        screenElements[1] = driver.findElement(By.cssSelector("[name='symbol']"));
        screenElements[2] = driver.findElement(By.cssSelector("[name='qty']"));
        screenElements[3] = driver.findElement(By.cssSelector(".btn"));
        screenElements[1].sendKeys(symbolName);
        screenElements[2].sendKeys(quantityName);
        screenElements[3].click();
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
