package br.projeto.observer;

import com.log.adaptador.LogConfig;
import com.log.model.LogRegister;

public class FileLogger implements LogObserver {

    @Override
    public void registrarLog(LogRegister logRegister, String formatoLog) {
        LogConfig.getInstance().setLogFormat(formatoLog);
        LogConfig.getInstance().novoRegistro(logRegister);

    }
}
