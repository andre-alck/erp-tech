/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.erptech.dao;

import com.erptech.connection.ConnectionFactory;
import com.erptech.model.CadastroProdutoModel;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
public class CadastroProdutoDao {

    public void cadastrarProduto(CadastroProdutoModel produto) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO erptech.cadastro_produto (codigo_produto, descricao_produto,"
                    + " unidade_comercializacao_produto, preco_produto, quantidade_produto) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, produto.getCodigoDoProduto());
            stmt.setString(2, produto.getDescricaoDoProduto());
            stmt.setString(3, produto.getUnidadeDeComercializacaoDoProduto());
            stmt.setDouble(4, produto.getPrecoDoProduto());
            stmt.setInt(5, produto.getQuantidadeEmEstoqueDoProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(null, ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public List<CadastroProdutoModel> listarProdutos() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<CadastroProdutoModel> listaDeProdutos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM cadastro_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                CadastroProdutoModel produto = new CadastroProdutoModel();

                produto.setCodigoDoProduto(rs.getInt("CODIGO_PRODUTO"));
                produto.setDescricaoDoProduto(rs.getString("DESCRICAO_PRODUTO"));
                produto.setUnidadeDeComercializacaoDoProduto(rs.getString("UNIDADE_COMERCIALIZACAO_PRODUTO"));
                produto.setPrecoDoProduto(rs.getDouble("PRECO_PRODUTO"));
                produto.setQuantidadeEmEstoqueDoProduto(rs.getInt("QUANTIDADE_PRODUTO"));

                listaDeProdutos.add(produto);
            }
        } catch (SQLException ex) {
            throw new SQLException(null, ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);

        }
        return listaDeProdutos;
    }

    public void excluirCadastroDeProduto(CadastroProdutoModel produto) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM erptech.cadastro_produto WHERE codigo_produto = ?");
            stmt.setInt(1, produto.getCodigoDoProduto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(null, ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

    public void AtualizarCadastroDeProduto(CadastroProdutoModel produto) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE erptech.cadastro_produto SET codigo_produto = ?, descricao_produto = ?,"
                    + " unidade_comercializacao_produto = ?, preco_produto = ?, quantidade_produto = ? WHERE codigo_produto = ?");
            stmt.setInt(1, produto.getCodigoDoProduto());
            stmt.setString(2, produto.getDescricaoDoProduto());
            stmt.setString(3, produto.getUnidadeDeComercializacaoDoProduto());
            stmt.setDouble(4, produto.getPrecoDoProduto());
            stmt.setInt(5, produto.getQuantidadeEmEstoqueDoProduto());
            stmt.setInt(6, produto.getCodigoDoProduto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(null, ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }

}