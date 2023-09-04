import java.util.Random;

public abstract class AbstractConta {
    private int agencia;
    private int senha;
    private PessoaFisica pessoaFisica;
    private int numeroConta;
    protected float saldo;
    protected Random numeroAleatorio = new Random();

    protected AbstractConta(int agencia, int senha, PessoaFisica pessoaFisica) {
        this.agencia = agencia;
        this.senha = senha;
        this.pessoaFisica = pessoaFisica;

        this.numeroConta = setNumeroConta(numeroAleatorio);
    }
    
    protected String getCpf(){
        return pessoaFisica.getCpf();
    }

    private int setNumeroConta(Random numeroAleatorio) {
        int teste = numeroAleatorio.nextInt();
        if (teste < 0){
            this.numeroConta = teste * -1;
        } else {
            this.numeroConta =  teste;
        }
        return teste;
    }

    protected int getSenha(){
        return senha;
    }

    

  


    

    

}
