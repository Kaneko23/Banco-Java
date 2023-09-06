/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Transacao;
import java.util.UUID;
import Models.Tipo;

/**
 *
 * @author kaue_brito
 */
public class TransacaoController {
    public Transacao registrarTransacao(Tipo tipoTransacao, Double valorTransacao, UUID contaID){
        Transacao transacao = new Transacao(tipoTransacao, valorTransacao, contaID);
        return transacao;
    }
}    
