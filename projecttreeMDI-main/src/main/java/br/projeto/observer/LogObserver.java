package br.projeto.observer;

import com.log.model.LogRegister;

public interface LogObserver {
    void registrarLog(LogRegister logRegister, String formatoLog);
}



