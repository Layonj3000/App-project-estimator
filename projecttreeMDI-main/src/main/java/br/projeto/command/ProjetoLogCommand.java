package br.projeto.command;

import br.projeto.observer.LogNotifier;
import com.log.model.LogRegister;

public abstract class ProjetoLogCommand implements Command {
    private LogNotifier logNotifier;
    private LogRegister logRegister;
    private String formatoLog;

    public ProjetoLogCommand(LogNotifier logNotifier, String formatoLog) {
        this.logNotifier = logNotifier;
        this.formatoLog = formatoLog;
    }

    @Override
    public void execute() {
        logNotifier.notificar(logRegister, formatoLog);
    }

    public void setLogRegister(LogRegister logRegister) {
        this.logRegister = logRegister;
    }

    public void setFormatoLog(String formatoLog) {
        this.formatoLog = formatoLog;
    }
}
