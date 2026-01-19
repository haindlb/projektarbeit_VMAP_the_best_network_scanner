package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.util.Loggable;

import java.io.IOException;
import java.net.*;

public class PortScanner implements Loggable {


    //Expects a Host and Port to be checked; returns a Boolean true if reachable false if not, also logs results
    public boolean scanPort(String checkThisIP, String checkThisPortString){

        //convert the input from string to int
        int checkThisPort=Integer.parseInt(checkThisPortString);

        //formatierungs check
        if (!checkThisIP.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            //System.out.println("nope");
            logWarn("Unusable IP, check Host field");
            return false;
        }

        //make sure the IP address entered is actually usable
        InetAddress ipAddress = null;
        try {
            ipAddress=InetAddress.getByName(checkThisIP);
        }catch (UnknownHostException setIP){
            logError("Unusable IP, check Host field", setIP);
        }
        logInfo("Starting connection check on port: "+checkThisPort);

        //create new socket to be used for port specific connection
        try(Socket socket=new Socket()) {
            //if connection works port is open, otherwise Exception is thrown
            socket.connect(new InetSocketAddress(ipAddress,checkThisPort),500);
            logInfo("Connection to port "+checkThisPort+" was successful, port is reachable");
            return true;
        }
        //if connection fails port is not available
        catch (IOException e){
            logInfo("Connection to port "+checkThisPort+" failed check firewall or host config");
            return false;
            }

    }
}
