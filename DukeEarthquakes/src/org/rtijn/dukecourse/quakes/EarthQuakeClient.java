package org.rtijn.dukecourse.quakes;

import java.util.ArrayList;

public class EarthQuakeClient {
	public EarthQuakeClient() {
	}

	public static void main(String[] args) {
		EarthQuakeClient client = new EarthQuakeClient();
//		client.bigQuakes();
//		client.closeToMe();
//		client.quakesOfDepth();
		client.quakesByPhrase();
//		client.findClosestQuakes();
	}

	public void bigQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		String source = "data/nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
		for (QuakeEntry quakeEntry : answer) {
			System.out.println(quakeEntry);
		}
		System.out.println("Found " + answer.size() + " quakes that match that criteria");
	}


	public void closeToMe() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		// String source =
		// "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		String source = "data/nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");

		// This location is Durham, NC
		// org.rtijn.dukecourse.quakes.Location city = new org.rtijn.dukecourse.quakes.Location(35.988, -78.907);

		// This location is Bridgeport, CA
		Location city = new Location(38.17, -118.82);

		ArrayList<QuakeEntry> filteredQuakes = filterByDistanceFrom(list, 1000000, city);
		for (QuakeEntry quakeEntry : filteredQuakes) {
			System.out.println(quakeEntry);
		}
		System.out.println("Found " + filteredQuakes.size() + " quakes that match that criteria");
	}


	public void createCSV() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedatasmall.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		dumpCSV(list);
		System.out.println("# quakes read: " + list.size());
		for (QuakeEntry qe : list) {
			System.out.println(qe);
		}
	}


	public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for (QuakeEntry qe : list) {
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
					qe.getMagnitude(), qe.getInfo());
		}

	}


	public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) { // exclusive
				answer.add(qe);
			}
		}
		return answer;
	}


	public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry quakeEntry : quakeData) {
			if (from.distanceTo(quakeEntry.getLocation()) < distMax) {
				answer.add(quakeEntry);
			}
		}
		return answer;
	}


	public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (qe.getMagnitude() > magMin) {
				answer.add(qe);
			}
		}
		return answer;
	}


	public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (where.equals("start") && qe.getInfo().startsWith(phrase)) {
				answer.add(qe);
			} else if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
				answer.add(qe);
			} else if (where.equals("any") && qe.getInfo().contains(phrase)) {
				answer.add(qe);
			}
		}
		return answer;
	}


	public void findClosestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");

		Location jakarta = new Location(-6.211, 106.845);

		ArrayList<QuakeEntry> answer = getClosest(list, jakarta, 3);
		for (QuakeEntry quakeEntry : answer) {
			System.out.println(quakeEntry);
		}
		System.out.println("number found: " + answer.size());
	}


	public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
		ArrayList<QuakeEntry> answer = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			int minIndex = 0;
			for (int j = 0; j < quakeData.size(); j++) {
				QuakeEntry quake = quakeData.get(j);
				Location loc = quake.getLocation();
				if (loc.distanceTo(current) < quakeData.get(minIndex).getLocation().distanceTo(current)) {
					minIndex = j;
				}
			}
			answer.add(quakeData.get(minIndex));
			quakeData.remove(minIndex);
		}
		return answer;
	}


	public void quakesByPhrase() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		String where = "any";
		String phrase = "Can";
		ArrayList<QuakeEntry> filteredQuakes = filterByPhrase(list, where, phrase);
		for (QuakeEntry quakeEntry : filteredQuakes) {
			System.out.println(quakeEntry);
		}
		System.out.println("Found " + filteredQuakes.size() + " quakes that match " + phrase + " at " + where);
	}


	public void quakesOfDepth() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		ArrayList<QuakeEntry> filteredQuakes = filterByDepth(list, -4000, -2000);
		for (QuakeEntry quakeEntry : filteredQuakes) {
			System.out.println(quakeEntry);
		}
		System.out.println("Found " + filteredQuakes.size() + " quakes that match that criteria");

	}

}
