package FAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FAG.Objetos.Produto;


public class Principal {

    static int qtdNota50 = 5;
    static int qtdNota20 = 5;
    static int qtdNota10 = 5;

    static List<Produto> Estoque = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    
    public static void main(String[] args) {
    	
        iniciaEstoque();
        menu();
        
    }

    
    public static void iniciaEstoque() {
    	
        Produto produtoUm = new Produto("Produto A", 25.00, 10);
        Produto produtoDois = new Produto("Produto B", 50.00, 5);
        Produto produtoTres = new Produto("Produto C", 30.00, 8);

        Estoque.add(produtoUm);
        Estoque.add(produtoDois);
        Estoque.add(produtoTres);
        
    }

    
    
    public static void menu() {
    	
        int opcao = -1;

        do {
            System.out.println("=== Menu ===");
            System.out.println("(1) Ver estoque");
            System.out.println("(2) Cadastrar Produto");
            System.out.println("(3) Vender");
            System.out.println("(4) Consultar saldo");
            System.out.println("(0) Sair");
            System.out.println("============");
            System.out.printf("Escolha uma opcao: ");

            opcao = scan.nextInt();
            scan.nextLine();
            validaOpcao(opcao);

        } while (opcao != 0);
        
    }

    
    
    public static void validaOpcao(int opcao) {
    	
        if (opcao == 1) {
            listarEstoque();
        } else if (opcao == 2) {
            novoProduto();
        } else if (opcao == 3) {
            vender();
        } else if (opcao == 4) {
            consultaSaldo();
        } else if (opcao == 0) {
        	sair();
        } else {
            System.out.println("======================");
            System.out.println("Opcao invalida!");
            return;
        }
        
    }

    
    
    public static void listarEstoque() {
    	
        System.out.println("\n=== Mostrando Estoque ===");

        for (int i = 0; i < Estoque.size(); i++) {
            System.out.printf("ID: %d ", i + 1);
            Estoque.get(i).produtoInfo();
        }

        System.out.println("=========================\n");
        
    }

    
    
    public static boolean validaNome(String nome) {
    	
        boolean existente = false;

        for (int i = 0; i < Estoque.size(); i++) {
            if (Estoque.get(i).getNome().equalsIgnoreCase(nome)) {
                existente = true;
                break;
            }
        }

        return existente;
        
    }

    
    
    public static void atualizaProduto(String nome) {
    	
        int opcao = -1;
        int novoEstoque = 0;
        double novoPreco = 0;
        int ID = 0;

        System.out.println("\n=== Produto ja Cadastrado ===");
        System.out.println("(1) Atualizar Estoque");
        System.out.println("(2) Atualizar Preco");
        System.out.println("(0) Voltar ao Menu");
        System.out.println("============");
        System.out.printf("Escolha uma opcao: ");

        opcao = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < Estoque.size(); i++) {
            if (Estoque.get(i).getNome().equalsIgnoreCase(nome)) {
                ID = i;
            }
        }

        if (opcao == 1) {
            int estoqueAnt = Estoque.get(ID).getQtdEstoque();

            System.out.println("============");
            System.out.printf("Informe o novo Estoque: ");

            novoEstoque = scan.nextInt();
            scan.nextLine();

            if (novoEstoque < estoqueAnt) {
                System.out.println("\n========================");
                System.out.println("A nova quantidade nao pode ser Menor que a atual!");
                System.out.println("========================\n");

            } else {
                Estoque.get(ID).setQtdEstoque(novoEstoque);
                System.out.println("\n========================");
                System.out.println("Estoque atualizado com Sucesso!");
                System.out.println("========================\n");
            }

        } else if (opcao == 2) {
            System.out.println("============");
            System.out.printf("Informe o novo Preco: ");

            novoPreco = scan.nextDouble();
            scan.nextLine();

            Estoque.get(ID).setPreco(novoPreco);
            System.out.println("\n========================");
            System.out.println("Preco atualizado com Sucesso!");
            System.out.println("========================\n");
        }
        
    }

    
    
    public static void novoProduto() {
    	
        Produto novoProduto = new Produto();

        System.out.println("\n=== Cadastrar novo Produto ===");
        System.out.printf("Informe o nome do Produto: ");
        String nome = scan.nextLine();
        novoProduto.setNome(nome);

        if (!validaNome(nome)) {
            System.out.printf("Informe o valor do Produto: ");
            novoProduto.setPreco(scan.nextDouble());

            System.out.printf("Informe a quantidade em Estoque: ");
            novoProduto.setQtdEstoque(scan.nextInt());
            scan.nextLine();

            Estoque.add(novoProduto);

            System.out.println("\n================================");
            System.out.println("Produto cadastrado com Sucesso! ");
            System.out.println("================================\n");

        } else {
            atualizaProduto(nome);
        }
        
    }
    
    

    public static void calculaTroco(double total, int ID, int qtdVendida) {
    	
        int qtd50, qtd20, qtd10;
        double totalRecebido;

        System.out.print("Informe a quantidade de notas de 50: ");
        qtd50 = scan.nextInt();

        System.out.print("Informe a quantidade de notas de 20: ");
        qtd20 = scan.nextInt();

        System.out.print("Informe a quantidade de notas de 10: ");
        qtd10 = scan.nextInt();

     
        qtdNota50 += qtd50;
        qtdNota20 += qtd20;
        qtdNota10 += qtd10;
        
        
        totalRecebido = (qtd50 * 50) + (qtd20 * 20) + (qtd10 * 10);

        System.out.println("\n======================");
        System.out.printf("Total recebido: R$ %.2f%n", totalRecebido);

        double troco = totalRecebido - total;

        if (troco < 0) {
            System.out.println("Valor insuficiente!");
            System.out.println("======================\n");
            return;
        } else if (troco == 0) {
            System.out.println("Pagamento exato. Sem troco.");
            System.out.println("======================\n");
            baixarEstoque(ID, qtdVendida);
            return;
        }

        int notas50 = (int) (troco / 50);
        troco %= 50;

        int notas20 = (int) (troco / 20);
        troco %= 20;

        int notas10 = (int) (troco / 10);
        troco %= 10;

        if (troco > 0) {
            System.out.println("\n======================");
            System.out.println("Nao é possível realizar o pagamento!");
            System.out.println("O troco não pode ser dado apenas com notas de 10, 20 e 50.");
            System.out.println("Compra cancelada.");
            System.out.println("======================\n");
            return;
        }

        System.out.println("======================");
        System.out.println("Distribuição do troco:");
        System.out.println("Notas de 50: " + notas50);
        System.out.println("Notas de 20: " + notas20);
        System.out.println("Notas de 10: " + notas10);
        System.out.println("======================\n");

        qtdNota50 -= notas50;
        qtdNota20 -= notas20;
        qtdNota10 -= notas10;

        baixarEstoque(ID, qtdVendida);

        
        System.out.println("Compra finalizada com sucesso!");
    }
    
    

    public static void baixarEstoque(int ID, int qtd) {
    	
        int qtdAtual = Estoque.get(ID-1).getQtdEstoque();
        Estoque.get(ID-1).setQtdEstoque(qtdAtual - qtd);
        
    }
    
    

    public static void vender() {
    	
        int ID = 0;
        int qtd = 0;

        listarEstoque();

        System.out.println("=== Realizar venda ===");
        System.out.printf("Informe o ID do Produto: ");

        ID = scan.nextInt();
        scan.nextLine();

        System.out.printf("Informe a Quantidade: ");
        qtd = scan.nextInt();
        scan.nextLine();

        String nome = Estoque.get(ID - 1).getNome();
        double preco = Estoque.get(ID - 1).getPreco();
        double total = preco * qtd;

        if (Estoque.get(ID - 1).getQtdEstoque() < qtd) {
            System.out.println("\n======================");
            System.out.println("Quantidade maior que a disponivel!");
            System.out.println("======================\n");
            return;
        }

        System.out.println("\n======================");
        System.out.printf("Produto: %s\n", nome);
        System.out.printf("Quantidade: %d\n", qtd);
        System.out.printf("Preco UND: %.2f\n", preco);
        System.out.printf("Total: %.2f\n", total);
        System.out.println("======================\n");

        calculaTroco(total, ID, qtd);
        
    }

    
    
    public static void consultaSaldo() {
    	
        double total50 = qtdNota50 * 50;
        double total20 = qtdNota20 * 20;
        double total10 = qtdNota10 * 10;

        double total = total50 + total20 + total10;

        System.out.println("======================");
        System.out.println("Notas no caixa:");
        System.out.println("Notas de 50: " + qtdNota50);
        System.out.println("Notas de 20: " + qtdNota20);
        System.out.println("Notas de 10: " + qtdNota10);
        System.out.println("======================");
        System.out.printf("Total: %.2f\n", total);
        System.out.println("======================\n");
        
    }
    
    
    
    public static void sair() {

        System.out.println("======================");
        System.out.println("Logout efetuado com Sucesso!");
        System.out.println("Até mais 👋");
        System.out.println("======================");
        
    }
    
    
    
}
