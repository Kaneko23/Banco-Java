package semana05;

import Models.Conta;
import Models.Transacao;
import Util.GerenciadorConexao;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import view.Sistema;

public class Semana05 {
    
    // DB FAKE
    public static ArrayList<Conta> banco = new ArrayList<>();
    public static Dotenv dotenv = Dotenv.load();
    public static void main(String[] args) {
       new Sistema().setVisible(true);
        System.out.println(dotenv.get("DB_URL"));
       GerenciadorConexao.getConexao();
    }
}
