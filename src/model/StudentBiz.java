package model;

public class StudentBiz extends Student {

    protected double accountingScore;
    protected double marketingScore;

    public StudentBiz(double accountingScore, double marketingScore, String ID, String fullName, double aveScore, Address address) {
        super(ID, fullName, aveScore, address);
        this.accountingScore = accountingScore;
        this.marketingScore = marketingScore;
    }

    public StudentBiz(double accountingScore, double marketingScore) {
        this.accountingScore = accountingScore;
        this.marketingScore = marketingScore;
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

    @Override
    public void calScoreAve() {
        setAveScore((getAccountingScore() * 2 + getMarketingScore()) / 3);
    }

    @Override
    public String toString() {
        return String.format(super.toString(),"%-10.2f | %-10.2f ",
                accountingScore, marketingScore);
    }

}
