package br.projeto.service;

import br.projeto.observer.LogNotifier;
import com.log.model.LogRegister;

public class LogService{
    private final LogNotifier logNotifier;
    private LogRegister logRegister;
    private String formatoLog;

    public LogService(String formatoLog) {
        this.logNotifier = new LogNotifier();
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

    public LogNotifier getLogNotifier() {
        return logNotifier;
    }

}
