package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.network.Host;
import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.util.logging.Logger;


public class IcmpScanner implements Loggable {

    public static final Logger LOG = Logger.getLogger(IcmpScanner.class.getName());

    public IcmpScanner() {

    }

    //Expects a Host Object to be checked; returns a Boolean true if reachable false if not

    public static boolean scanICMP(Host checkThisHost){

        System.out.println("pinging");
        try {
            return checkThisHost.getIpAddress().isReachable(1000);

        }catch (IOException e){
            System.out.println("IO Error");
            return false;
        }

    }

    /*
    public void scanIcmp(){

        logInfo("Scanning ICMP.. PLease Wait!");

        try {
            int x = 10 / 0;
            LOG.info("Ergebnis:" + x);
        }catch(Exception e){
            logError("Fehler bei ICMP", e);
        }

        logWarn("Das ein Test");

        for(int i = 0; i < 297; i++){
            logInfo("Log" + i);
        }
    }

     */





}
