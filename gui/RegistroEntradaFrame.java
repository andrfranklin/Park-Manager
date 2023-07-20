package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automovel.Automovel;
import automovel.Carro;
import automovel.Moto;
import estacionamento.Estacionamento;
import exceptions.AutomovelDuplicadoException;
import pessoa.Cliente;

public class RegistroEntradaFrame extends JFrame {
    private JTextField placaField;
    private JTextField nomeField;
    private JTextField telefoneField;
    private JComboBox<String> tipoVeiculoComboBox;
    private JButton registrarButton;
    private Estacionamento estacionamento;

    public RegistroEntradaFrame(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;

        setTitle("Registro de Entrada de Veículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Crie os componentes do formulário
        JLabel placaLabel = new JLabel("Placa:");
        placaField = new JTextField(10);
        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        nomeField = new JTextField(20);
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneField = new JTextField(10);
        JLabel tipoVeiculoLabel = new JLabel("Tipo de Veículo:");
        tipoVeiculoComboBox = new JComboBox<>(new String[] { "Carro", "Moto" });
        registrarButton = new JButton("Registrar");

        // Adicione os componentes ao painel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.add(placaLabel);
        panel.add(placaField);
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(telefoneLabel);
        panel.add(telefoneField);
        panel.add(tipoVeiculoLabel);
        panel.add(tipoVeiculoComboBox);
        panel.add(new JLabel()); // Espaçamento vazio
        panel.add(registrarButton);

        add(panel);

        // Adicione o ouvinte de ação ao botão de registro
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEntradaVeiculo();
            }
        });
    }

    private void registrarEntradaVeiculo() {
        String placa = placaField.getText();
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String tipoVeiculo = (String) tipoVeiculoComboBox.getSelectedItem();

        // Realize a validação dos campos (exemplo: verifique se estão preenchidos)

        if (placa.isEmpty() || nome.isEmpty() || tipoVeiculo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome.trim(), placa.trim(), telefone.trim());
        try {
            Automovel auto = estacionamento.verificaPLaca(placa);
            if (auto != null) {
                throw new AutomovelDuplicadoException("Automovel duplicado");
            }
            if (tipoVeiculo.equals("Carro")) {
                Carro carro = new Carro(placa, LocalDateTime.now(), cliente);
                estacionamento.adicionarCarro(carro);
                JOptionPane.showMessageDialog(
                        this,
                        "Entrada de veículo registrada com sucesso",
                        "Registro de Entrada",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (tipoVeiculo.equals("Moto")) {
                Moto moto = new Moto(placa, LocalDateTime.now(), cliente);

                estacionamento.adicionarMoto(moto);
                JOptionPane.showMessageDialog(
                        this,
                        "Entrada de veículo registrada com sucesso",
                        "Registro de Entrada",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            // Exiba a tela inicial novamente
            MainGUI mainGUI = new MainGUI(estacionamento);
            mainGUI.setVisible(true);
            dispose(); // Feche a tela de registro de entrada
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro de Registro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
