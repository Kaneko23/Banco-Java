/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.Postgres.TransacaoDAOPostgres;
import static Factory.FactoryDAO.makeContaDAO;

import Models.Conta;
import Models.ContaCorrente;
import Models.ContaPoupanca;
import Models.ContaSalario;
import Models.Pessoa;
import Models.Tipo;
import Models.Transacao;
import Util.GerenciadorConexao;

import javax.swing.JOptionPane;

/**
 *
 * @author kaue_brito
 */
public class ContaController {

    public int criarConta(Pessoa titular, boolean corrente, boolean poupanca, boolean salario, String senha) {
        Conta conta = null;
        if (corrente) {
            conta = new ContaCorrente(titular, senha);
        } else if (poupanca) {
            conta = new ContaPoupanca(titular, senha);
        } else if (salario) {
            conta = new ContaSalario(titular, senha);
        }
        makeContaDAO().insereConta(conta);

        return conta.getNumero();

    }

    public Conta buscarContaPorDocumentoTitular(String documento) {

        return makeContaDAO().buscarContaPorDocumentoTitular(documento);
    }

    public Conta depositar(Conta conta, double valor) {
        if (valor > 0) {
            conta.depositar(valor);
            makeContaDAO().atualizaSaldo(conta);
            new TransacaoDAOPostgres(GerenciadorConexao.getConexao()).RegistraTransacao(new Transacao(Tipo.deposito, valor, conta.getId()));

        } else {
            JOptionPane.showMessageDialog(null, "O valor é inválido!");
        }
        return conta;

    }

    public Conta sacar(Conta conta, double valor) {

        if (valor <= conta.getSaldo() && valor > 0) {
            conta.sacar(valor);
            makeContaDAO().atualizaSaldo(conta);
            new TransacaoDAOPostgres(GerenciadorConexao.getConexao()).RegistraTransacao(new Transacao(Tipo.saque, valor, conta.getId()));
        } else {
            JOptionPane.showMessageDialog(null, "O valor é inválido!");
        }
        return conta;

    }

    public Conta transferir(Conta conta, Conta contaTrans, double valorTrans) {
        if (conta.getSaldo() >= valorTrans && valorTrans > 0) {
            conta.tranferir(contaTrans, valorTrans);
            new TransacaoDAOPostgres(GerenciadorConexao.getConexao()).RegistraTransacao(new Transacao(Tipo.envio, valorTrans, conta.getId()));
            new TransacaoDAOPostgres(GerenciadorConexao.getConexao()).RegistraTransacao(new Transacao(Tipo.recebimento, valorTrans, contaTrans.getId()));
            makeContaDAO().atualizaSaldo(conta);
            makeContaDAO().atualizaSaldo(contaTrans);
        } else {
            JOptionPane.showMessageDialog(null, "O valor é inválido!");
        }
        return conta;
    }

}
