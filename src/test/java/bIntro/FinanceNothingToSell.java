package bIntro;

public class FinanceNothingToSell extends FinanceParentTest {
    public static void main(String[] args) throws Exception {
        navegarPaginaPrincipal("Chrome","http://vamonos-finance.herokuapp.com/");
        validarPantallaLogin();
        realizarLogin("Juanito.Banana", "Testing1234");
        validarPantallaPrincipal();
        validarUsuarioSoloCash();
        clickSell();
        validarSymbolInvisible();
        validarQtyInvisible();
        validarBotonSellInvisible();
        validarLeyendaSell();
    }

}