package com.company.Vehicle.utilities.InformationWindow;

import com.company.Controller;
import com.company.Place.City;
import com.company.Vehicle.third.CivilPlane;
import com.company.Vehicle.third.CivilShip;
import com.company.Vehicle.third.WarPlane;
import com.company.Vehicle.third.WarShip;
import com.company.Vehicle.utilities.EnumState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by johnny1024 on 2016-01-25.
 */
public class VehicleInformation
{
    private int option = 0;
    private CivilPlane civilPlane;
    private CivilShip civilShip;
    private WarPlane warPlane;
    private WarShip warShip;

    public VehicleInformation(CivilPlane civilPlane)
    {
        this.civilPlane = civilPlane;
        option = 1;
        display();
    }
    public VehicleInformation(CivilShip civilShip)
    {
        this.civilShip = civilShip;
        option = 2;
        display();
    }
    public VehicleInformation(WarPlane warPlane)
    {
        this.warPlane = warPlane;
        option = 3;
        display();
    }
    public VehicleInformation(WarShip warShip)
    {
        this.warShip = warShip;
        option = 4;
        display();
    }

    public void display()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlertFXML.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        AlertController controller = loader.getController();
        Button buttonModify = controller.getButtonModify();
        Button buttonDelete = controller.getButtonDelete();
        Button buttonEmergency = controller.getButtonEmergency();
        Label labelState = controller.getLabelState();
        Label labelId = controller.getLabelId();
        Label labelCrew = controller.getLabelCrew();
        Label labelSpeed = controller.getLabelSpeed();
        Label labelFuel = controller.getLabelFuel();
        Label labelPassengers = controller.getLabelPassengers();
        Label labelRoute = controller.getLabelRoute();
        Label labelCompany = controller.getLabelCompany();
        Label labelArmament = controller.getLabelArmament();

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        switch (option)
        {
            case 1:
            {
                buttonModify.setOnAction(event ->{
                    if(civilPlane.getstate() != EnumState.EMERGENCY) civilPlane.setState(EnumState.MODIFY);
                });
                buttonDelete.setOnAction(event -> {
                    civilPlane.setState(EnumState.DELETE);
                    window.close();
                });
                buttonEmergency.setOnAction(event -> civilPlane.setState(EnumState.EMERGENCY));

                labelState.setText(String.valueOf(civilPlane.getstate()));
                labelId.setText(civilPlane.getid());
                labelCrew.setText(String.valueOf(civilPlane.getCrewNumber()));
                labelSpeed.setText(String.valueOf(civilPlane.getMaxSpeed()));
                labelFuel.setText(String.valueOf(civilPlane.getFuelLevel()) + " / " + String.valueOf(civilPlane.getFuelMax()));
                labelPassengers.setText(String.valueOf(civilPlane.getCurrentPassengers()) + " / " + String.valueOf(civilPlane.getMaxPassengers()));
                String r = new String();
                for (City z : civilPlane.getRoute())
                {
                    r += " - " + civilPlane.getCities().indexOf(z);
                }
                labelRoute.setText(r);
                labelCompany.setText("-");
                labelArmament.setText("-");
            } break;
            case 2:
            {
                buttonModify.setOnAction(event ->{
                    if(civilShip.getstate() != EnumState.EMERGENCY) civilShip.setState(EnumState.MODIFY);
                });
                buttonDelete.setOnAction(event -> {
                    civilShip.setState(EnumState.DELETE);
                    window.close();
                });
                buttonEmergency.setVisible(false);

                labelState.setText(String.valueOf(civilShip.getstate()));
                labelId.setText(civilShip.getid());
                labelCrew.setText(String.valueOf(civilShip.getCrewNumber()));
                labelSpeed.setText(String.valueOf(civilShip.getMaxSpeed()));
                labelFuel.setText(String.valueOf(civilShip.getFuelLevel()) + " / " + String.valueOf(civilShip.getFuelMax()));
                labelPassengers.setText(String.valueOf(civilShip.getCurrentPassengers()) + " / " + String.valueOf(civilShip.getMaxPassengers()));
                String r = new String();
                for (City z : civilShip.getRoute())
                {
                    r += " - " + civilShip.getCities().indexOf(z);
                }
                labelRoute.setText(r);
                labelCompany.setText(String.valueOf(civilShip.getCompany()));
                labelArmament.setText("-");
            } break;
            case 3:
            {
                buttonModify.setOnAction(event ->{
                    if(warPlane.getstate() != EnumState.EMERGENCY) warPlane.setState(EnumState.MODIFY);
                });
                buttonDelete.setOnAction(event -> {
                    warPlane.setState(EnumState.DELETE);
                    window.close();
                });
                buttonEmergency.setOnAction(event -> warPlane.setState(EnumState.EMERGENCY));

                labelState.setText(String.valueOf(warPlane.getstate()));
                labelId.setText(warPlane.getid());
                labelCrew.setText(String.valueOf(warPlane.getCrewNumber()));
                labelSpeed.setText(String.valueOf(warPlane.getMaxSpeed()));
                labelFuel.setText(String.valueOf(warPlane.getFuelLevel()) + " / " + String.valueOf(warPlane.getFuelMax()));
                labelPassengers.setText("- / -");
                String r = new String();
                for (City z : warPlane.getRoute())
                {
                    r += " - " + warPlane.getCities().indexOf(z);
                }
                labelRoute.setText(r);
                labelCompany.setText("-");
                labelArmament.setText(String.valueOf(warPlane.getWeapon()));
            } break;
            case 4:
            {
                buttonModify.setOnAction(event ->{
                    if(warShip.getstate() != EnumState.EMERGENCY) warShip.setState(EnumState.MODIFY);
                });
                buttonDelete.setOnAction(event -> {
                    warShip.setState(EnumState.DELETE);
                    window.close();
                });
                buttonEmergency.setVisible(false);

                labelState.setText(String.valueOf(warShip.getstate()));
                labelId.setText(warShip.getid());
                labelCrew.setText(String.valueOf(warShip.getCrewNumber()));
                labelSpeed.setText(String.valueOf(warShip.getMaxSpeed()));
                labelFuel.setText(String.valueOf(warShip.getFuelLevel()) + " / " + String.valueOf(warShip.getFuelMax()));
                labelPassengers.setText("- / -");
                String r = new String();
                for (City z : warShip.getRoute())
                {
                    r += " - " + warShip.getCities().indexOf(z);
                }
                labelRoute.setText(r);
                labelCompany.setText("-");
                labelArmament.setText(String.valueOf(warShip.getWeapon()));
            } break;
        }

        window.setTitle("Information Panel");
        window.setScene(new Scene(root));
        window.showAndWait();
    }
}
