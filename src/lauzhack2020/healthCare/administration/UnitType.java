package lauzhack2020.healthCare.administration;

public enum UnitType {

    RESUSCITATION_UNIT("Réanimation"),
    INTENSIVE_CARE("Soins intensifs"),
    EMERGENCY("Urgences"),
    CARDIOLOGY("Cardiologie"),
    NEUROLOGY("Neurologie"),
    ONCOLOGY("Oncologie"),
    GYNAECO_OBSTETRICS("Gynécologie et obstétrique"),
    ;

    private final String name;

    private UnitType(String name) {
        this.name = name;
    }

    public String info() {
        return name;
    }
}
