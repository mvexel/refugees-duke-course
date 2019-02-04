package org.rtijn.dukecourse.quakes;

import java.util.ArrayList;

public class MatchAllFilter implements Filter {

	private ArrayList<Filter> filters;

	public MatchAllFilter() {
		this.filters = new ArrayList<>();
	}

	public String getName() {
		ArrayList<String> out = new ArrayList<>();
		for (Filter filter : filters) {
			out.add(filter.getName());
		}
		return String.join(", ", out);
	}

	public void addFilter(Filter f) {
		this.filters.add(f);
	}


	public boolean satisfies(QuakeEntry qe) {
		for (Filter filter : filters) {
			if (!filter.satisfies(qe)) {
				return false;
			}
		}
		return true;
	}
}
