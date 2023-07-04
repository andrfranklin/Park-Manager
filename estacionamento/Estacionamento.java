package estacionamento;

import java.util.ArrayList;

import automovel.Automovel;
import automovel.Carro;
import automovel.Moto;

public class Estacionamento {
    private ArrayList<Automovel> automoveis;
    private double taxaCarro;
    private double taxaMoto;
    private double lucroTotalDia;
    private double lucroTotalPeriodo;
    private int totalCarroDia;
    private int totalMotoDia;
    private double precoCarro;
    private double precoMoto;

    public Estacionamento(double taxaCarro, double taxaMoto, double precoCarro, double precoMoto) {
        this.automoveis = new ArrayList<>();
        this.lucroTotalDia = 0;
        this.taxaCarro = taxaCarro;
        this.taxaMoto = taxaMoto;
        this.precoCarro = precoCarro;
        this.precoMoto = precoMoto;
    }

    public void adicionarCarro(Carro carro) {
        carro.setTax(taxaCarro);
        try {
            this.adicionarAutomovel(carro);
            totalCarroDia++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarMoto(Moto moto) {
        moto.setTax(taxaMoto);
        try {
            this.adicionarAutomovel(moto);
            totalMotoDia++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerCarro(String placa) {
        try {
            Automovel auto = this.removerAutomovel(placa);
            double custo = auto.calculaValorEstacionamento(precoCarro);
            lucroTotalDia += auto.getCliente().realizarPagamento(custo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerMoto(String placa) {
        try {
            Automovel auto = this.removerAutomovel(placa);
            double custo = auto.calculaValorEstacionamento(precoMoto);
            lucroTotalDia += auto.getCliente().realizarPagamento(custo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adicionarAutomovel(Automovel automovel) {
        automoveis.add(automovel);
    }

    private Automovel removerAutomovel(String placa) {
        Automovel auto = verificaPLaca(placa);
        if (auto != null) {
            this.automoveis.remove(auto);
        }
        return auto;
    };

    public void novoDia() {
        this.lucroTotalPeriodo += lucroTotalDia;
        this.lucroTotalDia = 0;
        this.totalCarroDia = 0;
        this.totalMotoDia = 0;
    }

    public double obterLucroTotalDia() {
        return this.lucroTotalDia;
    }

    public double obterLucroTotalPeriodo() {
        return this.lucroTotalPeriodo;
    }

    public int quantidadeDeCarrosDia() {
        return this.totalCarroDia;
    }

    public int quantidadeDeMotoDia() {
        return this.totalMotoDia;
    }

    public Automovel verificaPLaca(String placa) {
        for (Automovel auto : automoveis) {
            if (auto.getPlaca().equals(placa)) {
                return auto;
            }
        }
        return null;
    }
}
