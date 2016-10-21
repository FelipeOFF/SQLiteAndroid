package icommerce.com.br.database.data;

import java.io.Serializable;

/**
 * Criado por Felipe de Oliveira em 11/04/16.
 * <p>
 * <p>
 * <p>
 * <p>
 * Qualquer duvida contate.
 * Tel: (011)95245-7295
 * Email: felipe-not@hotmail.com
 */
public class Produto implements Serializable {

    public static final String PRODUTO = "produto";

    private int id;
    private String nome;
    private double valor;

    public Produto(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
