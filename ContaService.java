import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ContaService{
    private PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
    private HashMap<String, AbstractConta> hashContaPoupanca = new HashMap<String, AbstractConta>();
    private HashMap<String, AbstractConta> hashContaCorrente = new HashMap<String, AbstractConta>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public ContaService(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    public void cadastrarContaPoupanca(AbstractConta conta) throws Exception{
        hashContaPoupanca.put(conta.getCpf(), conta);
    }

    public void cadastrarContaCorrente(AbstractConta conta) throws Exception{
        hashContaCorrente.put(conta.getCpf(), conta);
    }

    public void cadastrarContaCorrente() throws Exception{
        Integer agencia = getAgencia();
        Integer senha = verificarSenha();
        PessoaFisica pessoaFisica = verificarPessoaFisica();
        cadastrarContaCorrente(new ContaCorrente(agencia, senha, pessoaFisica));
    }

    public void cadastrarContaPoupanca() throws Exception{
        Integer agencia = getAgencia();
        Integer senha = verificarSenha();
        PessoaFisica pessoaFisica = verificarPessoaFisica();
        cadastrarContaPoupanca(new ContaPoupanca(agencia, senha, pessoaFisica));

    }

    private Integer getAgencia() throws NumberFormatException, IOException{
        System.out.println("Entre com o numero da sua agencia");
        int agencia = Integer.parseInt(reader.readLine());
        return agencia;
    }


    private Integer verificarSenha() throws Exception{
        System.out.println("Entre com a sua senha de 4 digitos");
        String teste = reader.readLine();
        if (!teste.matches("[0-9]+") || teste.length() != 4){
            System.out.println("Senha com parametros invalidos");
            verificarSenha();
            return 0;
        }
        else {
            int senha = Integer.parseInt(teste);
            return senha;
        }

    }

    private PessoaFisica verificarPessoaFisica() throws IOException{
        System.out.println("Entre com o cpf");
        String cpf = reader.readLine();
        if(pessoaFisicaService.hashPf.containsKey(cpf) == true){
            return pessoaFisicaService.obterPorCpf(cpf);
        } else{
            System.out.println("Cpf nao cadastrado, entre novamente com o cpf");
            return verificarPessoaFisica();
        }
       
    }


    private AbstractConta verificarContaPoupanca() throws IOException{
        System.out.println("Entre com o cpf do dono da conta");
        String cpf = reader.readLine();
        if(hashContaPoupanca.containsKey(cpf) == true){
            return hashContaPoupanca.get(cpf);
        } else{
            System.out.println("Cpf nao cadastrado, entre novamente com o cpf");
            return verificarContaPoupanca();
        }
    }

    private AbstractConta verificarContaCorrente() throws IOException{
        System.out.println("Entre com o cpf do dono da conta");
        String cpf = reader.readLine();
        if(hashContaCorrente.containsKey(cpf) == true){
            return hashContaCorrente.get(cpf);
        } else{
            System.out.println("Cpf nao cadastrado, entre novamente com o cpf");
            return verificarContaCorrente();
        }
    }

    public void efetuarDeposito() throws NumberFormatException, IOException{
        System.out.println("Deseja efetuar o deposito em uma conta CORRENTE ou POUPANCA?");
        System.out.println("1 - POUPANCA");
        System.out.println("2 - CORRENTE");
        int opcao = Integer.parseInt(reader.readLine());
        switch (opcao){
            case 1:
                efetuarDepositoPoupanca();
            break;

            case 2:
                efetuarDepositoCorrente();
            break;

            default:
                System.out.println("Opcao invalida. Selecione novamente");
                efetuarDeposito();
            break;
        }
    }

    private AbstractConta verificarContaOrigem() throws IOException{
        AbstractConta conta;
        System.out.println("A conta de origem é POUPANCA ou CORRENTE?");
        System.out.println("1 - POUPANCA");
        System.out.println("2 - CORRENTE");
        int opcao = Integer.parseInt(reader.readLine());
        switch (opcao){
            case 1:
                conta = verificarContaPoupanca();
                if (conta != null){
                    return conta;
                } else {
                    System.out.println("Conta nao encontrada");
                    return verificarContaOrigem();
                }

            case 2:
                conta = verificarContaCorrente();
                if (conta != null){
                    return conta;
                } else {
                    System.out.println("Conta nao encontrada");
                    return verificarContaOrigem();
                }

            default:
                System.out.println("Opcao invalida. Selecione novamente");
                return verificarContaOrigem();
        }
    }


    private int getValorTransacao() throws NumberFormatException, IOException{
        System.out.println("Insira o valor inteiro desjado");
        String teste = reader.readLine();
        if (!teste.matches("[0-9]+")){
            System.out.println("Invalido. Nao pode inserir letras");
            return getValorTransacao();
        } else if (Integer.parseInt(teste) <= 0){
            System.out.println("Valor nao pode ser igual ou menor a 0");
            return getValorTransacao();
        } else{
            int valor = Integer.parseInt(teste);
            return valor;
        }
    }

    private void efetuarDepositoCorrente() throws IOException {
        getAgencia();
        AbstractConta conta = verificarContaCorrente();
        AbstractConta contaOrigem = verificarContaOrigem();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            conta.saldo = conta.saldo + valor;
            hashContaCorrente.replace(conta.getCpf(), conta);
        }

    }

    private void efetuarDepositoPoupanca() throws IOException{
        getAgencia();
        AbstractConta conta = verificarContaPoupanca();
        AbstractConta contaOrigem = verificarContaOrigem();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            conta.saldo = conta.saldo + valor;
            hashContaPoupanca.replace(conta.getCpf(), conta);
        }
    }

    public void efetuarSaque() throws NumberFormatException, IOException{
        System.out.println("Deseja efetuar o saque em uma conta CORRENTE ou POUPANCA?");
        System.out.println("1 - POUPANCA");
        System.out.println("2 - CORRENTE");
        int opcao = Integer.parseInt(reader.readLine());
        switch (opcao){
            case 1:
                efetuarSaquePoupanca();
            break;

            case 2:
                efetuarSaqueCorrente();
            break;

            default:
                System.out.println("Opcao invalida. Selecione novamente");
                efetuarSaque();
            break;
        }
    }

    private void efetuarSaqueCorrente() throws IOException {
        getAgencia();
        AbstractConta conta = verificarContaCorrente();
        AbstractConta contaOrigem = verificarContaOrigem();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if(!verificarSaldo(conta, valor)){
                System.out.println("Saque nao pode ser concluido. Saldo insuficiente");
            } else {
                conta.saldo = conta.saldo - valor;
                hashContaCorrente.replace(conta.getCpf(), conta);
            }
        }
    }

    private void efetuarSaquePoupanca() throws NumberFormatException, IOException {
        getAgencia();
        AbstractConta conta = verificarContaPoupanca();
        AbstractConta contaOrigem = verificarContaOrigem();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if(!verificarSaldo(conta, valor)){
                System.out.println("Saque nao pode ser concluido. Saldo insuficiente");
            } else {
                conta.saldo = conta.saldo - valor;
                hashContaPoupanca.replace(conta.getCpf(), conta);
            }
        }
    }

    private Boolean verificarSaldo(AbstractConta conta, int valor){
        if (conta.saldo - valor < 0){
            return false;
        } else{
            return true;
        }
    }

    public void efetuarTransferencia() throws IOException{
        System.out.println("Escolha a transferencia desejada");
        System.out.println("1 - Poupanca para Poupanca");
        System.out.println("2 - Poupanca para Corrente");
        System.out.println("3 - Corrente para Poupanca");
        System.out.println("4 - Corrente para Corrente");
        int opcao = Integer.parseInt(reader.readLine());
        switch (opcao){
            case 1:
                efetuarTransferenciaPoupancaParaPoupanca();
            break;

            case 2:
                efetuarTransferenciaPoupancaParaCorrente();
            break;

            case 3:
                efetuarTransferenciaCorrenteParaPoupanca();
            break;

            case 4:
                efetuarTransferenciaCorrenteParaCorrente();
            break;

            default:
                System.out.println("Opcao invalida");
                efetuarTransferencia();
            break;
        }

    }
    
    private void efetuarTransferenciaPoupancaParaPoupanca() throws IOException{
        getAgencia();
        AbstractConta contaOrigem = verificarContaOrigem();
        AbstractConta conta = verificarContaPoupanca();
        getAgencia();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if (!verificarSaldo(contaOrigem, valor)){
                System.out.println("Transferencia nao pode ser concluido. Saldo insuficiente");
            } else {
                contaOrigem.saldo = conta.saldo - valor;
                conta.saldo = conta.saldo + valor;
                hashContaPoupanca.replace(conta.getCpf(), conta);
                hashContaPoupanca.replace(contaOrigem.getCpf(), contaOrigem);
            }
        }

    }

    private void efetuarTransferenciaCorrenteParaPoupanca() throws IOException{
        getAgencia();
        AbstractConta contaOrigem = verificarContaOrigem();
        AbstractConta conta = verificarContaPoupanca();
        getAgencia();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if (!verificarSaldo(contaOrigem, valor)){
                System.out.println("Transferencia nao pode ser concluido. Saldo insuficiente");
            } else {
                contaOrigem.saldo = conta.saldo - valor;
                conta.saldo = conta.saldo + valor;
                hashContaPoupanca.replace(conta.getCpf(), conta);
                hashContaCorrente.replace(contaOrigem.getCpf(), contaOrigem);
            }
        }

    }

    private void efetuarTransferenciaPoupancaParaCorrente() throws IOException{
        getAgencia();
        AbstractConta contaOrigem = verificarContaOrigem();
        AbstractConta conta = verificarContaPoupanca();
        getAgencia();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if (!verificarSaldo(contaOrigem, valor)){
                System.out.println("Transferencia nao pode ser concluido. Saldo insuficiente");
            } else {
                contaOrigem.saldo = conta.saldo - valor;
                conta.saldo = conta.saldo + valor;
                hashContaCorrente.replace(conta.getCpf(), conta);
                hashContaPoupanca.replace(contaOrigem.getCpf(), contaOrigem);
            }
        }

    }

    private void efetuarTransferenciaCorrenteParaCorrente() throws IOException{
        getAgencia();
        AbstractConta contaOrigem = verificarContaOrigem();
        AbstractConta conta = verificarContaPoupanca();
        getAgencia();
        int valor = getValorTransacao();
        if (verificarSenha(contaOrigem)){
            if (!verificarSaldo(contaOrigem, valor)){
                System.out.println("Transferencia nao pode ser concluido. Saldo insuficiente");
            } else {
                contaOrigem.saldo = conta.saldo - valor;
                conta.saldo = conta.saldo + valor;
                hashContaCorrente.replace(conta.getCpf(), conta);
                hashContaCorrente.replace(contaOrigem.getCpf(), contaOrigem);
            }
        }

    }

    private Boolean verificarSenha(AbstractConta conta) throws NumberFormatException, IOException{
        System.out.println("Insira sua senha");
        if (conta.getSenha() == Integer.parseInt(reader.readLine())){
            return true;
        } else {
            return false;
        }
    }
    

}
