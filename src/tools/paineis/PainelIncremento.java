package tools.paineis;

import tools.utils.Calculos;

import javax.swing.*;
import java.awt.*;

public class PainelIncremento extends JPanel {
    private final JTextField txtA = new JTextField("100,00", 10);
    private final JTextField txtB = new JTextField("36,00", 10);
    private final JTextField txtResultado = new JTextField("136,00", 10);

    public PainelIncremento() {
        setBorder(BorderFactory.createTitledBorder("Incrementar % a um valor"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(label("Valor inicial R$ (a)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtA.setBackground(new Color(255, 200, 200));
        add(txtA, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(label("% acréscimo (b)", Color.BLUE), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPanel pnlB = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        pnlB.add(txtB);
        pnlB.add(new JLabel("%"));
        txtB.setBackground(new Color(200, 220, 255));
        add(pnlB, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(label("Resultado", Color.BLACK), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtResultado.setEditable(false);
        add(txtResultado, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JLabel formula = new JLabel("v = a + (a * (b / 100))");
        formula.setFont(new Font("Monospaced", Font.ITALIC, 11));
        formula.setForeground(Color.BLUE);
        add(formula, gbc);

        DocumentAdapter calc = e -> calcular();
        txtA.getDocument().addDocumentListener(calc);
        txtB.getDocument().addDocumentListener(calc);
    }

    private void calcular() {
        try {
            double a = Double.parseDouble(txtA.getText().replace(",", "."));
            double b = Double.parseDouble(txtB.getText().replace(",", "."));
            txtResultado.setText(String.format("%.2f", Calculos.incrementarPorcentagem(a, b)).replace(".", ","));
        } catch (NumberFormatException ignored) {
            txtResultado.setText("");
        }
    }

    public void limpar() {
        txtA.setText(""); txtB.setText(""); txtResultado.setText("");
    }

    private JLabel label(String text, Color color) {
        JLabel l = new JLabel(text);
        l.setForeground(color);
        return l;
    }
}
