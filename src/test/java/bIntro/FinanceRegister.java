package bIntro;

public class FinanceRegister extends FinanceParentTest {
    public static void main(String[] args) {
        //Chrome, FireFox, InternetExplorer, Opera, Safari, Edge
        setup("Chrome", "http://vamonos-finance.herokuapp.com");

        clickRegisterLink();

        inputUsernamePassword("Juanito.Banana", "Testing1234");

        login("Juanito.Banana", "Testing1234");

        validateAccountCash("10000.00");
    }
}