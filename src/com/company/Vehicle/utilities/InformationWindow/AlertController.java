package com.company.Vehicle.utilities.InformationWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by johnny1024 on 2016-01-25.
 */
public class AlertController
{
    @FXML
    private Button buttonModify;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEmergency;

    @FXML
    private Label labelState;

    @FXML
    private Label labelId;

    @FXML
    private Label labelCrew;

    @FXML
    private Label labelSpeed;

    @FXML
    private Label labelFuel;

    @FXML
    private Label labelPassengers;

    @FXML
    private Label labelRoute;

    @FXML
    private Label labelCompany;

    @FXML
    private Label labelArmament;

    // GETTERS AND SETTERS

    public Button getButtonModify()
    {
        return buttonModify;
    }

    public Button getButtonDelete()
    {
        return buttonDelete;
    }

    public Button getButtonEmergency()
    {
        return buttonEmergency;
    }

    public Label getLabelState()
    {
        return labelState;
    }

    public Label getLabelId()
    {
        return labelId;
    }

    public Label getLabelCrew()
    {
        return labelCrew;
    }

    public Label getLabelSpeed()
    {
        return labelSpeed;
    }

    public Label getLabelFuel()
    {
        return labelFuel;
    }

    public Label getLabelPassengers()
    {
        return labelPassengers;
    }

    public Label getLabelRoute()
    {
        return labelRoute;
    }

    public Label getLabelCompany()
    {
        return labelCompany;
    }

    public Label getLabelArmament()
    {
        return labelArmament;
    }
}
