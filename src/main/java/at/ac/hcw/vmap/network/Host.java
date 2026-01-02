package at.ac.hcw.vmap.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host {

    //Expects an IP Address at creation; Name is optional

    private String name;
    private InetAddress ipAddress;

    public Host (String Address) throws UnknownHostException {
        this.ipAddress=InetAddress.getByName(Address);
    }

    public Host (String name,String Address) throws UnknownHostException {
        this.name=name;
        this.ipAddress=InetAddress.getByName(Address);
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public String getName(){
        return name;
    }

}
