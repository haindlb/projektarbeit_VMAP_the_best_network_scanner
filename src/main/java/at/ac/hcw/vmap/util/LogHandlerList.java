package at.ac.hcw.vmap.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandlerList extends Handler{


    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    
    private final ObservableList<String> target;
    private final ListView<String> listView;
    private final int maxItems;

    public LogHandlerList(ObservableList<String> target, ListView<String> listView, int maxItems) {
        this.target = target;
        this.listView = listView;
        this.maxItems = maxItems;
    }

    @Override
    public void publish(LogRecord record) {
        if(!isLoggable(record)) return;

        String name = record.getLoggerName();
        if (name == null || !name.startsWith("at.ac.hcw.vmap")) {
            return; // alles andere (javafx.*, com.sun.*, etc.) ignorieren
        }


        String time = TS.format(Instant.ofEpochMilli(record.getMillis()));
        String row = time + " [" + record.getLevel().getName() + "] " + "--  " + record.getMessage();
        
        if(record.getThrown() != null){
            row += " | Error=" + record.getThrown().getClass().getSimpleName() + ": " + record.getThrown().getMessage();
        }
        
        String finalrow = row;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                target.add(finalrow);

                if(target.size() > maxItems){
                    target.remove(0,target.size() - maxItems);
                }
                listView.scrollTo(target.size() - 1);
            }
        });
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
