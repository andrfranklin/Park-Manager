package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import estacionamento.Estacionamento;

public class MainGUI extends JFrame implements ActionListener {
    private Estacionamento park;

    public MainGUI(Estacionamento estacionamento) {
        this.park = estacionamento;
        createPainel();
    }

    public MainGUI() {
        this.park = new Estacionamento(1.5, 1.2, 2, 1.1);
        createPainel();
    }

    private void createPainel() {
        setTitle("Gerenciador de Estacionamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Crie os componentes da tela inicial
        JButton registrarEntradaButton = new JButton("Registrar Entrada de Veículo");
        registrarEntradaButton.setActionCommand("entrada");
        registrarEntradaButton.addActionListener(this);

        JButton registrarSaidaButton = new JButton("Registrar Saída de Veículo");
        registrarSaidaButton.setActionCommand("saida");
        registrarSaidaButton.addActionListener(this);

        JButton obterBalancoCarrosButton = new JButton("Obter Balanço de Carros");
        obterBalancoCarrosButton.setActionCommand("balancoCarros");
        obterBalancoCarrosButton.addActionListener(this);

        JButton obterBalancoMotosButton = new JButton("Obter Balanço de Motos");
        obterBalancoMotosButton.setActionCommand("balancoMotos");
        obterBalancoMotosButton.addActionListener(this);

        JButton obterLucroDiaButton = new JButton("Obter Lucro do Dia");
        obterLucroDiaButton.setActionCommand("lucroDia");
        obterLucroDiaButton.addActionListener(this);

        JButton obterLucroPeriodoButton = new JButton("Obter Lucro do Período");
        obterLucroPeriodoButton.setActionCommand("lucroPeriodo");
        obterLucroPeriodoButton.addActionListener(this);

        JButton novoDiaButton = new JButton("Iniciar Novo Dia");
        novoDiaButton.setActionCommand("novoDia");
        novoDiaButton.addActionListener(this);

        // Crie o painel da tela inicial
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.add(registrarEntradaButton);
        panel.add(registrarSaidaButton);
        panel.add(obterBalancoCarrosButton);
        panel.add(obterBalancoMotosButton);
        panel.add(obterLucroDiaButton);
        panel.add(obterLucroPeriodoButton);
        panel.add(novoDiaButton);

        add(panel);
    }

    private void obterBalancoCarros() {
        int quantidadeCarros = park.quantidadeDeCarrosDia();
        String mensagem = "A quantidade de carros que já passaram hoje no seu estacionamento foi de "
                + quantidadeCarros;

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Balanço de Carros",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void obterBalancoMotos() {
        int quantidadeMotos = park.quantidadeDeMotoDia();
        String mensagem = "A quantidade de motos que já passaram hoje no seu estacionamento foi de "
                + quantidadeMotos;

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Balanço de Motos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void obterLucroDia() {
        double lucroDia = park.obterLucroTotalDia();
        String mensagem = "O lucro total do dia até o momento é de " + lucroDia;

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Lucro do Dia",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void obterLucroPeriodo() {
        double lucroPeriodo = park.obterLucroTotalPeriodo();
        String mensagem = "O lucro total do período até o momento é de " + lucroPeriodo;

        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Lucro do Dia",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void novoDia() {
        park.novoDia();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "entrada":
                RegistroEntradaFrame telaRegistroEntrada = new RegistroEntradaFrame(park);
                telaRegistroEntrada.setVisible(true);
                dispose();
                break;
            case "saida":
                RegistroSaidaFrame telaRegistroSaida = new RegistroSaidaFrame(park);
                telaRegistroSaida.setVisible(true);
                dispose();
                break;
            case "balancoCarros":
                obterBalancoCarros();
                break;
            case "balancoMotos":
                obterBalancoMotos();
                break;
            case "lucroDia":
                obterLucroDia();
                break;
            case "lucroPeriodo":
                obterLucroPeriodo();
                break;
            case "novoDia":
                novoDia();
                break;
        }
    }
}