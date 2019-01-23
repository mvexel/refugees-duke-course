
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter {
	private double magMin;
	private String name;


	public String getName() {
		return this.name != null ? this.name : "Unnamed " + this.getClass().getName();
	}


	public void setName(String name) {
		this.name = name;
	}


	public MinMagFilter(double min) {
		magMin = min;
	}


	public boolean satisfies(QuakeEntry qe) {
		return qe.getMagnitude() >= magMin;
	}

}