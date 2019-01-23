import java.util.*;


public class LargestQuakes {
	public void findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");
		System.out.println("Index of largest quake = " + indexOfLargest(list));
	}


	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int largestIndex = 0;
		double largestMag = 0;
		for (QuakeEntry quakeEntry : data) {
			if (quakeEntry.getMagnitude() > largestMag) {
				largestMag = quakeEntry.getMagnitude();
				largestIndex = data.indexOf(quakeEntry);
			}
		}
		return largestIndex;
	}


	public void getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
		ArrayList<QuakeEntry> answer = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			int largestIndex = indexOfLargest(quakeData);
			answer.add(quakeData.get(largestIndex));
			quakeData.remove(largestIndex);
		}
		int cnt = 1;
		for (QuakeEntry quakeEntry : answer) {
			System.out.println("" + cnt + ": " + quakeEntry);
			cnt++;
		}
	}


	public static void main(String[] args) {
		LargestQuakes lq = new LargestQuakes();
		lq.findLargestQuakes();

		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");

		lq.getLargest(list, 50);
	}
}
