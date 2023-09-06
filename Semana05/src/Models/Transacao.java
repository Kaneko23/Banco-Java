/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author kaue_brito
 */
public class Transacao {

    private UUID id;
    private Date data;
    private Tipo tipoTransacao;
    private Double valorTransacao;
    private UUID contaID;

    public Transacao(Tipo tipoTransacao, Double valorTransacao, UUID contaID) {
        this.id = UUID.randomUUID();
        this.data = new Date();
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.contaID = contaID;
    }


    public UUID getId() {
        return id;
    }

    public Tipo getTipoTransacao() {
        return tipoTransacao;
    }

    public Double getValorTransacao() {
        return valorTransacao;
    }

    public UUID getContaID() {
        return contaID;
    }

    public Date getData() {
        return data;
    }

 

}
