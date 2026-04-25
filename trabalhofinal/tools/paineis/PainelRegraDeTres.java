package tools.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelRegraDeTres extends JPanel {
    private final JTextField txtA  = new JTextField("3,00", 8);
    private final JTextField txtR1 = new JTextField("2,00", 8);
    private final JTextField txtB  = new JTextField("1", 8);
    private final JTextField txtR2 = new JTextField("0,67", 8);

    public PainelRegraDeTres() {
        setBorder(BorderFactory.createTitledBorder("Regra de três"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);

        // Linha 1: a: [txtA] = r1: [txtR1]
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("a:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtA, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("= r1:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST;
        txtR1.setBackground(new Color(200, 255, 200));
        add(txtR1, gbc);

        // Linha 2: b: [txtB] = r2: [txtR2]
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("b:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtB, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("= r2:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST;
        txtR2.setEditable(false);
        add(txtR2, gbc);

        // Linha 3: fórmula
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.WEST;

        DocumentAdapter calc = e -> calcular();
        txtA.getDocument().addDocumentListener(calc);
        txtR1.getDocument().addDocumentListener(calc);
        txtB.getDocument().addDocumentListener(calc);
    }

    private void calcular() {
        try {
            double a  = Double.parseDouble(txtA.getText().replace(",", "."));
            double r1 = Double.parseDouble(txtR1.getText().replace(",", "."));
            double b  = Double.parseDouble(txtB.getText().replace(",", "."));
            txtR2.setText(String.format("%.2f", Calculos.regraDeTres(a, r1, b)).replace(".", ","));
        } catch (NumberFormatException ignored) {
            txtR2.setText("");
        }
    }

    public void limpar() {
        txtA.setText(""); txtR1.setText(""); txtB.setText(""); txtR2.setText("");
    }
}
