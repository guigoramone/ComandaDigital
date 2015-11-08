package unoesc.edu.br.comandadigital;

/**
 * Created by root on 04/10/15.
 */
public class Comanda {

    private int id;
    private int nume_mesa;
    private String produto;
    private double valor;
    private int categoria;
    private String enviado;
    private String mesa_status;

    public Comanda(){

    }

    public Comanda(int id, int nume_mesa, String produto, double valor,
                   int categoria, String enviado, String mesa_status) {
        super();
        this.id = id;
        this.nume_mesa = nume_mesa;
        this.produto = produto;
        this.valor = valor;
        this.categoria = categoria;
        this.enviado = enviado;
        this.mesa_status = mesa_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNume_mesa() {
        return nume_mesa;
    }

    public void setNume_mesa(int nume_mesa) {
        this.nume_mesa = nume_mesa;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getMesa_status() {
        return mesa_status;
    }

    public void setMesa_status(String mesa_status) {
        this.mesa_status = mesa_status;
    }


    
@Override
    public  String toString() {


        return  "Id: "+ id + '\n'+"Mesa"+nume_mesa +'\n'+ produto + "R$:"+ valor + '\n'+"Cat: "+categoria
                + '\n'+enviado + '\n'+mesa_status;




    }

}

