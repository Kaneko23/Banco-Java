/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Postgres;

import DAO.TransacaoDAO;
import Models.Tipo;
import Models.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author kaue_brito
 */
public class TransacaoDAOPostgres implements TransacaoDAO {

    private final Connection conexao;

    public TransacaoDAOPostgres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void RegistraTransacao(Transacao transacao) {
        String sql = "insert into transacao (id, data, tipo_operacao, valor_transacao, conta_id) values (?, ?, CAST(? as tipo_transacao), ?, ?)";
        String tipoTrans = (transacao.getTipoTransacao() == Tipo.deposito) ? "deposito" : (transacao.getTipoTransacao() == Tipo.saque) ? "saque" : (transacao.getTipoTransacao() == Tipo.envio) ? "envio" : "recebimento";
        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setObject(1, transacao.getId());
            stm.setDate(2, new Date (transacao.getData().getTime()));
            stm.setString(3, tipoTrans);
            stm.setDouble(4, transacao.getValorTransacao());
            stm.setObject(5, transacao.getContaID());

            stm.executeUpdate();

        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public void extrato() {
       String sql = "select * from transacao where conta(id) = ?";
    }

}
