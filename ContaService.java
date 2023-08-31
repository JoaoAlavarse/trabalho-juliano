import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class ContaService{
    private PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
    private Hashtable<String, AbstractConta> hashConta = new Hashtable<String, AbstractConta>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void cadastrarConta(AbstractConta conta) throws Exception{
        hashConta.put(conta.getCpf(), conta);
    }

    public void cadastrarContaCorrente() throws Exception{
        Integer agencia = getAgencia();
        Integer senha = verificarSenha();
        PessoaFisica pessoaFisica = verificarPessoaFisica();
        cadastrarConta(new ContaCorrente(agencia, senha, pessoaFisica));

    }

    public void cadastrarContaPoupanca() throws Exception{
        Integer agencia = getAgencia();
        Integer senha = verificarSenha();
        PessoaFisica pessoaFisica = verificarPessoaFisica();
        cadastrarConta(new ContaPoupanca(agencia, senha, pessoaFisica));

    }

    private Integer getAgencia() throws NumberFormatException, IOException{
        System.out.println("Entre com o numero da sua agencia");
        int agencia = Integer.parseInt(reader.readLine());
        return agencia;
    }


    public Integer verificarSenha() throws Exception{
        System.out.println("Entre com a sua senha de 4 digitos");
        String teste = reader.readLine();
        if (!teste.matches("[0-9]+") && teste.length() != 4){
            System.out.println("Senha com parametros invalidos");
            verificarSenha();
            return 0;
        }
        else {
            int senha = Integer.parseInt(teste);
            return senha;
        }

    }

    public PessoaFisica verificarPessoaFisica() throws IOException{
        System.out.println("Entre com o cpf");
        String cpf = reader.readLine();
        if(!pessoaFisicaService.hashPf.containsKey(cpf)){
            System.out.println("Cpf nao cadastrado");
            verificarPessoaFisica();
        }
        return pessoaFisicaService.obterPorCpf(cpf);
    }


}
