package tools.paineis;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PainelDiferencaDatas extends JPanel {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JSpinner spData1 = criarSpinner();
    private final JSpinner spData2 = criarSpinner();
    private final JLabel   lblResultado = new JLabel("dia.");

    public PainelDiferencaDatas() {
        setBorder(BorderFactory.createTitledBorder("Diferença entre datas"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        gbc.gridx = 0; gbc.gridy = 0;
        add(spData1, gbc);
        gbc.gridx = 1;
        add(spData2, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        add(lblResultado, gbc);

        spData1.addChangeListener(e -> calcular());
        spData2.addChangeListener(e -> calcular());
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
            java.util.Date d1 = (java.util.Date) spData1.getValue();
            java.util.Date d2 = (java.util.Date) spData2.getValue();
            LocalDate ld1 = d1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            LocalDate ld2 = d2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            long diff = Calculos.diferencaEntreDatas(ld1, ld2);
            lblResultado.setText(diff + (Math.abs(diff) == 1 ? " dia." : " dias."));
        } catch (Exception ignored) {
            lblResultado.setText("dia.");
        }
    }

    public void limpar() {
        spData1.setValue(new java.util.Date());
        spData2.setValue(new java.util.Date());
    }
}
