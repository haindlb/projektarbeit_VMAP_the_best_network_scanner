package at.ac.hcw.vmap.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host {

    //Expects an IP Address at creation; Name is optional

    private String name;
    private InetAddress ipAddress;

    public Host (String Address){
        try {
            this.ipAddress=InetAddress.getByName(Address);
        }catch (UnknownHostException e){
            System.out.println("Invalid Host");
        }

    }

    public Host (String name,String Address) {
        this.name=name;
        try {
            this.ipAddress=InetAddress.getByName(Address);
        }catch (UnknownHostException e){
            System.out.println("Invalid Host");
        }
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public String getName(){
        return name;
    }

}
