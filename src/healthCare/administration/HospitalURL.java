package healthCare.administration;

public enum HospitalURL {
    CHUV("https://goo.gl/maps/T4keLwNTMtmvBKGN7"), eHnv("https://goo.gl/maps/BxayLZAnP37DmG9QA"), FDH("https://goo.gl/maps/y5ZZcan2FN4t6c5t8");


    private String URL;
    private HospitalURL(String url){
        URL = url;
    }

    public String getURL() {
        return new String(URL);
    }
}
