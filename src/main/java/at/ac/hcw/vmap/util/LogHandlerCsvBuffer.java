package at.ac.hcw.vmap.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandlerCsvBuffer extends Handler {

    //Deque double ended queue -> Angenehm am Anfang oder Ende Hinzufügen entfernen ohne index
    private final Deque<LogRecord> logBuffer = new ArrayDeque<>();
    private final int maxRows;

    public LogHandlerCsvBuffer(int maxRows) {
        this.maxRows = maxRows;
    }

    //Logs werden in publish geschrieben - eigender Thread
    //Wenn User auf Export drückt UI Thread liest Buffer somit doppelter Zugriff auf publish -> Race Condition
    //Sychronized verhindert gleichzeitigen Zugriff von mehreren Threads -> exportList muss warten bis publish fertig ist

    @Override
    public synchronized void publish(LogRecord record) {

        if(!isLoggable(record)) return; //Prüfung Loglevel und Filter
        logBuffer.addLast(record);
        while(logBuffer.size() > maxRows){
            logBuffer.removeFirst();
        }
    }

    public synchronized ArrayList<LogRecord> exportList(){
        return new ArrayList<>(logBuffer);
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
