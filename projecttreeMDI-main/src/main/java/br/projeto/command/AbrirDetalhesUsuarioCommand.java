package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.helpers.WindowManager;
import javax.swing.JDesktopPane;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class AbrirDetalhesUsuarioCommand implements Command {
    private final JDesktopPane desktop;
    private final String titulo;
    private final UsuarioModel usuario;

    public AbrirDetalhesUsuarioCommand(JDesktopPane desktop, String titulo, UsuarioModel usuario) {
        this.desktop = desktop;
        this.titulo = titulo;
        this.usuario = usuario;
    }

    @Override
    public void execute() {
        if (WindowManager.getInstance().isFrameAberto(titulo)) {
            WindowManager.getInstance().bringToFront(titulo);
            return;
        }

        JInternalFrame frame = new JInternalFrame(titulo, true, true, true, true);
        frame.setSize(500, 300);
        frame.setIconifiable(false);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        topPanel.add(createLinePanel("Nome: ", usuario.getNome(), labelFont, valueFont));
        topPanel.add(createLinePanel("Email: ", usuario.getEmail(), labelFont, valueFont));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JPanel(), BorderLayout.CENTER);

        frame.add(new JScrollPane(mainPanel));
        desktop.add(frame);

        try {
            frame.setMaximum(true);
        } catch (Exception ignored) {}
    }

    private JPanel createLinePanel(String labelText, String valueText, Font labelFont, Font valueFont) {
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linePanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.DARK_GRAY);
        linePanel.add(label);

        JLabel value = new JLabel(valueText);
        value.setFont(valueFont);
        value.setForeground(Color.BLACK);
        linePanel.add(value);

        return linePanel;
    }
}