package bIntro;

public class FinanceLoginError extends FinanceParentTest{
    public static void main(String[] args) throws Exception {
        navegarPaginaPrincipal("Chrome","http://vamonos-finance.herokuapp.com/");
        validarPantallaLogin();
        realizarLogin("Juanito.Juanito","Testing1234");
        validarErrorLogin();
    }
}
