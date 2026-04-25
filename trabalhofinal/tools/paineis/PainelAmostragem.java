package tools.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelAmostragem extends JPanel {
    private final JTextField txtA = new JTextField("250,00", 10);
    private final JTextField txtB = new JTextField("15,00", 10);
    private final JTextField txtResultado = new JTextField("37,50", 10);

    public PainelAmostragem() {
        setBorder(BorderFactory.createTitledBorder("Amostragem - Quanto X% representa de Y"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        add(label("Total (a)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtA, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(label("Porcentagem (b)", Color.RED), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPanel pnlB = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        pnlB.add(txtB);
        pnlB.add(new JLabel("%"));
        add(pnlB, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(label("Corresponde a", Color.BLACK), gbc);
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
            txtResultado.setText(String.format("%.2f", Calculos.amostragem(a, b)).replace(".", ","));
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
