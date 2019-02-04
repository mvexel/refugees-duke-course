package org.rtijn.dukecourse.quakes;

import java.util.ArrayList;

public class EarthQuakeClient2 {
	public EarthQuakeClient2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EarthQuakeClient2 client = new EarthQuakeClient2();
//		client.quakesWithFilter();
//		client.testMatchAllFilter();
		client.testMatchAllFilter2();
	}

	public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (f.satisfies(qe)) {
				answer.add(qe);
			}
		}

		return answer;
	}

	public void quakesOfDepth(double minDepth, double maxDepth) {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		ArrayList<QuakeEntry> answer = new ArrayList<>();
		answer = this.filter(list, new DepthFilter(-12000, -10000));
		for (QuakeEntry quakeEntry : answer) {
			System.out.println(quakeEntry);
		}
		System.out.println(answer.size());

	}

	public void quakesWithFilter() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");

//		org.rtijn.dukecourse.quakes.Location tokyo = new org.rtijn.dukecourse.quakes.Location(35.42, 139.43);
//		org.rtijn.dukecourse.quakes.Location denver = new org.rtijn.dukecourse.quakes.Location(39.7392, -104.9903);

		ArrayList<QuakeEntry> filteredQuakeList = new ArrayList<>();

//		filteredQuakeList = filter(list, new org.rtijn.dukecourse.quakes.DistanceFilter(denver, 1000000));
		filteredQuakeList = filter(filteredQuakeList, new PhraseFilter("any", "o"));
		filteredQuakeList = filter(list, new MagnitudeFilter(1.0, 4.0));
		filteredQuakeList = filter(filteredQuakeList, new DepthFilter(-180000, -30000));
		for (QuakeEntry quakeEntry : filteredQuakeList) {
			System.out.println(quakeEntry);
		}
		System.out.println(filteredQuakeList.size());
	}

	public void testMatchAllFilter() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		MatchAllFilter maf = new MatchAllFilter();
		maf.addFilter(new MagnitudeFilter(0.0, 2.0));
		maf.addFilter(new DepthFilter(-100000, -10000));
		maf.addFilter(new PhraseFilter("any", "a"));
		ArrayList<QuakeEntry> result = filter(list, maf);
		for (QuakeEntry quakeEntry : result) {
			System.out.println(quakeEntry);
		}
		System.out.println(result.size());
		System.out.println(maf.getName());
	}

	public void testMatchAllFilter2() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
//		org.rtijn.dukecourse.quakes.Location tulsa = new org.rtijn.dukecourse.quakes.Location(36.1314, -95.9372);
		Location billund = new Location(55.7308, 9.1153);
		MatchAllFilter maf = new MatchAllFilter();
		maf.addFilter(new MagnitudeFilter(0.0, 5.0));
		maf.addFilter(new DistanceFilter(billund, 3000000));
		maf.addFilter(new PhraseFilter("any", "e"));
		ArrayList<QuakeEntry> result = filter(list, maf);
		for (QuakeEntry quakeEntry : result) {
			System.out.println(quakeEntry);
		}
		System.out.println(result.size());
	}

	public void createCSV() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source = "../data/nov20quakedata.atom";
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
}
