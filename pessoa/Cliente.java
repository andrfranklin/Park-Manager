package pessoa;

import java.util.Random;

import interfaces.Pagamento;

public class Cliente extends Pessoa implements Pagamento {
    private String code;

    public Cliente(String nome, String email, String telefone) {
        super(nome, email, telefone);
        this.code = this.generateCode();
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(6);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // Gera um número aleatório entre 0 e 9
            sb.append(digito);
        }

        return sb.toString();
    }

    @Override
    public double realizarPagamento(double valor) {
        return valor; // simula o pagamento do estacionamento pelo cliente
    }

    public String getCode() {
        return code;
    }

}
