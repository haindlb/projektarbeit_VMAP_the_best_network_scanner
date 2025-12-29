package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.util.logging.Logger;


public class IcmpScanner implements Loggable {

    public static final Logger LOG = Logger.getLogger(IcmpScanner.class.getName());


    public void scanIcmp(){

        logInfo("Scanning ICMP.. PLease Wait!");

        try {
            int x = 10 / 0;
            LOG.info("Ergebnis:" + x);
        }catch(Exception e){
            logError("Fehler bei ICMP", e);
        }

        logWarn("Das ein Test");
    }



}
