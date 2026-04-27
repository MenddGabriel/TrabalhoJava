package tools.paineis;

import tools.utils.Calculos;

import javax.swing.*;
import java.awt.*;

public class PainelDescontoPago extends JPanel {
    private final JTextField txtA = new JTextField("650,00", 10);
    private final JTextField txtB = new JTextField("123,00", 10);
    private final JTextField txtResultado = new JTextField("81,08", 10);

    public PainelDescontoPago() {
        setBorder(BorderFactory.createTitledBorder("Valor era A e paguei B, qual foi o desconto%?"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(label("Valor original (a)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtA.setBackground(new Color(255, 200, 200));
        add(txtA, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(label("Valor c/desconto (b)", Color.BLUE), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtB.setBackground(new Color(200, 220, 255));
        add(txtB, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(label("% desconto", Color.BLACK), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPanel pnlR = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        txtResultado.setEditable(false);
        pnlR.add(txtResultado);
        pnlR.add(new JLabel("%"));
        add(pnlR, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JLabel formula = new JLabel("v = ((a - b) / a) * 100");
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
            txtResultado.setText(String.format("%.2f", Calculos.descontoPago(a, b)).replace(".", ","));
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
