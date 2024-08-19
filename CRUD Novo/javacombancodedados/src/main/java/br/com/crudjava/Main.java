package br.com.crudjava;

import java.io.Console;
import java.sql.*;
import java.sql.Statement;
import br.com.crudjava.DAO.Produto;
import br.com.crudjava.DAO.ProdutoDAO;
import br.com.crudjava.conexao.Conexao;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static Console console = System.console();
    public static void main(String[] args) throws SQLException {
        int op = 0;

        do {
            exibirMenu();
            op = Integer.parseInt(console.readLine());
            switch (op) {
                case 0:
                    salvarProduto();
                    break;
                case 1:
                    buscarProdutos();
                    break;
                case 2:
                    buscarProdutosID();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;            
                case 5:
                    System.out.println("\nFim do programa!\n");
                    break;    
                default:
                    System.out.println("\nOpção inválida!\n");
                    break;
            }
        } while (op != 5);
    }

    private static void exibirMenu(){
        System.out.println("\nMENU:\n");
        System.out.println("0. Salvar novo produto");
        System.out.println("1. Buscar todos os produtos");
        System.out.println("2. Buscar produto por ID");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Excluir produto");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void salvarProduto() {
        System.out.println("\n###Criar Novo Produto###\n");

        System.out.print("Nome do produto: ");
        String nome = console.readLine();
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(console.readLine());
        System.out.print("Valor do produto: ");
        Double vlr = Double.parseDouble(console.readLine());

        Produto produto = new Produto(nome, qtd, vlr);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.salvar(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void buscarProdutos(){
        System.out.println("\n###Buscar Todos os Produtos###");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = produtoDAO.buscarTodos();

            if (produtos != null) {
                System.out.println("\nLista de Produtos:\n");

                for (Produto produto : produtos) {
                    System.out.println("ID: " + produto.id());
                    System.out.println("Nome: " + produto.nome());
                    System.out.println("Quantidade: " + produto.quantidade());
                    System.out.println("Valor: R$ " + produto.valor());
                    System.out.println("-----------------------");
                }
            }else{
                System.out.println("Produto não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarProdutosID(){
        System.out.println("\n###Buscar Produtos por ID###");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        System.out.print("Digite o ID do produto: ");
        Long id = Long.parseLong(console.readLine());

        try {
            Produto produto = produtoDAO.buscarPorID(id);
            if (produto!=null) {
                System.out.println("\nProduto encontrado: ");
                System.out.println("ID: " + produto.id());
                System.out.println("Nome: " + produto.nome());
                System.out.println("Quantidade: " + produto.quantidade());
                System.out.println("Valor: R$ " + produto.valor());
            }else{
                System.out.println("\nProduto não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void atualizarProduto(){
        System.out.println("\n###Atualziar Produto###");

        System.out.print("Digite o ID do produto que deseja atualizar: ");
        Long id = Long.parseLong(console.readLine());

        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            Produto produtoExistente = produtoDAO.buscarPorID(id);
            if (produtoExistente!=null) {
                System.out.printf("Novo nome (atual: %s): ", produtoExistente.nome());
                String nome = console.readLine();
                System.out.printf("Nova Quantidade (atual: %d): ", produtoExistente.quantidade());
                int qtd = Integer.parseInt(console.readLine());
                System.out.printf("Novo Valor do produto: (atual: R$ %.2f): ", produtoExistente.valor());
                Double vlr = Double.parseDouble(console.readLine());

                Produto produtoAtualizado = new Produto(id, nome, qtd, vlr);
                try {
                    produtoDAO.atualizar(produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                System.out.println("\nProduto não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void excluirProduto(){
        System.out.println("\n###Excluir Produto###");

        System.out.print("\nDigite o ID do produto a ser excluído: ");
        Long id = Long.parseLong(console.readLine());

        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            Produto produtoExistente = produtoDAO.buscarPorID(id);
            if (produtoExistente != null) {
                try {
                    produtoDAO.excluir(produtoExistente.id());
                    System.out.println("\nProduto excluído com sucesso!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("\nProduto não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}