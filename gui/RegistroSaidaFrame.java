package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automovel.Automovel;
import estacionamento.Estacionamento;
import exceptions.AutomovelIndefinidoException;

public class RegistroSaidaFrame extends JFrame {
    private Estacionamento estacionamento;

    public RegistroSaidaFrame(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;

        setTitle("Remover Veículo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crie os componentes da tela
        JLabel tipoLabel = new JLabel("Tipo de Veículo:");
        JComboBox<String> tipoComboBox = new JComboBox<>(new String[] { "Carro", "Moto" });
        JLabel placaLabel = new JLabel("Placa do Veículo:");
        JTextField placaField = new JTextField();
        JButton removerButton = new JButton("Remover");

        // Crie o painel da tela
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(tipoLabel);
        panel.add(tipoComboBox);
        panel.add(placaLabel);
        panel.add(placaField);

        // Adicione o painel e o botão à tela
        add(panel, BorderLayout.CENTER);
        add(removerButton, BorderLayout.SOUTH);

        // Adicione o ActionListener ao botão de remoção
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoVeiculo = (String) tipoComboBox.getSelectedItem();
                String placa = placaField.getText().trim();
                removerVeiculo(tipoVeiculo, placa);
            }
        });
    }

    private void removerVeiculo(String tipoVeiculo, String placa) {

        try {
            Automovel auto = estacionamento.verificaPLaca(placa);
            if (auto == null) {
                throw new AutomovelIndefinidoException("automovel não encontrado");
            }
            if (tipoVeiculo.equals("Carro")) {
                estacionamento.removerCarro(placa);
            } else if (tipoVeiculo.equals("Moto")) {
                estacionamento.removerMoto(placa);
            }

            JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!");
            dispose(); // Fecha a tela de remoção
            MainGUI telaPrincipal = new MainGUI(estacionamento);
            telaPrincipal.setVisible(true); // Reexibe a tela principal
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "Veículo não encontrado.");
        }
    }
}
