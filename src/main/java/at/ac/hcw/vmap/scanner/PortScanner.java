package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.net.*;

public class PortScanner implements Loggable {


    //Expects a Host and Port to be checked; returns a Boolean true if reachable false if not, also logs results
    //Erwartet einen Host und einen Port zur Überprüfung; gibt einen Boolean-Wert als true zurück, wenn der Port erreichbar ist
    public boolean scanPort(String checkThisIP, String checkThisPortString){

        //konvertiere input string zu int
        int checkThisPort=Integer.parseInt(checkThisPortString);

        //formatierungs check
        if (!checkThisIP.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            //System.out.println("nope");
            logWarn("Unusable IP, check Host field");
            return false;
        }

        //überprüfe ob port in valider range ist
        if (checkThisPort<1||checkThisPort>65535){
            logWarn("Unsuable Port, please enter valid value");
            return false;
        }

        //konvertiere String zu IP Adress Objekt
        InetAddress ipAddress = null;
        try {
            ipAddress=InetAddress.getByName(checkThisIP);
        }catch (UnknownHostException setIP){
            logError("Unusable IP, check Host field", setIP);
        }
        logInfo("Starting connection check on port: "+checkThisPort);

        //erstellt socket zum verbindung überprüfen
        try(Socket socket=new Socket()) {
            //if connection works port is open, otherwise Exception is thrown
            socket.connect(new InetSocketAddress(ipAddress,checkThisPort),500);
            logInfo("Connection to port " + checkThisPort + " was successful, port is reachable");
            return true;
        }
        //wenn verbindung fehlschlägt --> port nicht erreichbar
        catch (IOException e){
            logInfo("Connection to port " + checkThisPort + " failed check firewall or host config");
            return false;
            }

    }
}
