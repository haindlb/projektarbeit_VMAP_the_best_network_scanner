package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class PortScanner implements Loggable {

    public static final Logger LOG = Logger.getLogger(PortScanner.class.getName());

    //Expects a Host and Port to be checked; returns a Boolean true if reachable false if not, also logs results
    public boolean scanPort(String checkThisIP, int checkThisPort){

        InetAddress ipAddress = null;
        try {
            ipAddress=InetAddress.getByName(checkThisIP);
        }catch (UnknownHostException setIP){
            logError("Unusable IP, check Host field", setIP);
        }
        logInfo("Starting connection check on port"+checkThisPort);
        try(Socket socket=new Socket()) {
            //if connection works port is open, otherwise Exception is thrown
            socket.connect(new InetSocketAddress(ipAddress,checkThisPort),500);
            logInfo("Connection to port "+checkThisPort+" was successful, port is reachable");
            return true;
        }catch (IOException e){
            logInfo("Connection to port "+checkThisPort+" failed, check firewall or host config");
            return false;
            }

    }
}
