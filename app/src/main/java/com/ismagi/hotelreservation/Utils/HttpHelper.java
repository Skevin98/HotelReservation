package com.ismagi.hotelreservation.Utils;

public abstract class HttpHelper {

    //Configurer votre adresse IP conveyor
    private static String Url = "http://192.168.137.1:45455/api";

    public static String GetUrl(){
        return Url;
    }

}
