package model;

public class StudentBiz extends Student {

    protected double accountingScore;
    protected double marketingScore;

    public StudentBiz(String ID, String fullName, double aveScore, Address address,double accountingScore, double marketingScore) {
        super(ID, fullName, aveScore, address);
        this.accountingScore = accountingScore;
        this.marketingScore = marketingScore;
    }

    public StudentBiz() {
    }

    public double getAccountingScore() {
        return accountingScore;
    }

    public void setAccountingScore(double accountingScore) {
        this.accountingScore = accountingScore;
    }

    public double getMarketingScore() {
        return marketingScore;
    }

    public void setMarketingScore(double marketingScore) {
        this.marketingScore = marketingScore;
    }

    public double calScoreAve(double accountingScore, double marketingScore) {
        return((accountingScore * 2 + marketingScore) / 3);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-20s | %-10.2f | %-10.2f | %-10.2f ",
                ID, fullName, address,accountingScore, marketingScore, aveScore);
    }

}
