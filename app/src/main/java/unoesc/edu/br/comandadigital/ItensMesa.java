package unoesc.edu.br.comandadigital;

/**
 * Created by root on 04/10/15.
 */
public class ItensMesa {

    private int id;
    private int nume_mesa;
    private String produto;
    private double valor;
    private int categoria;
    private String enviado;
    private String mesa_status;
    private Double sum;

    public ItensMesa(){

    }

    public ItensMesa(int id, int nume_mesa, String produto, double valor,
                     int categoria, String enviado, String mesa_status, Double sum) {
        super();
        this.id = id;
        this.nume_mesa = nume_mesa;
        this.produto = produto;
        this.valor = valor;
        this.categoria = categoria;
        this.enviado = enviado;
        this.mesa_status = mesa_status;
        this.sum = sum;
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public  String toString() {


        return  '\n'+"Mesa nº:"+nume_mesa +'\n'+ produto + "R$: " + valor;




    }

}

