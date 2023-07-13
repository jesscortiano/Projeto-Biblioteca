package Biblioteca;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Biblioteca {
    private ArrayList<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<Livro>();
    }
    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

        return dadosOut;
    }

    public LivroInfantil leLivroInfantil (){

        String [] valores = new String [4];
        String [] nomeVal = {"nome", "isbn", "autor", "brinde"};
        valores = leValores (nomeVal);

        LivroInfantil li = new LivroInfantil (valores[0],valores[1],valores[2], valores[3]);
        return li;
    }

    public LivroCulinario leLivroCulinario (){

        String [] valores = new String [4];
        String [] nomeVal = {"nome", "isbn", "autor", "tipoCulinario"};
        valores = leValores (nomeVal);

        LivroCulinario lc = new LivroCulinario (valores[0],valores[1],valores[2], valores[3]);
        return lc;
    }

    public GuiaViagem leGuiaViagem (){

        String [] valores = new String [4];
        String [] nomeVal = {"nome", "isbn", "autor", "local"};
        valores = leValores (nomeVal);

        GuiaViagem gv = new GuiaViagem (valores[0],valores[1],valores[2], valores[3]);
        return gv;
    }


    private boolean intValido(String s) {
        try {
            Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
            return true;
        } catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
            return false;
        }
    }
    public int retornaInteiro(String entrada) { // retorna um valor inteiro
        int numInt;

        //Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
        while (!this.intValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }
        return Integer.parseInt(entrada);
    }

    public void salvaLivro (ArrayList<Livro> livros){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream
                    (new FileOutputStream("c:\\temp\\Biblioteca.dados"));
            for (int i=0; i < livros.size(); i++)
                outputStream.writeObject(livros.get(i));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {  //Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Livro> recuperaLivros (){
        ArrayList<Livro> livrosTemp = new ArrayList<Livro>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream
                    (new FileInputStream("c:\\temp\\Biblioteca.dados"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Livro) {
                    livrosTemp.add((Livro) obj);
                }
            }
        } catch (EOFException ex) { // when EOF is reached
            System.out.println("Fim de arquivo.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo com livros NÃO existe!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {  //Close the ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return livrosTemp;
        }
    }

    public void menuBiblioteca (){

        String menu = "";
        String entrada;
        int    opc1, opc2;

        do {
            menu = "Controle Biblioteca\n" +
                    "Opções:\n" +
                    "1. Entrar Livros\n" +
                    "2. Exibir Livros\n" +
                    "3. Limpar Livros\n" +
                    "4. Gravar Livros\n" +
                    "5. Recuperar Livros\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog (menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:// Entrar dados
                    menu = "Entrada de Livros\n" +
                            "Opções:\n" +
                            "1. LivroInfantil\n" +
                            "2. LivroCulinario\n" +
                            "3. GuiaViagem\n";

                    entrada = JOptionPane.showInputDialog (menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2){
                        case 1: livros.add((Livro) leLivroInfantil());
                            break;
                        case 2: livros.add((Livro)leLivroCulinario());
                            break;
                        case 3: livros.add((Livro)leGuiaViagem());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Livro para entrada NÃO escolhido!");
                    }

                    break;
                case 2: // Exibir dados
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com livros primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i=0; i < livros.size(); i++)	{
                        dados += livros.get(i).toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;
                case 3: // Limpar Dados
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com livros primeiramente");
                        break;
                    }
                    livros.clear();
                    JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
                    break;
                case 4: // Grava Dados
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com livros primeiramente");
                        break;
                    }
                    salvaLivro(livros);
                    JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
                    break;
                case 5: // Recupera Dados
                    livros = recuperaLivros();
                    if (livros.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Fim do aplicativo Biblioteca");
                    break;
            }
        } while (opc1 != 9);
    }


    public static void main (String [] args){

        var app = new Biblioteca ();
        app.menuBiblioteca();

    }

}