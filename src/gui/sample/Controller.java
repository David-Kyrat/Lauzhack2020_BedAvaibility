package gui.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import healthCare.Patient;
import healthCare.administration.HospitalURL;
import healthCare.administration.UnitType;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField ageInput;
    public TextField riskFactorInput;
    public ComboBox probableInput;
    public Button patientAddButton;
    public ComboBox unitTypeInput;
    public TextField firstNameSearchInput;
    public TextField lastNameSearchInput;
    public Button searchButtonInput;
    public TableView dataBaseTable;
    public WebView webView;
    public HospitalURL hospitalURL= HospitalURL.CHUV;

    @FXML
    public Patient addPatient() {
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        int age = Integer.parseInt(ageInput.getText());
        int Input = Integer.parseInt(ageInput.getText());
        int riskFactor = Integer.parseInt(ageInput.getText());
        boolean probable = probableInput.getValue().toString().equals("COVID Probable");
        UnitType unit;
        String unitTypeSelector = unitTypeInput.getValue().toString();
        switch (unitTypeSelector) {
            case ("Resuscitation unit"):
                unit = UnitType.RESUSCITATION_UNIT;
                break;
            case ("Intensive Care"):
                unit = UnitType.INTENSIVE_CARE;
                break;
            case ("Emergency"):
                unit = UnitType.EMERGENCY;
                break;
            case ("Cardiology"):
                unit = UnitType.CARDIOLOGY;
                break;
            case ("Neurology"):
                unit = UnitType.NEUROLOGY;
                break;
            case ("Oncology"):
                unit = UnitType.ONCOLOGY;
                break;
            case ("Gyneacologie and obstetrics"):
                unit = UnitType.GYNAECO_OBSTETRICS;
                break;
            default:
                unit = null;
                break;
        }
        return new Patient(firstName, lastName, age, unit, probable, riskFactor);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(hospitalURL.getURL());
    }
}