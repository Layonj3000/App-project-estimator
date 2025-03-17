package br.projeto.service;

import br.projeto.observer.LogNotifier;
import com.log.model.LogRegister;

public class ProjetoLogService{
    private LogNotifier logNotifier;
    private LogRegister logRegister;
    private String formatoLog;

    public ProjetoLogService(LogNotifier logNotifier, String formatoLog) {
        this.logNotifier = logNotifier;
        this.formatoLog = formatoLog;
    }

    public void notificar() {
        logNotifier.notificar(logRegister, formatoLog);
    }

    public void setLogRegister(LogRegister logRegister) {
        this.logRegister = logRegister;
    }

    public void setFormatoLog(String formatoLog) {
        this.formatoLog = formatoLog;
    }
}
