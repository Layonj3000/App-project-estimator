package br.projeto.command;

import br.projeto.presenter.helpers.WindowManager;

import javax.swing.*;

public class AbrirInternalFrameGenericoProjetoCommand implements Command {
    private final JDesktopPane desktop;
    private final String titulo;

    public AbrirInternalFrameGenericoProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            JInternalFrame frame = new JInternalFrame(titulo, true, true, true, true);
            frame.setSize(desktop.getWidth(), desktop.getHeight());
            frame.setIconifiable(false);
            frame.setVisible(true);
            desktop.add(frame);
            try {
                frame.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
