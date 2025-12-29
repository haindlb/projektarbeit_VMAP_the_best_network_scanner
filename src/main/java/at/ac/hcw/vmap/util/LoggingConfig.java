package at.ac.hcw.vmap.util;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingConfig {

    private LoggingConfig(){

    }

    public static void connectListView(ObservableList<String> items, ListView<String> listView){

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);

        LogHandlerList fxHandler = new LogHandlerList(items, listView, 1000);
        fxHandler.setLevel(Level.INFO);

        rootLogger.addHandler(fxHandler);



    }
}
