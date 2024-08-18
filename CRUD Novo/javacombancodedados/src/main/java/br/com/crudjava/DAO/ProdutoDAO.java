package br.com.crudjava.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


import br.com.crudjava.conexao.Conexao;

public class ProdutoDAO {
    
    public void salvar(Produto produto) throws Exception{
        String sql = "insert into produto (nome, quantidade, valor) values (?, ?, ?)";

         // abre e fecha automaticamente a conexão (sem necessidade de usar close())
        try (Connection conexao = Conexao.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, produto.nome());
            stmt.setInt(2, produto.quantidade());
            stmt.setDouble(3, produto.valor());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public List<Produto> buscarTodos() throws Exception{
        String sql = "select * from produto";
        List<Produto> produtos = new ArrayList<>();

         // abre e fecha automaticamente a conexão (sem necessidade de usar close())
        try (Connection conexao = Conexao.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Produto produto = new Produto(rs.getLong("id"), 
                        rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("valor"));

                        produtos.add(produto);
                    }
                }

        } catch (SQLException e) {
            throw new Exception(e);
        }
        return produtos;
    }

    public Produto buscarPorID(Long id) throws Exception{
        String sql = "select * from produto where id = ?";
        Produto produto = null;

         // abre e fecha automaticamente a conexão (sem necessidade de usar close())
        try (Connection conexao = Conexao.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        produto = new Produto(rs.getLong("id"), 
                        rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("valor"));
                    }
                }

        } catch (SQLException e) {
            throw new Exception(e);
        }
        return produto;
    }

        
    public void atualizar(Produto produto) throws Exception{
        String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = ?";

         // abre e fecha automaticamente a conexão (sem necessidade de usar close())
        try (Connection conexao = Conexao.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, produto.nome());
            stmt.setInt(2, produto.quantidade());
            stmt.setDouble(3, produto.valor());
            stmt.setLong(4, produto.id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void excluir(Long id) throws Exception{
        String sql = "delete from produto where id = ?";

         // abre e fecha automaticamente a conexão (sem necessidade de usar close())
        try (Connection conexao = Conexao.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}
