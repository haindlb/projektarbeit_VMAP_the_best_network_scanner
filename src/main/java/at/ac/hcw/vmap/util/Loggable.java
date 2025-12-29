package at.ac.hcw.vmap.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface Loggable {
    //Nur Methoden zum logging, der Logger selbst wird in der Klasse erzeugt

    default Logger logger(){
        return Logger.getLogger(this.getClass().getName());
    }

    default void logInfo(String msg){
        logger().info(msg);
    }

    default void logWarn(String msg){
        logger().warning(msg);
    }

    default void logError(String msg, Throwable t) {
        logger().log(Level.SEVERE, msg, t);
    }

}
