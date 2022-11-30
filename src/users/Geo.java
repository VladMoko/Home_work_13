package users;

public class Geo {
    private String geo;
    private String lat;
    private String Ing;

    public Geo(String geo, String lat, String ing) {
        this.geo = geo;
        this.lat = lat;
        Ing = ing;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIng() {
        return Ing;
    }

    public void setIng(String ing) {
        Ing = ing;
    }

}
