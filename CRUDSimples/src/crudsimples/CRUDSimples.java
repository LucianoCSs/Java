package crudsimples;
import java.sql.*;
import java.util.Scanner;


public class CRUDSimples {

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        CRUDSimples crud = new CRUDSimples();
        
        int opMenu;
        
        do{
            System.out.println("\nPrograma de Cadastro de Alunos");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            opMenu = scan.nextInt();
            System.out.println();
            
            switch(opMenu){
                case 1:
                    crud.inserir();
                    break;
                case 2:
                    crud.consulta();
                    break;
                case 3:
                    crud.atualizar();
                    break;
                case 4:
                    crud.deletar();
                    break;
                case 5:
                    System.out.println("Fim do programa.");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
                    break;
            }
        
        }while(opMenu != 5);
        
        
    }
    // método para conectar ao banco de dados
    public Connection conectar() throws ClassNotFoundException{
        Connection conn = null;
        // informações necessárias
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/banco_teste";
        String user = "usuario2024";
        String password = "1234";
        try {
            // carrega o driver
            Class.forName(driver);
            // faz a conexão
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("----------------------------------");
            //System.out.println("Conectou ao banco de dados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return conn;
    }
    // método para desconectar
    public void desconectar(){
        Connection conn = null;
        try {
            if(conn != null && !conn.isClosed()){
                conn.close();
                //System.out.println("----------------------------------");
                //System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    // método para consulta
    public void consulta() throws ClassNotFoundException{
        CRUDSimples crud = new CRUDSimples();
        Connection conn = crud.conectar();
        //conectar();
        try {
            // consulta SQL
            String consulta = "SELECT * FROM alunos";
            // prepara a consulta, por meio da conexão (conn)
            Statement stm = conn.createStatement();
            // executa a consulta, obtendo um conjunto de resultados (ResultSet)
            ResultSet resultado = stm.executeQuery(consulta);
            
            //itera pelo conjunto de resultados, perguntando se tem um próximo (next)
            while (resultado.next()) {
                System.out.println("----------------------------------");
                System.out.println("ID do Aluno: " + resultado.getInt("id_aluno"));
                System.out.println("Nome: " +resultado.getString("nome"));
                System.out.println("Nota: " + resultado.getFloat("nota"));
                /*
                O método next() retorna true, caso ainda existam novas linhas na resposta
                e false quando não tem mais registros para percorrer.
                */
            }
            
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }finally{
            desconectar();
        }
    }
    
    public void inserir() throws ClassNotFoundException{
        CRUDSimples crud = new CRUDSimples();
        Connection conn = crud.conectar();
        
        Scanner scan = new Scanner(System.in);
        String n;
        float nt;
        
        try {
            System.out.print("Digite o nome do aluno: ");
            n = scan.nextLine();
            System.out.print("Digite a nota do aluno: ");
            nt = scan.nextFloat();
            String adicionar = "INSERT INTO alunos(nome, nota) VALUES('" + n + "'" + "," + nt + ")";
            
            Statement stm = conn.createStatement();
            
            stm.execute(adicionar);
            System.out.println("Adicionado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            desconectar();
        }
    }
    
    public void atualizar() throws ClassNotFoundException{
        CRUDSimples crud = new CRUDSimples();
        Connection conn = crud.conectar();
        
        Scanner scan = new Scanner(System.in);
        String nAt;
        float ntAt;
        int id;
        int op;
        try {
            System.out.println("1 - alterar nome");
            System.out.println("2 - alterar nota");
            System.out.print("Opção: ");
            op = scan.nextInt();
            if(op == 1){
                System.out.print("Digite o ID do aluno que deseja atualizar: ");
                id = scan.nextInt();
                
                scan.nextLine(); // limpa o buffer
                
                System.out.print("Digite o nome a ser atualizado: ");
                nAt = scan.nextLine();
                String atualizacao = "UPDATE alunos SET nome = '" + nAt + "' WHERE id_aluno = " + id;
                
                Statement stm = conn.createStatement();
                stm.execute(atualizacao);
                
                System.out.println("Atualizou o nome com sucesso.");
            } else if (op == 2){
                System.out.print("Digite o ID do aluno que deseja atualizar: ");
                id = scan.nextInt();
                System.out.print("Digite a nota a ser atualizado: ");
                ntAt = scan.nextFloat();
                String atualizacao = "UPDATE alunos SET nota = " + ntAt + " WHERE id_aluno = " + id;
                
                Statement stm = conn.createStatement();
                stm.execute(atualizacao);
                
                System.out.println("Atualizou a nota com sucesso.");
            } else {
                System.out.println("Digite uma opção válida.");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            desconectar();
        }
    }
    
    public void deletar() throws ClassNotFoundException{
        CRUDSimples crud = new CRUDSimples();
        Connection conn = crud.conectar();
        
        Scanner scan = new Scanner(System.in);
        int id;
        
        try {
            System.out.print("Digite o ID do aluno a ser excluído: ");
            id = scan.nextInt();
            
            String delete = "DELETE FROM alunos WHERE id_aluno = " + id;
            
            Statement stm = conn.createStatement();
            stm.execute(delete);
            
            System.out.println("Aluno deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            desconectar();
        }
        
    }
    
}
