package healthCare.administration;

public enum Canton {
    AR("APPENZELL"),
    AG("ARGOVIE"),
    BA("BALE"),
    BE("BERNE"),
    FR("FRIBOURG"),
    GE("GENEVE"),
    GR("GRISON"),
    JU("JURA"),
    LU("LUCERNE"),
    NE("NEUCHATEL"),
    NW("NIDWALD"),
    OW("OBWALD"),
    SG("SAINT_GALL"),
    SH("SCHAFFHOUSE"),
    SZ("SCHWYTZ"),
    SO("SOLEURE"),
    TI("TESSIN"),
    TG("THURGOVIE"),
    UR("URI"),
    VS("VALAIS"),
    VD("VAUD"),
    ZG("ZUG"),
    ZH("ZURICH"),
    ;

    private String name;

    private Canton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

   /* @Override
    public String toString() {
        return this.name;
    }*/
}
