package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class IcmpScanner implements Loggable {


    //Expects a Host Object to be checked; returns a Boolean true if reachable, false if not


    public boolean scanICMP(String checkThisIP){

        //formatierungs check
        if (!checkThisIP.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            //System.out.println("nope");
            logWarn("Unusable IP, check Host field");
            return false;
        }

        //konvertiere zu IP Objekt
        InetAddress ipAddress = null;
        try {
            ipAddress=InetAddress.getByName(checkThisIP);
        }catch (UnknownHostException setIP){
            logError("Unusable IP, check Host field", setIP);
            return false;
        }

       logInfo("Starting ICMP Check");
        //check if host is reachable
        try {
            if(ipAddress.isReachable(1000)){
                logInfo("Host " + ipAddress + " is reachable, ICMP Check successful");
                return true;
            }
            else {
                logInfo("Host" + ipAddress + " is unreachable check firewall settings and IP address");
                return false;
            }
        }catch (IOException ioException) {
            logError("IO exception during ICMP check",ioException);
            return false;
        }

    }



/*
    public void scanIcmp(){

        logInfo("Scanning ICMP.. Please Wait!");


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
