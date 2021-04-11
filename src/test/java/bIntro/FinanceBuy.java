package bIntro;

public class FinanceBuy extends FinanceParentTest {
    public static void main(String[] args) {
        setup("Chrome", "http://vamonos-finance.herokuapp.com");

        login("Juanito.Banana", "Testing1234");

        clickBuyLink();

        buyShares("AMZN", "2");

        validateShares("AMZN", "2");
    }


}