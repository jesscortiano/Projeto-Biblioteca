package Biblioteca;

import java.io.Serial;

public class LivroInfantil extends Livro {
    @Serial
    private static final long serialVersionUID = 1L;
    private String brinde;

    public LivroInfantil(String nome, String isbn, String autor, String brinde) {
        super(nome, isbn, autor);
        this.brinde = brinde;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Brinde: "  + brinde + '\n';
    }

    public String getBrinde() {
        return brinde;
    }

    public void setBrinde(String brinde) {
        this.brinde = brinde;
    }
}
