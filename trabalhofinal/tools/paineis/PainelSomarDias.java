package tools.paineis;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PainelSomarDias extends JPanel {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JSpinner spDataInicio = criarSpinner();
    private final JTextField txtDias    = new JTextField(8);
    private final JTextField txtResult  = new JTextField(12);

    public PainelSomarDias() {
        setBorder(BorderFactory.createTitledBorder("Somar dias (aceita negativo)"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Data início"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(spDataInicio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Dias"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        add(txtDias, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Resultado"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        txtResult.setEditable(false);
        add(txtResult, gbc);

        spDataInicio.addChangeListener(e -> calcular());
        txtDias.getDocument().addDocumentListener((DocumentAdapter) e -> calcular());
        calcular();
    }

    private JSpinner criarSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner sp = new JSpinner(model);
        sp.setEditor(new JSpinner.DateEditor(sp, "dd/MM/yyyy"));
        return sp;
    }

    private void calcular() {
        try {
            Date d = (Date) spDataInicio.getValue();
            LocalDate inicio = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int dias = Integer.parseInt(txtDias.getText().trim());
            LocalDate resultado = Calculos.somarDias(inicio, dias);
            txtResult.setText(resultado.format(FMT));
        } catch (Exception ignored) {
            txtResult.setText("");
        }
    }

    public void limpar() {
        spDataInicio.setValue(new Date());
        txtDias.setText("");
        txtResult.setText("");
    }
}
