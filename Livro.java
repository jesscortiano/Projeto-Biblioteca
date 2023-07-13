package Biblioteca;

import java.io.Serial;
import java.io.Serializable;

public abstract class Livro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nome;
    private String isbn;
    private String autor;

    public Livro(String nome, String isbn, String autor) {
        this.nome = nome;
        this.isbn = isbn;
        this.autor = autor;

    }

    @Override
    public String toString() {
        return "Nome: " + nome + '\n' +
                "ISBN: " + isbn + '\n' +
                "Autor: " + autor + '\n';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
