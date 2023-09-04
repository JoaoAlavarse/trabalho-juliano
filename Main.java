import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
    private static ContaService contaService = new ContaService(pessoaFisicaService);
    private static  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int opcaoMenu;
        boolean repetirMenu = true;
        

        do{
            System.out.println("BANCO JOAO INTERPRISE");
            System.out.println("");
            System.out.println("Selecione sua opcao: ");
            System.out.println("");
            System.out.println("1 - Cadastrar Pessoa Fisica");
            System.out.println("2 - Cadastrar Conta Poupanca");
            System.out.println("3 - Cadastrar Conta Corrente");
            System.out.println("4 - Efetuar Saque");
            System.out.println("5 - Efetuar Deposito");
            System.out.println("6 - Efetuar Transferencia");
            System.out.println("");
            System.out.println("7 - Sair");
            System.out.println("Escolha sua opcao");

            opcaoMenu = Integer.parseInt(reader.readLine());
            switch (opcaoMenu){
                case 1:
                    pessoaFisicaService.cadastrar();
                break;

                case 2:
                    contaService.cadastrarContaPoupanca();
                break;
                    
                case 3:
                    contaService.cadastrarContaCorrente();
                break;

                case 4:
                    contaService.efetuarSaque();
                break;
                    
                case 5:
                    contaService.efetuarDeposito();
                break;

                case 6:

                break;

                case 7:
                    System.out.println("Saindo...");
                    repetirMenu = false;
                break;
            }
        } while (repetirMenu);


    }
}