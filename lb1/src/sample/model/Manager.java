package sample.model;
import java.time.LocalDate;

public class Manager extends Employee {
    public enum ManageType {
        AccountManager,
        SalesManager,
        FinanceManager,
        AdvertasingManager,
        ProjectManager;
    }
    private ManageType manageType;
    protected int office;

    public Manager(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, ManageType manageType, int office) {
        super(name, publishingDate, publishingCompany, takeawayPermission);
        this.manageType = manageType;
        this.office = office;
    }

    public ManageType getManageType() {
        return manageType;
    }
    public void setManageType(ManageType manageType) {
        this.manageType = manageType;
    }
    public void setOffice(int rage) {
        this.office = office;
    }

    public int getOffice() {
        return office;
    }
}
