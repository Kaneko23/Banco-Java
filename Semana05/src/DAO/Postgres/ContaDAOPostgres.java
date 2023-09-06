package DAO.Postgres;

import DAO.ContaDAO;
import Models.Conta;
import Models.ContaCorrente;
import Models.ContaPoupanca;
import Models.ContaSalario;
import Models.Pessoa;
import Models.PessoaFisica;
import Models.PessoaJuridica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContaDAOPostgres implements ContaDAO {

    private final Connection conexao;

    public ContaDAOPostgres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insereConta(Conta conta) {
        try {
            String sql = "insert into conta (id, numero, saldo, tipo, pessoa_id, senha) values (?, ?, ?, CAST(? as tipo_conta), ?, ?)";
            String tipoConta = (conta instanceof ContaCorrente) ? "corrente" : (conta instanceof ContaSalario) ? "salario" : "poupança";

            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setObject(1, conta.getId());
            stm.setInt(2, conta.getNumero());
            stm.setDouble(3, conta.getSaldo());
            stm.setString(4, tipoConta);
            stm.setObject(5, conta.getTitular().getId());
            stm.setString(6, conta.getSenha());

            stm.executeUpdate();

        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public Conta buscarContaPorDocumentoTitular(String documento) {
        String sql = "select c.id, c.numero, c.saldo, c.tipo, c.pessoa_id, p.nome, p.tipo as pessoa_tipo, c.senha from conta c "
                + "join pessoa p on c.pessoa_id = p.id where p.documento = ?";

        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setString(1, documento);

            ResultSet resultado = stm.executeQuery();
            if (resultado.next()) {
                UUID id = resultado.getObject("pessoa_id", UUID.class);
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("pessoa_tipo");

                Pessoa pessoa = null;
                if (tipo.equals("PF")) {
                    pessoa = new PessoaFisica(id, nome, documento);
                } else {
                    pessoa = new PessoaJuridica(id, nome, documento);
                }
                UUID idConta = resultado.getObject("id", UUID.class);
                int numero = resultado.getInt("numero");
                double saldo = resultado.getDouble("saldo");
                String tipoC = resultado.getString("tipo");
                String senha = resultado.getString("senha");

                switch (tipoC) {
                    case "corrente":
                        return new ContaCorrente(idConta, numero, saldo, pessoa, senha);
                    case "poupança":
                        return new ContaPoupanca(idConta, numero, saldo, pessoa, senha);
                    case "salario":
                        return new ContaSalario(idConta, numero, saldo, pessoa, senha);
                    default:
                        return null;
                }
            }

        } catch (SQLException error) {
            System.out.println(error);
            return null;
        }
        return null;

    }

    @Override
    public void atualizaSaldo(Conta conta) {
        String sql = "UPDATE conta SET saldo = ? where id = ?;";
        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setDouble(1, conta.getSaldo());
            stm.setObject(2, conta.getId());
            stm.execute();
        } catch (SQLException error) {
            System.out.println(error);
        }

    }

}
