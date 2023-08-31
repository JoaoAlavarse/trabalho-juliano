import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class PessoaFisicaService {
    protected Hashtable <String, PessoaFisica> hashPf = new Hashtable<String, PessoaFisica>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public void cadastrar() throws IOException{
        System.out.println("Digite o seu cpf: ");
        String cpf = reader.readLine();
        System.out.println("Digite seu nome");
        String nome = reader.readLine();

        hashPf.put(cpf, new PessoaFisica(nome,  cpf));
        
    }

    public PessoaFisica obterPorCpf(String cpf){
        return hashPf.get(cpf);
    }

    
    

    

}
