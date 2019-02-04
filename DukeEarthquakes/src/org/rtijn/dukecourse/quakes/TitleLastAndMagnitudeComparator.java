package org.rtijn.dukecourse.quakes;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry thisQuake, QuakeEntry thatQuake) {
        String thisTitle = thisQuake.getInfo();
        String thatTitle = thatQuake.getInfo();
        String thisTitleLastWord = thisTitle.substring(thisTitle.lastIndexOf(" ") + 1);
        String thatTitleLastWord = thatTitle.substring(thatTitle.lastIndexOf(" ") + 1);
        double thisMag = thisQuake.getMagnitude();
        double thatMag = thatQuake.getMagnitude();
        if (thisTitleLastWord.compareTo(thatTitleLastWord) != 0) {
            return thisTitleLastWord.compareTo(thatTitleLastWord);
        } else if (thisMag < thatMag) {
            return -1;
        } else if (thisMag > thatMag) {
            return 1;
        }
        return 0;
    }
}
