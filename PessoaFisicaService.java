import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PessoaFisicaService {
    protected HashMap <String, PessoaFisica> hashPf = new HashMap<String, PessoaFisica>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public void cadastrar() throws IOException{
        System.out.println("Digite o seu cpf: ");
        String cpf = reader.readLine();
        if (hashPf.containsKey(cpf)){
            System.out.println("Nao pode existir o mesmo cpf para duas pessoas");
            return;
        }
        System.out.println("Digite seu nome");
        String nome = reader.readLine();

        hashPf.put(cpf, new PessoaFisica(nome,  cpf));
        System.out.println(hashPf.get(cpf));
        
    }

    public PessoaFisica obterPorCpf(String cpf){
        return hashPf.get(cpf);
    }

    
    

    

}
