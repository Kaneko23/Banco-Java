/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import DAO.ContaDAO;
import DAO.PessoaDAO;
import DAO.Postgres.ContaDAOPostgres;
import DAO.Postgres.PessoaDAOPostgres;
import Util.GerenciadorConexao;

/**
 *
 * @author kaue_brito
 */
public class FactoryDAO {
    public static ContaDAO makeContaDAO(){
        return new ContaDAOPostgres(GerenciadorConexao.getConexao());
    }
    public static PessoaDAO makePessoaDAO(){
        return new PessoaDAOPostgres(GerenciadorConexao.getConexao());
    }
}
