package at.ac.hcw.vmap.util;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.logging.Level;
import java.util.logging.Logger;

//Hier keine Vererbung! durch final --> Configklasse

public final class LoggingConfig {

    //private Konstruktor mit absicht -> kein Erstellen des Obejkts der Klasse
    private LoggingConfig(){

    }

    public static void connectListView(ObservableList<String> items, ListView<String> listView){
        //Level INFO: INFO, WARNING, SEVERE werden geloggt / FINE, FINER, FINEST werden ignoriert
        //ALL geht nicht ohne JavaFX Filter, bitte auf INFO lassen

        //Logger entscheidet was gelogt wird, Handler entscheidet wohin es geht
        //RootLogger Hierachisch über allen anderen Loggern
        //Bitte lassen sonst in jeder Klasse Loglevel manuel setzen

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);

        //Eigener Hadnler für FXListView geschrieben
        //Über Config Klasse Einstellungen für max. Logs setzen

        LogHandlerList fxHandler = new LogHandlerList(items, listView, 1000);
        fxHandler.setLevel(Level.INFO);

        //Alle logs aus Klassen automatisch im UI durch Registrierung von am Root Logger
        rootLogger.addHandler(fxHandler);



    }
}
