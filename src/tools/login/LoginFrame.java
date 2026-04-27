package tools.login;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class LoginFrame extends JFrame {

    // ── Credenciais válidas ────────────────────────────────────────────────
    private static final String[][] CREDENCIAIS_VALIDAS = {
            {"Administrador", "Administrador"},
            {"Adm", "Adm"},
            {"Administrador", "pr4frente0rever"}
    };

    // ── Arquivo de sessão (o "cookie" em .txt) ────────────────────────────
    private static final Path SESSION_FILE = Paths.get(
            System.getProperty("user.dir"), "tools_session.txt"
    );

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new LoginFrame().setVisible(true);
        });
    }

    private static boolean validarCredenciais(String usuario, String senha) {
        // Valida campos em branco
        if (usuario == null || usuario.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            return false;
        }

        // Compara com todas as credenciais válidas
        for (String[] cred : CREDENCIAIS_VALIDAS) {
            if (cred[0].equals(usuario) && cred[1].equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // ── Lê o usuário salvo no cookie (ou null se não existir) ─────────────
    private static String lerSessaoSalva() {
        if (!Files.exists(SESSION_FILE)) return null;
        try {
            String conteudo = Files.readString(SESSION_FILE).trim();
            if (!conteudo.isEmpty()) {
                return conteudo;
            }
        } catch (IOException ignored) {}
        return null;
    }

    // ── Salva o "cookie" no disco ─────────────────────────────────────────
    private static void salvarSessao(String usuario) {
        try {
            Files.writeString(SESSION_FILE, usuario);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível salvar a sessão:\n" + e.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── Remove o "cookie" do disco ────────────────────────────────────────
    public static void encerrarSessao() {
        try {
            Files.deleteIfExists(SESSION_FILE);
        } catch (IOException ignored) {}
    }

    // ── Abre a aplicação principal ────────────────────────────────────────
    private static void abrirAplicacao() {
        new MainFrame().setVisible(true);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Interface da tela de login
    // ══════════════════════════════════════════════════════════════════════
    public LoginFrame() {
        setTitle("Login – Tools");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill   = GridBagConstraints.HORIZONTAL;

        // ── Título ────────────────────────────────────────────────────────
        JLabel titulo = new JLabel("🔐  Tools – Acesso");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        painel.add(titulo, g);

        g.gridwidth = 1;

        // ── Usuário ───────────────────────────────────────────────────────
        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        painel.add(new JLabel("Usuário:"), g);

        JTextField campoUsuario = new JTextField(16);
        g.gridx = 1; g.weightx = 1;
        painel.add(campoUsuario, g);

        // ── Senha ─────────────────────────────────────────────────────────
        g.gridx = 0; g.gridy = 2; g.weightx = 0;
        painel.add(new JLabel("Senha:"), g);

        JPasswordField campoSenha = new JPasswordField(16);
        g.gridx = 1; g.weightx = 1;
        painel.add(campoSenha, g);

        // ── Lembrar sessão ────────────────────────────────────────────────
        JCheckBox chkLembrar = new JCheckBox("Lembrar usuário");
        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        painel.add(chkLembrar, g);

        // ── Mensagem de erro ──────────────────────────────────────────────
        JLabel lblErro = new JLabel(" ");
        lblErro.setForeground(Color.RED);
        lblErro.setHorizontalAlignment(SwingConstants.CENTER);
        g.gridy = 4;
        painel.add(lblErro, g);

        // ── Botão Entrar ──────────────────────────────────────────────────
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(btnEntrar.getFont().deriveFont(Font.BOLD));
        g.gridy = 5;
        painel.add(btnEntrar, g);

        // ── Preenche campos se cookie existir ─────────────────────────────
        String sessao = lerSessaoSalva();
        if (sessao != null) {
            campoUsuario.setText(sessao);
            chkLembrar.setSelected(true);
            btnEntrar.requestFocusInWindow();
        } else {
            campoUsuario.requestFocusInWindow();
        }

        // ── Ação de login ─────────────────────────────────────────────────
        Runnable tentarLogin = () -> {
            String usuario = campoUsuario.getText().trim();
            String senha   = new String(campoSenha.getPassword()).trim();

            if (usuario.isEmpty()) {
                lblErro.setText("Usuário não pode estar em branco.");
                campoUsuario.requestFocus();
                return;
            }

            if (senha.isEmpty()) {
                lblErro.setText("Senha não pode estar em branco.");
                campoSenha.requestFocus();
                return;
            }


            if (validarCredenciais(usuario, senha)) {
                if (chkLembrar.isSelected()) {
                    salvarSessao(usuario);
                } else {
                    encerrarSessao();
                }
                dispose();
                abrirAplicacao();
            } else {
                lblErro.setText("Usuário ou senha incorretos.");
                campoSenha.setText("");
                campoSenha.requestFocus();
            }
        };

        btnEntrar.addActionListener(e -> tentarLogin.run());
        campoUsuario.addActionListener(e -> campoSenha.requestFocus());
        campoSenha.addActionListener(e -> tentarLogin.run());

        add(painel);
        pack();
        setLocationRelativeTo(null);
    }
}