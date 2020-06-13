package sample.model;

import java.time.LocalDate;

public abstract class Engineer extends Employee {
    public enum Quality{
        Junior,
        Middle,
        Senior;
    }
    private Quality quality;
    protected int office;

    public Engineer(String name, LocalDate startDate, String publishingCompany, boolean takeawayPermission, int office, Quality quality) {
        super(name, startDate, publishingCompany, takeawayPermission);
        this.office = office;
        this.quality=quality;
    }


    public void setOffice(int office) {
        this.office = office;
    }

    public int getOffice() {
        return office;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Quality getQuality() {
        return quality;
    }
}
