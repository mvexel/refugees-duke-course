package org.rtijn.dukecourse.quakes;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry thisQuake, QuakeEntry thatQuake) {
        String thisTitle = thisQuake.getInfo();
        String thatTitle = thatQuake.getInfo();
        double thisMag = thisQuake.getMagnitude();
        double thatMag = thatQuake.getMagnitude();
        if (thisTitle.compareTo(thatTitle) != 0) {
            return thisTitle.compareTo(thatTitle);
        } else if (thisMag < thatMag) {
            return -1;
        } else if (thisMag > thatMag) {
            return 1;
        }
        return 0;
    }
}
