package model;

public class Student {

    protected String ID;
    protected String fullName;
    protected double aveScore;
    protected Address address;

    public Student(String ID, String fullName, double aveScore, Address address) {
        this.ID = ID;
        this.fullName = fullName;
        this.aveScore = aveScore;
        this.address = address;
    }

    public Student() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAveScore() {
        return aveScore;
    }

    public void setAveScore(double aveScore) {
        this.aveScore = aveScore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-10.2f | %-20s ",
                ID, fullName, aveScore, address);
    }

}
