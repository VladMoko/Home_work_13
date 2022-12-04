package otions;

public class Company {
    private String company;
    private String phone;
    private String website;
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String company, String phone, String website, String name, String catchPhrase, String bs) {
        this.company = company;
        this.phone = phone;
        this.website = website;
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
