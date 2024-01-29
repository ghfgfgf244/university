package model;

public class StudentIT extends Student {

    protected double javaScore;
    protected double cssScore;

    public StudentIT(String ID, String fullName, double aveScore, Address address, double javaScore, double cssScore) {
        super(ID, fullName, aveScore, address);
        this.javaScore = javaScore;
        this.cssScore = cssScore;
    }

    public StudentIT() {

    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    public double getCssScore() {
        return cssScore;
    }

    public void setCssScore(double cssScore) {
        this.cssScore = cssScore;
    }

    
    public double calScoreAve(double javaScore, double cssScore) {
        return ((3 * javaScore + cssScore) / 4);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-20s | %-10.2f | %-10.2f | %-10.2f ",
                ID, fullName, address,javaScore, cssScore, aveScore);
    }

}
