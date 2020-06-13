package sample.model;
import java.time.LocalDate;

public class Designer extends Engineer {
    public enum DesignType{
        Game,
        Web,
        Graphic;
    }
    private DesignType type;

    public Designer(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, int rage, Quality quality, DesignType type) {
        super(name, publishingDate, publishingCompany, takeawayPermission, rage, quality);
        this.type = type;
    }
    public DesignType getType() {
        return type;
    }

    public void setType(DesignType type) {
        this.type = type;
    }
}
