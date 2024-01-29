package model;

public class Address {

    protected String street;
    protected String district;
    protected String city;
    protected String country;

    public Address(String street, String district, String city, String country) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public Address() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-20s | %-20s | %-20s ",
                street, district,city, country);
    }

}
