package Biblioteca;

import java.io.Serial;

public class LivroCulinario extends Livro {
    @Serial
    private static final long serialVersionUID = 1L;
    private String tipoCulinario;

    public LivroCulinario(String nome, String isbn, String autor, String tipoCulinario) {
        super(nome, isbn, autor);
        this.tipoCulinario = tipoCulinario;
    }

    @Override
    public String toString() {
        return super.toString() +
                "TipoCulinario: " + tipoCulinario + '\n';
    }

    public String getTipoCulinario() {
        return tipoCulinario;
    }

    public void setTipoCulinario(String tipoCulinario) {
        this.tipoCulinario = tipoCulinario;
    }
}
