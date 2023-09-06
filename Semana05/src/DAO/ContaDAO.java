/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Conta;

/**
 *
 * @author kaue_brito
 */
public interface ContaDAO {
    public void insereConta(Conta conta);
    public Conta buscarContaPorDocumentoTitular(String documento);
    public void atualizaSaldo(Conta conta);
}
