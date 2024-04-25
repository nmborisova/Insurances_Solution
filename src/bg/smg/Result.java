package bg.smg;

public class Result implements Comparable<Result>{
    private String regNo;
    private int ownersCount;
    private int insuranceCount;

    public Result(String regNo, int ownersCount, int insuranceCount) {
        this.regNo = regNo;
        this.ownersCount = ownersCount;
        this.insuranceCount = insuranceCount;
    }

    public Result() {
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getOwnersCount() {
        return ownersCount;
    }

    public void setOwnersCount(int ownersCount) {
        this.ownersCount = ownersCount;
    }

    public int getInsuranceCount() {
        return insuranceCount;
    }

    public void setInsuranceCount(int insuranceCount) {
        this.insuranceCount = insuranceCount;
    }

    @Override
    public int compareTo(Result o) {
        return o.ownersCount - ownersCount;
    }
}
