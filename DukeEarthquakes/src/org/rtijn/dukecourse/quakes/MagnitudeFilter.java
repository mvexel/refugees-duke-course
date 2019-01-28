package org.rtijn.dukecourse.quakes;

public class MagnitudeFilter implements Filter {

	private double minMag;
	private double maxMag;
	private String name;


	public String getName() {
		return this.name != null ? this.name : "Unnamed " + this.getClass().getName();
	}


	public void setName(String name) {
		this.name = name;
	}


	public MagnitudeFilter(double minMag, double maxMag) {
		this.minMag = minMag;
		this.maxMag = maxMag;
	}


	@Override
	public boolean satisfies(QuakeEntry qe) {
		return (qe.getMagnitude() <= this.maxMag && qe.getMagnitude() >= this.minMag);
	}

}
