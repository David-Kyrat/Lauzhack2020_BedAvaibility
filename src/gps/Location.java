package gps;

import healthCare.administration.Canton;

public final class Location {

    /**
     * Basically the address but split into separate fields so that we can after searc hospitals
     * by specific regions, or specific zipcode etc...
     */
    private String stName; //Street Nam
    private int stNb; //Street Number
    private int npa;
    private String town;
    private Canton canton;

    /**
     * Constructor for Location
     * @param stName String
     * @param stNb int
     * @param npa int (Zip code)
     * @param town String
     * @param canton Canton
     */
    public Location(String stName, int stNb, int npa, String town, Canton canton) {
        this.stName = stName;
        this.stNb = stNb;
        this.npa = npa;
        this.town = town;
        this.canton = canton;
    }

    public String getStName() {
        return stName;
    }

    public int getStNb() {
        return stNb;
    }

    public int getNpa() {
        return npa;
    }

    public String getTown() {
        return town;
    }

    public Canton getCanton() {
        return canton;
    }

    @Override
    public String toString() {
        return stName + " " + stNb + ", "
                + "\n" + npa + ", " + town + " (" + canton + ")" ;
    }

}
