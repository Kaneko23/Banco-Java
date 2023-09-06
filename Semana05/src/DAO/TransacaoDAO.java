/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Transacao;


/**
 *
 * @author kaue_brito
 */
public interface TransacaoDAO {
    public void RegistraTransacao(Transacao transacao);
    public void extrato();
}
