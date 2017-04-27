package com.company.Vehicle.third;

import com.company.Place.City;
import com.company.Place.Place;
import com.company.Vehicle.utilities.EnumArmament;
import com.company.Vehicle.utilities.Emergency;
import com.company.Vehicle.second.War;
import com.company.Vehicle.utilities.EnumState;
import com.company.Vehicle.utilities.InformationWindow.VehicleInformation;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public class WarPlane extends War implements Emergency
{
    // CONSTRUCTOR

    public WarPlane(String id, int crewNumber, int maxSpeed, int fuelMax, Vector<City> Cities, EnumArmament weapon, double x, double y)
    {
        super(id, crewNumber, maxSpeed, fuelMax, Cities, weapon);
        setX(x);
        setY(y);
        setNowStation(new Place("XXX", getX(), getY()));
        setNextStation(getNowCity());

        //graphics
        setIcon(new Canvas(20, 20));
        setGcIcon(getIcon().getGraphicsContext2D());
        try
        {
            setImageIcon(new Image(new FileInputStream(new File("src\\com\\company\\Pictures\\WarPlane.png"))));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        getGcIcon().drawImage(getImageIcon(), 0, 0);
    }

    // MAIN

    public void run()
    {

        getIcon().setOnMouseClicked(e -> new VehicleInformation(this));

        travel3();

        while(isThreadRun())
        {
            //System.out.println(getid()+ " " + getNowStation().getId() + "\n");

            if(getNowStation().getId().substring(0, 1).equals("C") && getNowStation().getId().substring(5, 6).equals("1")) // Civil Airport
            {
                nextCity();
                if (getstate() == EnumState.MODIFY)
                {
                    getRoute().removeAllElements();
                    getRoute().add(getNowCity());
                    routeRand();
                    nextCity();
                }
                waitingForPlace();
                parking();
                refueling();
                starting();
                getNowCity().warPlaneSub(this);
            }
            else if(getNowStation().getId().substring(0, 1).equals("C"))
            {
                nextCity();
                if (getstate() == EnumState.MODIFY)
                {
                    getRoute().removeAllElements();
                    getRoute().add(getNowCity());
                    routeRand();
                    nextCity();
                }
            }
            else if(getNowStation().getId().substring(0, 1).equals("I") && !getNowStation().getId().substring(1, 2).equals("2")) // Intersection
            {
                //getNowStation().getSemaphore(this);
                nextPoint();
                //getNowStation().releaseSemaphore();
            }
            else
            {
                nextPoint();
            }

            travel3();
        }
    }

    // PERIODIC METHODS

    protected void waitingForPlace()
    {
        getNowCity().warPlaneAdd(this);
    }

    // OTHER METHODS

    @Override
    public void emergencyLanding()
    {
        double minDistance = 10000;
        for(City x : getCities())
        {
            if (x.getId().substring(5, 6).equals("1") && Math.sqrt(Math.pow(x.getX() - getX(), 2) + Math.pow(x.getY() - getY(), 2)) < minDistance)
            {
                minDistance = Math.sqrt(Math.pow(x.getX() - getX(), 2) + Math.pow(x.getY() - getY(), 2));
                setNextStation(x);
            }
        }
        setNowStation(new Place("x", getX(), getY()));
        travel3();

        try
        {
            sleep(5000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        setNowStation(getNextStation());
        setNextStation(getNowCity());
        setState(EnumState.TRAVELING);
        travel3();
    }
}
