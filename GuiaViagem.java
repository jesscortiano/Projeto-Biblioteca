package Biblioteca;

import java.io.Serial;

public class GuiaViagem extends Livro {
    @Serial
    private static final long serialVersionUID = 1L;
    private String local;

    public GuiaViagem(String nome, String isbn, String autor, String local) {
        super(nome, isbn, autor);
        this.local = local;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Local:" + local + '\n';
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
