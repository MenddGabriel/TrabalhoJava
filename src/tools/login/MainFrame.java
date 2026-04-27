package tools.login;

import tools.paineis.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final PainelDesconto      painelDesconto      = new PainelDesconto();
    private final PainelIncremento    painelIncremento    = new PainelIncremento();
    private final PainelAmostragem    painelAmostragem    = new PainelAmostragem();
    private final PainelAmostragem2   painelAmostragem2   = new PainelAmostragem2();
    private final PainelDescontoPago  painelDescontoPago  = new PainelDescontoPago();
    private final PainelVariacaoDelta painelVariacaoDelta = new PainelVariacaoDelta();
    private final PainelValorOriginal painelValorOriginal = new PainelValorOriginal();
    private final PainelRegraDeTres   painelRegraDeTres   = new PainelRegraDeTres();
    private final PainelGeradorSenha  painelGeradorSenha  = new PainelGeradorSenha();

    public MainFrame() {
        limparTudo();
        setTitle("Trabalho");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ── Barra de abas ──────────────────────────────────────────────────
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Cálculo", buildAbaCalculo());

        add(abas, BorderLayout.CENTER);

        // ── Barra inferior com botão Sair ──────────────────────────────────
        JPanel barraInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            LoginFrame.encerrarSessao();
            dispose();
            new LoginFrame().setVisible(true);
        });
        barraInferior.add(btnSair);
        add(barraInferior, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /** Monta a aba principal "Cálculo" com todos os painéis */
    private JPanel buildAbaCalculo() {
        JPanel aba = new JPanel(new BorderLayout(6, 6));
        aba.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ── Linha 1: 4 caixas de porcentagem ──────────────────────────────
        JPanel linha1 = new JPanel(new GridLayout(1, 4, 6, 6));
        linha1.add(painelDesconto);
        linha1.add(painelIncremento);
        linha1.add(painelAmostragem);
        linha1.add(painelAmostragem2);

        // ── Linha 2: 3 caixas + botão Limpar ──────────────────────────────
        JPanel linha2 = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(0, 0, 0, 6);
        g.fill = GridBagConstraints.BOTH;
        g.weighty = 1;

        g.gridx = 0; g.weightx = 1;
        linha2.add(painelDescontoPago, g);
        g.gridx = 1;
        linha2.add(painelVariacaoDelta, g);
        g.gridx = 2;
        linha2.add(painelValorOriginal, g);

        // Painel com botão Limpar no canto inferior direito
        JPanel pnlBotao = new JPanel(new BorderLayout());
        JButton btnLimpar = new JButton("Limpar Campos");
        btnLimpar.addActionListener(e -> limparTudo());
        pnlBotao.add(btnLimpar, BorderLayout.SOUTH);
        g.gridx = 3; g.weightx = 0.3;
        linha2.add(pnlBotao, g);

        // ── Linha 3: Regra de três + Datas ────────────────────────────────
        JPanel linha3 = new JPanel(new GridLayout(1, 2, 6, 6));
        linha3.add(painelRegraDeTres);
        linha3.add(painelGeradorSenha);

        // ── Empilha as linhas ─────────────────────────────────────────────
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(linha1);
        centro.add(Box.createVerticalStrut(6));
        centro.add(linha2);
        centro.add(Box.createVerticalStrut(6));
        centro.add(linha3);

        aba.add(centro, BorderLayout.CENTER);
        return aba;
    }

    private void limparTudo() {
        painelDesconto.limpar();
        painelIncremento.limpar();
        painelAmostragem.limpar();
        painelAmostragem2.limpar();
        painelDescontoPago.limpar();
        painelVariacaoDelta.limpar();
        painelValorOriginal.limpar();
        painelRegraDeTres.limpar();
        painelGeradorSenha.limpar();
    }
}