/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static semana05.Semana05.dotenv;
/**
 *
 * @author kaue_brito
 */
public class GerenciadorConexao {

    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                
                String URL = dotenv.get("DB_URL");
                String user = dotenv.get("DB_USER");
                String password = dotenv.get("DB_PASSWORD");
                conexao = DriverManager.getConnection(URL, user, password);
                System.out.println("sucesso");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conexao;
    }
}
