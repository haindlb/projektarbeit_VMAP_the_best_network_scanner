package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;


public class IcmpScanner implements Loggable {

    public static final Logger LOG = Logger.getLogger(IcmpScanner.class.getName());

    //Expects a Host Object to be checked; returns a Boolean true if reachable false if not


    public boolean scanICMP(String checkThisIP){

        //make sure entered IP is usable
        InetAddress ipAddress = null;
        try {
            ipAddress=InetAddress.getByName(checkThisIP);
        }catch (UnknownHostException setIP){
            logError("Unusable IP, check Host field", setIP);
        }

       logInfo("Starting ICMP Check \n");
        //check if host is reachable
        try {
            if(ipAddress.isReachable(1000)){
                logInfo("Host is reachable, ICMP Check successful");
                return true;
            }
            else {
                logInfo("Host is unreachable \n check firewall settings and IP address");
                return false;
            }
        }catch (IOException ioException) {
            logError("IO exception during ICMP check",ioException);
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
