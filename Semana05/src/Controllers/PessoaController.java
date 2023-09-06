/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import static Factory.FactoryDAO.makePessoaDAO;
import Models.Pessoa;
import Models.PessoaFisica;
import Models.PessoaJuridica;


/**
 *
 * @author kaue_brito
 */
public class PessoaController {

    public Pessoa criarPessoa(String nome, String documento, String tipo) {
        Pessoa pessoa = null;

        if (tipo.equals("Pessoa Física")) {
            pessoa = new PessoaFisica(nome, documento);
        } else {
            pessoa = new PessoaJuridica(nome, documento);
        }
        
        makePessoaDAO().inserirPessoa(pessoa);
        return pessoa;

    }

}
