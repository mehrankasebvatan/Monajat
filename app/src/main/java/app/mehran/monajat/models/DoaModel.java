package app.mehran.monajat.models;

public class DoaModel {

    private String id, arabic, persian;

    public DoaModel(String arabic, String id, String persian) {

        this.arabic = arabic;
        this.id = id;
        this.persian = persian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getPersian() {
        return persian;
    }

    public void setPersian(String persian) {
        this.persian = persian;
    }
}
