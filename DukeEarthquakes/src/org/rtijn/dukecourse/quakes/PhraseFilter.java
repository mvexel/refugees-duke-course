package org.rtijn.dukecourse.quakes;

public class PhraseFilter implements Filter {

	private String where;
	private String phrase;
	private String name;


	public String getName() {
		return this.name != null ? this.name : "Unnamed " + this.getClass().getName();
	}


	public void setName(String name) {
		this.name = name;
	}


	public PhraseFilter(String where, String phrase) {
		this.where = where;
		this.phrase = phrase;
	}


	@Override
	public boolean satisfies(QuakeEntry qe) {
		if (this.where.equals("start") && qe.getInfo().startsWith(phrase)) {
			return true;
		}
		if (this.where.equals("end") && qe.getInfo().endsWith(phrase)) {
			return true;
		}
		return this.where.equals("any") && qe.getInfo().contains(phrase);
	}

}
