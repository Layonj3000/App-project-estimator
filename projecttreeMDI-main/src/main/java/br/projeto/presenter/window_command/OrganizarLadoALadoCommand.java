package br.projeto.presenter.window_command;

import br.projeto.presenter.DesktopMemento;
import br.projeto.presenter.Zelador;

import javax.swing.*;
import java.awt.*;

public class OrganizarLadoALadoCommand implements WindowCommand {
    private final JDesktopPane desktop;

    public OrganizarLadoALadoCommand(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    @Override
    public void execute() {
        Zelador.getInstance().salvarEstado(new DesktopMemento(desktop));

        JInternalFrame[] frames = desktop.getAllFrames();
        int cols = (int) Math.ceil(Math.sqrt(frames.length));
        int rows = (int) Math.ceil((double) frames.length / cols);
        Dimension size = desktop.getSize();

        int w = size.width / cols;
        int h = size.height / rows;

        for (int i = 0; i < frames.length; i++) {
            int x = (i % cols) * w;
            int y = (i / cols) * h;
            frames[i].setBounds(x, y, w, h);
        }
    }
}
