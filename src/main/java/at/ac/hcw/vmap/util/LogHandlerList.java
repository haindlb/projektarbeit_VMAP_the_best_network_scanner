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

    //Statische Konstante für Zeitformat kann pber String Pattern geändert werden HH:mm:ss ZoneId bitte lassen, Erkennung Zeitzone von OS
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    
    private final ObservableList<String> target; //Formatierte Logzeilen werden in Liste gespeicht
    //muss unbedingt Observable bleiben mit ArrayList werden die Änderungen nicht automatisch in FX gemeldet--> sonst refresh manual
    private final ListView<String> listView; //Referenz auf UI-ListView --> automatischen scrollen
    private final int maxItems;

    public LogHandlerList(ObservableList<String> target, ListView<String> listView, int maxItems) {
        this.target = target;
        this.listView = listView;
        this.maxItems = maxItems;
    }
    //Methoden sind durch Java Superklasse Handler vorgeschrieben -> publish eigens etabliert für FX Viewer

    @Override
    public void publish(LogRecord record) {
        if(!isLoggable(record)) return; //Prüfung Loglevel und Filter

        String name = record.getLoggerName();
        if (name == null || !name.startsWith("at.ac.hcw.vmap")) {
            return; // alles andere javafx. ignorieren -> sonst zu viele Log Einträge
        }


        String time = TS.format(Instant.ofEpochMilli(record.getMillis())); //Formatierung Zeitstempel in Config oben
        String row = time + " [" + record.getLevel().getName() + "] " + "--  " + record.getMessage(); //Erstellung Logzeile
        
        if(record.getThrown() != null){ //Gibt es eine Exception wenn ja Sonderzeile Severe
            row += " | Error=" + record.getThrown().getClass().getSimpleName() + ": " + record.getThrown().getMessage();
        }
        
        String finalRow = row; //Brauch ich für FX UI Thread da row in Klasse publish sitz und nach übergabe auch nicht geändert werden darf --> final

            //Ganz WICHTIG!!!! UI darf nur von JAVAFX Application Thread verändet werden --> keinen UI Zugriff von anderen Threads
            //Publisch läuft ohne dem im Hintergrund Thread und somit nicht in UI
            //„Hey JavaFX, führe diesen Code in deinem UI-Thread aus"
            //Runlater weil sonst Deadlock weil JavaFX nicht unterbrochen werden darf

             Platform.runLater(new Runnable() {
            @Override
            public void run() {
                target.add(finalRow); //automatische Aktualisierung

                if(target.size() > maxItems){
                    target.remove(0,target.size() - maxItems); //Wenn limit überschritten letzer Eintrag wird gelöscht
                }
                listView.scrollTo(target.size() - 1); //scrollt Liste im UI zu neuesten Eintrag
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
