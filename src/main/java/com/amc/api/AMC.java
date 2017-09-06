package com.amc.api;

import com.amc.api.showtimes.Showtimes;
import com.amc.api.theatres.Theatres;

public class AMC {
    public final static String URL = "https://api.amctheatres.com/v2";

    public static Showtimes showtimes() {
        return new Showtimes();
    }

    public static Theatres theatres() {
        return new Theatres();
    }

}
