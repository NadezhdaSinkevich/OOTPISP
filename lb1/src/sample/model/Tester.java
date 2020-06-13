package sample.model;
        import java.time.LocalDate;

public class Tester extends Engineer {
    public enum TestType{
        Automated,
        Manual;
    }
    private TestType type;

    public Tester(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, int rage, Quality quality, TestType type) {
        super(name, publishingDate, publishingCompany, takeawayPermission, rage, quality);
        this.type = type;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }
}