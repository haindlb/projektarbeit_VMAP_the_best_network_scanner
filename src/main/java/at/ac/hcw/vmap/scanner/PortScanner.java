package at.ac.hcw.vmap.scanner;

import at.ac.hcw.vmap.network.Host;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class PortScanner {

    //Expects a Host Object and Port to be checked; returns a Boolean true if reachable false if not
    public static boolean scanPort(Host checkThisHost, int checkThisPort){

        try(Socket socket=new Socket()) {
            //if connection works port is open, otherwise Exception is thrown
            socket.connect(new InetSocketAddress(checkThisHost.getIpAddress(),checkThisPort),500);
            return true;
        }catch (IOException e){
            return false;
            }

    }
}
