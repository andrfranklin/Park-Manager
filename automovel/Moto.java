package automovel;

import java.time.Duration;
import java.time.LocalDateTime;

import pessoa.Cliente;

public class Moto extends Automovel {
    public Moto(String placa, LocalDateTime entrada, Cliente cliente) {
        super(placa, entrada, cliente);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double calculaValorEstacionamento(double preco) {
        Duration duracao = Duration.between(entrada, LocalDateTime.now());
        Long horas = duracao.toHours();
        if (horas <= 1) {
            return 0;
        }

        return preco * (duracao.toHours() - 1);
    }

}
