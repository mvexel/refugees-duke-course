
public class DepthFilter implements Filter {

	private double minDepth;
	private double maxDepth;
	private String name;


	public String getName() {
		return this.name != null ? this.name : "Unnamed " + this.getClass().getName();
	}


	public void setName(String name) {
		this.name = name;
	}


	public DepthFilter(double minDepth, double maxDepth) {
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
	}
	
	
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		return (qe.getDepth() <= this.maxDepth && qe.getDepth() >= this.minDepth);
	}

}