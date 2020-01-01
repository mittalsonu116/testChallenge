package core.utils.response_POJO;

public class GetAddressPOJO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GetGeoPOJO geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipCode(String zip_code) {
        this.zipcode = zip_code;
    }

    public GetGeoPOJO getGeo() {
        return geo;
    }

    public void setGeo(GetGeoPOJO geo) {
        this.geo = geo;
    }
}
