package core.utils.response_POJO;

import java.util.List;

public class GetAddressPOJO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private List<GetGeoPOJO> geo;

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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<GetGeoPOJO> getGeo() {
        return geo;
    }

    public void setGeo(List<GetGeoPOJO> geo) {
        this.geo = geo;
    }
}
