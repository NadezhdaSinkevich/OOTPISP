package sample.model;

import java.time.LocalDate;

public abstract class Employee {
    protected String name;
    protected LocalDate startDate;
    protected AdditionalInfo additionalInfo;

    public Employee(String name, LocalDate startDate, String publishingCompany, boolean takeawayPermission) {
        this.name = name;
        this.startDate = startDate;
        this.additionalInfo = new AdditionalInfo(publishingCompany, takeawayPermission);
    }

    public String getName() {
        return name;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
