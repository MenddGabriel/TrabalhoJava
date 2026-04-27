package tools.paineis;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PainelGeradorSenha extends JPanel {

    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS    = "0123456789";
    private static final String SIMBOLOS   = "!@#$%&*?+-";

    private static final SecureRandom random = new SecureRandom();

    // Componentes
    private final JCheckBox chkMaiusculas;
    private final JCheckBox chkMinusculas;
    private final JCheckBox chkNumeros;
    private final JCheckBox chkSimbolos;
    private final JSpinner spTamanho;
    private final JTextField txtSenhaGerada;
    private final JButton btnGerar;

    public PainelGeradorSenha() {
        setBorder(BorderFactory.createTitledBorder("Gerador de Senha"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel coluna1 = new JPanel(new GridLayout(2, 1, 0, 5));
        chkMaiusculas = new JCheckBox("Maiúsculas", true);
        chkMinusculas = new JCheckBox("Minúsculas", true);
        coluna1.add(chkMaiusculas);
        coluna1.add(chkMinusculas);

        // Coluna 2: Números e Símbolos
        JPanel coluna2 = new JPanel(new GridLayout(2, 1, 0, 5));
        chkNumeros    = new JCheckBox("Números", false);
        chkSimbolos   = new JCheckBox("Símbolos", false);
        coluna2.add(chkNumeros);
        coluna2.add(chkSimbolos);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        add(coluna1, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 10;
        add(coluna2, gbc);

        // ── Linha 2: Tamanho, spinner e botão Gerar ──────────────────────────
        JPanel linhaTamanho = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        linhaTamanho.add(new JLabel("Tamanho"));

        spTamanho = new JSpinner(new SpinnerNumberModel(8, 4, 32, 1));
        spTamanho.setPreferredSize(new Dimension(60, 25));
        linhaTamanho.add(spTamanho);

        btnGerar = new JButton("Gerar");
        btnGerar.setPreferredSize(new Dimension(80, 28));
        linhaTamanho.add(btnGerar);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        add(linhaTamanho, gbc);

        // ── Linha 3: Campo da senha gerada ──────────────────────────────────
        txtSenhaGerada = new JTextField();
        txtSenhaGerada.setEditable(false);
        txtSenhaGerada.setFont(new Font("Monospaced", Font.BOLD, 14));
        txtSenhaGerada.setHorizontalAlignment(JTextField.CENTER);
        txtSenhaGerada.setPreferredSize(new Dimension(300, 30));

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSenhaGerada, gbc);

        btnGerar.addActionListener(e -> gerarSenha());

        gerarSenha();
    }

    private void gerarSenha() {
        // Verifica se pelo menos uma opção está marcada
        if (!chkMaiusculas.isSelected() && !chkMinusculas.isSelected() &&
                !chkNumeros.isSelected() && !chkSimbolos.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione pelo menos um tipo de caractere!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            txtSenhaGerada.setText("");
            return;
        }

        int tamanho = (Integer) spTamanho.getValue();

        // Monta lista dos tipos selecionados
        List<String> tiposSelecionados = new ArrayList<>();
        if (chkMaiusculas.isSelected()) tiposSelecionados.add(MAIUSCULAS);
        if (chkMinusculas.isSelected()) tiposSelecionados.add(MINUSCULAS);
        if (chkNumeros.isSelected())    tiposSelecionados.add(NUMEROS);
        if (chkSimbolos.isSelected())   tiposSelecionados.add(SIMBOLOS);

        if (tamanho < tiposSelecionados.size()) {
            tamanho = tiposSelecionados.size();
            spTamanho.setValue(tamanho);
            JOptionPane.showMessageDialog(this,
                    "O tamanho mínimo foi ajustado para " + tamanho +
                            " pois você selecionou " + tiposSelecionados.size() + " tipos de caracteres.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

        // GARANTE: pelo menos 1 caractere de CADA tipo selecionado
        StringBuilder senha = new StringBuilder();

        // Passo 1: adiciona 1 caractere de cada tipo selecionado
        for (String tipo : tiposSelecionados) {
            int indice = random.nextInt(tipo.length());
            senha.append(tipo.charAt(indice));
        }

        // Passo 2: completa o restante com caracteres aleatórios de todos os tipos
        String todosCaracteres = concatenarTipos(tiposSelecionados);
        int restante = tamanho - tiposSelecionados.size();

        for (int i = 0; i < restante; i++) {
            int indice = random.nextInt(todosCaracteres.length());
            senha.append(todosCaracteres.charAt(indice));
        }

        // Passo 3: embaralha a senha para não ficar com os tipos agrupados
        String senhaEmbaralhada = embaralhar(senha.toString());

        txtSenhaGerada.setText(senhaEmbaralhada);
    }

    private String concatenarTipos(List<String> tipos) {
        StringBuilder sb = new StringBuilder();
        for (String tipo : tipos) {
            sb.append(tipo);
        }
        return sb.toString();
    }

    private String embaralhar(String texto) {
        List<Character> caracteres = new ArrayList<>();
        for (char c : texto.toCharArray()) {
            caracteres.add(c);
        }
        Collections.shuffle(caracteres, random);

        StringBuilder sb = new StringBuilder();
        for (char c : caracteres) {
            sb.append(c);
        }
        return sb.toString();
    }

    public void limpar() {
        chkMaiusculas.setSelected(true);
        chkMinusculas.setSelected(true);
        chkNumeros.setSelected(false);
        chkSimbolos.setSelected(false);
        spTamanho.setValue(8);
        gerarSenha();
    }
}