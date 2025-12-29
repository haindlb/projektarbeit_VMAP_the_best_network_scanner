package at.ac.hcw.vmap.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Loggable {
    //Nur Methoden zum Logging, der Logger selbst wird in der Klasse erzeugt
    //Interface wird für alle Klassen etabliert, die Loggen wollen


    //default Methode--Klassen müssen nicht etablieren / dürfen aber überschreibem
    //Jede Klasse eigener Logger
    default Logger logger(){
        return Logger.getLogger(this.getClass().getName());
    }

    //Ab hier Log Methode pro Loglevel
    //logger().info(msg); --> schreibt Logmeldung in Logger

    default void logInfo(String msg){
        logger().info(msg);
    }

    default void logWarn(String msg){
        logger().warning(msg);
    }

    //Sondermethode für Errormeldungen --> Throwable ist die Exception die geworfen wird
    default void logError(String msg, Throwable t) {
        logger().log(Level.SEVERE, msg, t);
    }

}
