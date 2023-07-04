package automovel;

import java.time.LocalDateTime;

import pessoa.Cliente;

public abstract class Automovel {
    protected String placa;
    protected LocalDateTime entrada;
    protected double tax;
    protected Cliente cliente;

    public Automovel(String placa, LocalDateTime entrada, Cliente cliente) {
        this.placa = placa;
        this.entrada = entrada;
        this.cliente = cliente;
    }

    public abstract double calculaValorEstacionamento(double preco);

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
