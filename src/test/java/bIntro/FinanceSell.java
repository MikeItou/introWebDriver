package bIntro;

public class FinanceSell extends FinanceParentTest {
    public static void main(String[] args) {
        setup("chrome", "url");

        login("xxx", "yyy");

        clickSellLink();

        sellShares("AMZN", "2");

        validateShares("AMZN", "0");
    }
}