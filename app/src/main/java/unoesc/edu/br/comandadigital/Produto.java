package unoesc.edu.br.comandadigital;

import java.io.Serializable;

/**
 * Created by root on 17/09/15.
 */
public class Produto {
    private  int codi;
    private String descricao;
    private Double preco;
    private int categoria;


    public Produto(){

    }

    public Produto(int codi, String descricao, Double preco, int categoria) {
        super();
        this.codi = codi;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codi=" + codi +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }
}

