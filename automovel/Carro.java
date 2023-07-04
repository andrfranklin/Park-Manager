package automovel;

import java.time.Duration;
import java.time.LocalDateTime;

import pessoa.Cliente;

public class Carro extends Automovel {

    public Carro(String placa, LocalDateTime entrada, Cliente cliente) {
        super(placa, entrada, cliente);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double calculaValorEstacionamento(double preco) {
        Duration duracao = Duration.between(entrada, LocalDateTime.now());
        return preco * duracao.toHours() + tax;
    }

}
