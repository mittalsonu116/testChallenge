package core.utils.response_POJO;

import java.util.List;

public class GetUsersPOJO {

    private String id;
    private String name;
    private String username;
    private String email;
    private List<GetAddressPOJO> address;
    private String phone;
    private String website;
    private List<GetCompanyPOJO> company;

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

    public List<GetAddressPOJO> getAddress() {
        return address;
    }

    public void setAddress(List<GetAddressPOJO> address) {
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

    public List<GetCompanyPOJO> getCompany() {
        return company;
    }

    public void setCompany(List<GetCompanyPOJO> company) {
        this.company = company;
    }
}
