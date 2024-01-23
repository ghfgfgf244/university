package model;

public class StudentIT extends Student {

    protected double javaScore;
    protected double cssScore;

    public StudentIT(double javaScore, double cssScore, String ID, String fullName, double aveScore, Address address) {
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

    @Override
    public void calScoreAve() {
        setAveScore((3 * getJavaScore() + getCssScore()) / 4);
    }

    @Override
    public String toString() {
        return String.format(super.toString(),"%-10.2f | %-10.2f ",
                javaScore, cssScore);
    }

}
