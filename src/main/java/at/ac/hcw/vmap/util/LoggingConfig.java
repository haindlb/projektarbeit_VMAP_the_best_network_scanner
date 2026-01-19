package at.ac.hcw.vmap.util;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.logging.Level;
import java.util.logging.Logger;

//Hier keine Vererbung! durch final --> Configklasse
//Alle Handler werden hier Initiallisiert

public final class LoggingConfig {

    //private Konstruktor mit absicht -> kein Erstellen des Objekts der Klasse
    private LoggingConfig(){

    }

    public static void connectListView(ObservableList<String> items, ListView<String> listView){
        //Level INFO: INFO, WARNING, SEVERE werden geloggt / FINE, FINER, FINEST werden ignoriert
        //ALL geht nicht ohne JavaFX Filter, bitte auf INFO lassen

        //Logger entscheidet was geloggt wird, Handler entscheidet wohin es geht
        //RootLogger Hierachisch über allen anderen Loggern
        //Bitte lassen sonst in jeder Klasse Loglevel manuel setzen

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);

        //Eigener Handler für FXListView geschrieben
        //Über Config Klasse Einstellungen für max. Logs setzen

        LogHandlerListView fxHandler = new LogHandlerListView(items, listView, 1000);
        fxHandler.setLevel(Level.INFO);

        //Eigener Handler für CSVExport geschrieben
        LogHandlerCsvBuffer csvBuffer = new LogHandlerCsvBuffer(10000);
        csvBuffer.setLevel(Level.INFO);

        //Alle logs aus Klassen automatisch im UI durch Registrierung von am Root Logger
        rootLogger.addHandler(fxHandler);

        //Alle logs aus Klassen automatisch in CSV-Buffer druch Reggistrierung am Root Logger
        rootLogger.addHandler(csvBuffer);



    }
}
