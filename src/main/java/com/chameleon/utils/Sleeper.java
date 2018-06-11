package com.chameleon.utils;

import static com.chameleon.utils.Constants.MAX_SLEEP_TIME;

import org.apache.commons.lang3.time.StopWatch;

import com.chameleon.AutomationException;

public class Sleeper {
    public static void sleep(double seconds) {
        if (seconds >= MAX_SLEEP_TIME) {
            throw new AutomationException("Sleep time exceeds " + MAX_SLEEP_TIME + " seconds.");
        }
        TestReporter.logTrace("Sleeping for [ " + seconds + " ] seconds");
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        do {

        } while ((double) (stopwatch.getTime() / 1000.0) < seconds);
        stopwatch.stop();
        stopwatch.reset();
    }
}
