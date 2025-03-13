package br.projeto.observer;

import com.log.model.LogRegister;

import java.util.ArrayList;
import java.util.List;

public class LogNotifier {
    private List<LogObserver> observers = new ArrayList<LogObserver>();

    public void add(LogObserver observer) {
        observers.add(observer);
    }
    public void notificar(LogRegister logRegister, String formatoLog) {
        for (LogObserver observer : observers) {
            observer.registrarLog(logRegister, formatoLog);
        }
    }

}
