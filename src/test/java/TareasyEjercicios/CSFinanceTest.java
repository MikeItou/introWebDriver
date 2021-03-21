package TareasyEjercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CSFinanceTest {
    static WebDriver driver;

    public static void main(String[] args) {
        //Abrir el navegador
        driver = new ChromeDriver();
        driver.get("http://vamonos-finance.herokuapp.com/");

        //Declarar elementos a usar
        WebElement linkRegistro, registerLogin, fieldUsername, fieldPassword, fieldRepeatPassword, buttonLoginRegister,
                registerQuote, registerBuy, registerSell, registerHistory, registerLgout;

        //username & password
        int randomNumber = new Random().nextInt(100000);
        String username = "Juanito.Banana"/*+randomNumber*/,password = "Testing1234";


        //Navegar en la URL y verificarla
        linkRegistro = driver.findElement(By.cssSelector("[href='/register']"));
        registerLogin = driver.findElement(By.cssSelector("[href='/login']"));
        fieldUsername = driver.findElement(By.cssSelector("[name='username']"));
        fieldPassword = driver.findElement(By.cssSelector("[name='password']"));
        buttonLoginRegister = driver.findElement(By.cssSelector(".btn"));

        if(validarPantalla(linkRegistro, registerLogin, fieldUsername, fieldPassword, buttonLoginRegister))
            System.out.println("Link de registro visible");
        else{
            System.out.println("Error al cargar la pagina");
        }
        linkRegistro.click();
        //Crear Usuario
        fieldUsername = driver.findElement(By.cssSelector("[name='username']"));
        fieldPassword = driver.findElement(By.cssSelector("[name='password']"));
        fieldRepeatPassword = driver.findElement(By.cssSelector("[name='repeat_password']"));
        buttonLoginRegister = driver.findElement(By.cssSelector(".btn"));

        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        fieldRepeatPassword.sendKeys(password);
        buttonLoginRegister.click();

        //Inicicar sesion con el usuario creado
        fieldUsername = driver.findElement(By.cssSelector("[name='username']"));
        fieldPassword = driver.findElement(By.cssSelector("[name='password']"));
        buttonLoginRegister = driver.findElement(By.cssSelector(".btn"));

        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        buttonLoginRegister.click();

        //Validar que al realizar el login se este en este en la pagina del login
        registerQuote = driver.findElement(By.cssSelector("[href='/quote']"));
        registerBuy = driver.findElement(By.cssSelector("[href='/buy']"));
        registerSell= driver.findElement(By.cssSelector("[href='/sell']"));
        registerHistory = driver.findElement(By.cssSelector("[href='/history']"));
        registerLgout = driver.findElement(By.cssSelector("[href='/logout']"));

        if(validarPantalla(registerQuote, registerBuy, registerSell, registerHistory, registerLgout))
            System.out.println("Pagina de login validada correctamente");
        else{
            System.out.println("Error al validar login");
        }

    }

    public static boolean validarPantalla(WebElement ... screenElements) {
        int numElements = screenElements.length;
        for (int i = 0; i < numElements; i++) {
            if(screenElements[i].isDisplayed()) {
                continue;
            } else return false;

        }
        return true;
    }
}


