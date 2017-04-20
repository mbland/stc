package com.schibsted.engprod.stc;

import java.util.HashMap;
import java.util.Map;

/**
 * @see BugCountTest
 */
public class BugCount {

    private Map<String, Integer> bugCounts = new HashMap<>();

    public void add(String appName, int nrOfBugs) {
        int currentCount = getCount(appName);
        if (currentCount == 0) {
            this.bugCounts.put(appName, nrOfBugs);
        } else {
            this.bugCounts.put(appName, nrOfBugs + currentCount);
        }
    }

    public int getCount(String appName) {
        return this.bugCounts.containsKey(appName) ? this.bugCounts.get(appName) : 0;
    }
}
