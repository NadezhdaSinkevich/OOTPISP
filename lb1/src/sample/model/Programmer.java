package sample.model;
        import java.time.LocalDate;

public class Programmer extends Engineer {
    public enum ProgType {
        Backend,
        Frontend,
        FullStack,
        Web,
        Game_Developer,
        Android,
        iOS;
    }
    private ProgType type;
    private boolean higher_education;
    private Quality quality;

    public Programmer(String name, LocalDate publishingDate, String publishingCompany, boolean takeawayPermission, ProgType type, boolean higher_education, int rage, Quality quality) {
        super(name, publishingDate, publishingCompany, takeawayPermission, rage, quality);
        this.type = type;
        this.higher_education = higher_education;
        this.quality = quality;
    }

    public void setType(ProgType type) {
        this.type = type;
    }

    public void setColorful(boolean colorful) {
        higher_education = colorful;
    }

    public ProgType getType() {
        return type;
    }

    public boolean isHigher_education() {
        return higher_education;
    }
}
