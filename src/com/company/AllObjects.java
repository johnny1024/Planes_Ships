package com.company;

import com.company.Passenger.Passenger;
import com.company.Place.World;
import com.company.Vehicle.third.CivilPlane;
import com.company.Vehicle.third.CivilShip;
import com.company.Vehicle.third.WarPlane;
import com.company.Vehicle.third.WarShip;
import com.company.Vehicle.utilities.EnumArmament;
import com.company.Vehicle.utilities.EnumFirms;
import com.company.Vehicle.utilities.EnumState;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 * Metoda przechowuj¹ca wszystkie obsluguj¹ca wszystkie w¹tki, przechowuj¹ca pojazdy i pasazerow oraz informacje o miastach, skrzyzowaniach i trasach.
 *
 *
 *
 */
public class AllObjects implements Serializable
{
    private World WorldObject;
    private Vector<CivilPlane> civilPlaneVector = new Vector<>();
    private Vector<CivilShip> civilShipVector = new Vector<>();
    private Vector<WarPlane> warPlaneVector = new Vector<>();
    private Vector<WarShip> warShipVector = new Vector<>();
    private Vector<Passenger> passengerVector = new Vector<>();
    private int civilPlaneCount;
    private int civilShipCount;
    private int warPlaneCount;
    private int warShipCount;
    private int passengerProportion;
    private int vehicleCapacity;

    private AnchorPane anchorPane;
    private ListView passengerList;

    public AllObjects(AnchorPane anchorPane, ListView passengerList)
    {
        this.anchorPane = anchorPane;
        this.passengerList = passengerList;

        WorldObject = new World();
        this.civilPlaneCount = 0;
        this.civilShipCount = 0;
        this.warPlaneCount = 0;
        this.warShipCount = 0;
        this.passengerProportion = 2;
        this.vehicleCapacity = 2;
        start();
    }

    public void start() // pocz¹tkowe samoloty i statki
    {
        for (int i = 0; i < 5; i++)
        {
            addCivilPlane();
            addCivilShip();
            addWarShip();
            addWarPlane();
        }
    }

    public void addCivilPlane()
    {
        String id = "CP" + Integer.toString(civilPlaneCount++);
        CivilPlane x = new CivilPlane(id, 5, 100, 10000, WorldObject.getCities(), vehicleCapacity);
        civilPlaneVector.add(x);
        x.start();
        for (int i = 0; i < passengerProportion; i++) passengerAdd();

        anchorPane.getChildren().add(x.getIcon());
    }

    public void removeCivilPlane(CivilPlane x)
    {
        civilPlaneVector.remove(x);
        for (Passenger z : x.getPassengers())
        {
            z.setVehicleNow(null);
            z.getCityNow().passengerAdd(z);
            z.notify();
        }
        x.setThreadRun(false);
        x.setState(EnumState.DELETED);
        for (int i = 0; i < passengerProportion; i++) passengerSub();
    }

    public void addCivilShip()
    {
        String id = "CS" + Integer.toString(civilShipCount++);
        Random gen = new Random();
        CivilShip x = new CivilShip(id, 10, 100, 10000, WorldObject.getCities(), vehicleCapacity, EnumFirms.values()[gen.nextInt(10)]);
        civilShipVector.add(x);
        x.start();
        for (int i = 0; i < passengerProportion; i++) passengerAdd();

        anchorPane.getChildren().add(x.getIcon());
    }

    public void removeCivilShip(CivilShip x)
    {
        civilShipVector.remove(x);
        for (Passenger z : x.getPassengers())
        {
            z.setVehicleNow(null);
            z.getCityNow().passengerAdd(z);
            z.notify();
        }
        x.setThreadRun(false);
        x.setState(EnumState.DELETED);
        for (int i = 0; i < passengerProportion; i++) passengerSub();
    }

    public void addWarPlane() //?????????????????????????????
    {
        String id = "WP" + Integer.toString(warPlaneCount++);
        Random gen = new Random();
        if (!warShipVector.isEmpty())
        {
            WarShip z = warShipVector.get(gen.nextInt(warShipVector.size()));
            WarPlane x = new WarPlane(id, 1, 100, 10000, WorldObject.getCities(), z.getWeapon(), z.getX(), z.getY());
            warPlaneVector.add(x);
            x.start();

            anchorPane.getChildren().add(x.getIcon());
        } else
        {
            // error - nie ma lotniskowca z ktorego samolot mog³by wystartowaæ
        }
    }

    public void removeWarPlane(WarPlane x)
    {
        warPlaneVector.remove(x);
        x.setThreadRun(false);
        x.setState(EnumState.DELETED);
    }

    public void addWarShip()
    {
        String id = "WS" + Integer.toString(warShipCount++);
        Random gen = new Random();
        WarShip x = new WarShip(id, 50, 100, 10000, WorldObject.getCities(), EnumArmament.values()[gen.nextInt(5)]);
        warShipVector.add(x);
        x.start();

        anchorPane.getChildren().add(x.getIcon());
    }

    public void removeWarShip(WarShip x)
    {
        warShipVector.remove(x);
        x.setThreadRun(false);
        x.setState(EnumState.DELETED);
    }

    public void passengerAdd()
    {
        Passenger x = new Passenger(WorldObject.getCities(), WorldObject.getConnectionsTab());
        passengerVector.add(x);
        x.start();
    }

    public void passengerSub()
    {
        Random gen = new Random();
        Passenger x = passengerVector.get(gen.nextInt(passengerVector.size()));
        if (x.getCityNow() != null) x.getCityNow().passengerSub(x);
        if (x.getVehicleNow() != null) x.getVehicleNow().passengerSub(x);
        x.setThreadRun(false);
    }

    public void endAll()
    {
        for (CivilPlane x : civilPlaneVector) x.stop();
        for (CivilShip x : civilShipVector) x.stop();
        for (WarPlane x : warPlaneVector) x.stop();
        for (WarShip x : warShipVector) x.stop();
        for (Passenger x : passengerVector) x.stop();
    }

    // GETTERS AND SETTERS

    public Vector<CivilPlane> getCivilPlaneVector()
    {
        return civilPlaneVector;
    }

    public Vector<CivilShip> getCivilShipVector()
    {
        return civilShipVector;
    }

    public Vector<WarPlane> getWarPlaneVector()
    {
        return warPlaneVector;
    }

    public Vector<WarShip> getWarShipVector()
    {
        return warShipVector;
    }
}
