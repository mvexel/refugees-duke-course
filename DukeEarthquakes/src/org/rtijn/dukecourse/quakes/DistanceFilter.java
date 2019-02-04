package org.rtijn.dukecourse.quakes;

public class DistanceFilter implements Filter {

    private Location location;
    private double maxDistance;
    private String name;


    public DistanceFilter(Location location, double maxDistance) {
        this.location = location;
        this.maxDistance = maxDistance;
    }

    public String getName() {
        return this.name != null ? this.name : "Unnamed " + this.getClass().getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (location.distanceTo(qe.getLocation()) < maxDistance);
    }

}
