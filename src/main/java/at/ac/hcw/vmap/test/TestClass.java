package at.ac.hcw.vmap.test;

import at.ac.hcw.vmap.network.Host;
import at.ac.hcw.vmap.scanner.IcmpScanner;

import java.net.UnknownHostException;

public class TestClass {
    public static void main(String[] args){

        Host testHost1=new Host("name","192.168.115.59");

        /*
        if(IcmpScanner.scanICMP(testHost1)){
            System.out.println("REACHABLE");
        }else {
            System.out.println("NOT REACHABLE");
        }

         */



    }
}
