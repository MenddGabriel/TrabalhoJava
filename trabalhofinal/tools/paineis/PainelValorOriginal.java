package tools.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelValorOriginal extends JPanel {
    private final JTextField txtA = new JTextField("41,00", 10);
    private final JTextField txtB = new JTextField("2,00", 10);
    private final JTextField txtResultado = new JTextField("41,84", 10);

    public PainelValorOriginal() {
        setBorder(BorderFactory.createTitledBorder("Qual era o valor original?"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(label("Valor final R$ (a)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtA, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(label("% desconto (b)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPanel pnlB = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        pnlB.add(txtB);
        pnlB.add(new JLabel("%"));
        add(pnlB, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(label("Valor inicial", Color.BLACK), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtResultado.setEditable(false);
        add(txtResultado, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.WEST;

        DocumentAdapter calc = e -> calcular();
        txtA.getDocument().addDocumentListener(calc);
        txtB.getDocument().addDocumentListener(calc);
    }

    private void calcular() {
        try {
            double a = Double.parseDouble(txtA.getText().replace(",", "."));
            double b = Double.parseDouble(txtB.getText().replace(",", "."));
            txtResultado.setText(String.format("%.2f", Calculos.valorOriginal(a, b)).replace(".", ","));
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
