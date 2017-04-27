package com.company;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class Controller
{
    @FXML
    private Button newCivilPlane;

    @FXML
    private Button newCivilShip;

    @FXML
    private Button newMilitaryPlane;

    @FXML
    private Button newMilitaryShip;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView passengerList;

    // GETTERS AND SETTERS

    public Button getNewCivilPlane()
    {
        return newCivilPlane;
    }

    public Button getNewCivilShip()
    {
        return newCivilShip;
    }

    public Button getNewMilitaryPlane()
    {
        return newMilitaryPlane;
    }

    public Button getNewMilitaryShip()
    {
        return newMilitaryShip;
    }

    public AnchorPane getAnchorPane()
    {
        return anchorPane;
    }

    public ListView getPassengerList()
    {
        return passengerList;
    }
}
