package com.company.Vehicle.third;

import com.company.Place.City;
import com.company.Vehicle.utilities.EnumFirms;
import com.company.Vehicle.second.Civil;
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
public class CivilShip extends Civil
{
    private EnumFirms company;

    // CONSTRUCTOR

    public CivilShip(String id, int crewNumber, int maxSpeed, int fuelMax, Vector<City> Cities, int maxPassengers, EnumFirms company)
    {
        super(id, crewNumber, maxSpeed, fuelMax, Cities, maxPassengers);
        this.company = company;

        //graphics
        setIcon(new Canvas(20, 20));
        setGcIcon(getIcon().getGraphicsContext2D());
        try
        {
            setImageIcon(new Image(new FileInputStream(new File("src\\com\\company\\Pictures\\CivilShip.png"))));
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

        while(isThreadRun())
        {
            //System.out.println(getid()+ " " + getNowStation().getId() + "\n");

            if(getNowStation().getId().substring(0, 1).equals("C") && getNowStation().getId().substring(4, 5).equals("1")) // Port
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
                exchangePassagers();
                starting();
                getNowCity().civilShipSub(this);
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
            else if(getNowStation().getId().substring(0, 1).equals("I") && getNowStation().getId().substring(1, 2).equals("2")) // Intersection
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
        getNowCity().civilShipAdd(this);
    }

    // GETTERS AND SETTERS

    public EnumFirms getCompany()
    {
        return company;
    }
}
