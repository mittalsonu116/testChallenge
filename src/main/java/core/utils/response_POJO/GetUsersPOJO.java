package core.utils.response_POJO;

public class GetUsersPOJO {

    private String id;
    private String name;
    private String username;
    private String email;
    private GetAddressPOJO address;
    private String phone;
    private String website;
    private GetCompanyPOJO company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GetAddressPOJO getAddress() {
        return address;
    }

    public void setAddress(GetAddressPOJO address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public GetCompanyPOJO getCompany() {
        return company;
    }

    public void setCompany(GetCompanyPOJO company) {
        this.company = company;
    }
}
