package org.rtijn.dukecourse.quakes;
/**
 * Write a description of class org.rtijn.dukecourse.quakes.QuakeSortInPlace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;


public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
//		 init parser
//		org.rtijn.dukecourse.quakes.EarthQuakeParser parser = new org.rtijn.dukecourse.quakes.EarthQuakeParser();
//		String source = "data/earthquakeDataSampleSix2.atom";
//		ArrayList<org.rtijn.dukecourse.quakes.QuakeEntry> list = parser.read(source);
//		System.out.println("read data for " + list.size() + " quakes");
//
//		// print quakes list
//		int c = 0;
//		for (org.rtijn.dukecourse.quakes.QuakeEntry qe : list) {
//			System.out.println("" + c++ + ": " + qe);
//		}

        QuakeSortInPlace sorter = new QuakeSortInPlace();

        sorter.testSort();
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
                    qe.getMagnitude(), qe.getInfo());
        }

    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        // String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
//		sortByMagnitude(list);
//		sortByLargestDepth(list);
//		sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
//		sortByMagnitudeWithCheck(list);
		for (QuakeEntry qe : list) {
			System.out.println(qe);
		}

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        double largestDepth = Double.MAX_VALUE;
        int idx = 0;
        for (int i = from; i < quakeData.size(); i++) {
            double thisDepth = quakeData.get(i).getDepth();
            if (thisDepth < largestDepth) {
                idx = i;
                largestDepth = thisDepth;
            }
        }
        return idx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // loop over list length - 1
        for (int i = 0; i < 50; i++) {
//		for (int i = 0; i < in.size() - 1; i++) {
            // find largest remaining in unsorted part of list (start at i)
            int largestIndex = getLargestDepth(in, i);
            // when found swap element i and found index
            Collections.swap(in, i, largestIndex);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++) {
            // print before
//			System.out.println("BEFORE===============PASS " +numSorted);
//			int c = 0;
//			for (org.rtijn.dukecourse.quakes.QuakeEntry qe : quakeData) {
//				System.out.println("" + c++ + ": " + qe);
//			}
            // compare i and i + 1
            QuakeEntry thisEntry = quakeData.get(i);
            QuakeEntry thatEntry = quakeData.get(i + 1);
            // swap if not in order
            if (thisEntry.getMagnitude() > thatEntry.getMagnitude()) {
                quakeData.set(i, thatEntry);
                quakeData.set(i + 1, thisEntry);
            }
        }
//		 print after
//		System.out.println("AFTER===============PASS " +numSorted);
//		int c = 0;
//		for (org.rtijn.dukecourse.quakes.QuakeEntry qe : quakeData) {
//			System.out.println("" + c++ + ": " + qe);
//		}
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            if (checkInSortedOrder(in)) {
                System.out.println("ran " + i + " times, max " + in.size());
                break;
            }
            onePassBubbleSort(in, i);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            if (checkInSortedOrder(in)) {
                System.out.println("ran " + i + " times, max " + in.size());
                break;
            }
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }
}
