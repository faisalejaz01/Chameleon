package com.orasi.utils;

import org.apache.commons.lang3.time.StopWatch;
import static com.orasi.utils.Constants.MAX_SLEEP_TIME;

public class Sleeper {
    public static void sleep(double seconds) {
    	if (seconds>MAX_SLEEP_TIME) {
    		throw new RuntimeException("Sleep time exceeds 600 seconds.");
    	}
        TestReporter.logTrace("Sleeping for [ " + seconds + " ] seconds");
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        do {

        } while ((double)(stopwatch.getTime()/1000.0) < seconds);
        stopwatch.stop();
        stopwatch.reset();
    }
}
