package lauzhack2020.healthCare;

import lauzhack2020.healthCare.administration.UnitType;

public final class Patient {

    private String firstName;
    private String lastName;
    private int age;
    private UnitType typeOfCare;
    private boolean covidProbable;
    private int complicationRiskPercent;

    public Patient(String firstName, String lastName, int age, UnitType type, boolean probab, int complicationRiskPercent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfCare = type;
        this.covidProbable = probab;
        this.complicationRiskPercent = complicationRiskPercent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public int getAge() {
        return age;
    }

    public UnitType getTypeOfCare() {
        return typeOfCare;
    }

    public boolean isCovidProbable() {
        return covidProbable;
    }

    public int getComplicationRiskPercent() {
        return complicationRiskPercent;
    }

    //TODO
    /**
     * Core decision taking algorithm
     */
    public void sendTo() {

    }

    public static class PatientBuilder {

        private String firstName;
        private String lastName;
        private int age;
        private UnitType typeOfCare;
        private boolean covidProbable;
        private int complicationRiskPercent;

        public PatientBuilder setName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            return this;
        }

        public PatientBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PatientBuilder setCareType(UnitType typeOfCare) {
            this.typeOfCare = typeOfCare;
            return this;
        }

        public PatientBuilder setIsCovidProbable(boolean covidPlus) {
            this.covidProbable = covidPlus;
            return this;
        }

        public PatientBuilder setComplicationRiskPercent(int percent) {
            this.complicationRiskPercent = percent;
            return this;
        }

        public Patient build() {
            return new Patient(firstName, lastName, age, typeOfCare, covidProbable, complicationRiskPercent);
        }
    }
}
