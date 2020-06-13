package sample.model;

public class AdditionalInfo {
    public String publishingCompany;
    public boolean remote_work;

    public AdditionalInfo(String publishingCompany, boolean remote_work) {
        this.publishingCompany = publishingCompany;
        this.remote_work = remote_work;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public boolean is_remote_work() {
        return remote_work;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public void setTakeawayPermission(boolean takeawayPermission) {
        this.remote_work = takeawayPermission;
    }
}
