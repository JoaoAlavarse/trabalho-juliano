import java.util.Random;

public abstract class AbstractConta {
    private int agencia;
    private int senha;
    private PessoaFisica pessoaFisica;
    private int numeroConta;
    protected Random numeroAleatorio = new Random();

    public AbstractConta(int agencia, int senha, PessoaFisica pessoaFisica) {
        this.agencia = agencia;
        this.senha = senha;
        this.pessoaFisica = pessoaFisica;
        this.numeroConta = numeroAleatorio.nextInt() * -1;
    }
    
    public String getCpf(){
        return pessoaFisica.getCpf();
    }


  


    

    

}
