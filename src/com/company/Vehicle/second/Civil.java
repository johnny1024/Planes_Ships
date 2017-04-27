package com.company.Vehicle.second;

import com.company.Passenger.Passenger;
import com.company.Place.City;
import com.company.Vehicle.first.Vehicle;
import com.company.Vehicle.utilities.EnumState;

import java.util.Vector;

import static java.lang.Thread.sleep;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public abstract class Civil extends Vehicle
{
    private int maxPassengers;
    private int currentPassengers;
    private Vector<Passenger> passengers = new Vector<>();

    // CONSTRUCTOR

    public Civil(String id, int crewNumber, int maxSpeed, int fuelMax, Vector<City> Cities, int maxPassengers)
    {
        super(id, crewNumber, maxSpeed, fuelMax, Cities);
        this.maxPassengers = maxPassengers;
        this.currentPassengers = 0;
    }

    // PERIODIC METHODS

    protected void exchangePassagers()
    {
        for(Passenger x : passengers)
        {
            passengerSub(x);
            getNowCity().passengerAdd(x);
            synchronized (x) {x.notify();}
        }
        for(Passenger x : getNowCity().getPassengers())
        {
            synchronized (x) {x.notify();}
        }
        setState(EnumState.PASSENGERCHANGE);
        try
        {
            sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void passengerAdd(Passenger a)
    {
        passengers.add(a);
        currentPassengers++;
        if (currentPassengers == maxPassengers) setState(EnumState.STARTING);
    }

    public void passengerSub(Passenger a)
    {
        passengers.remove(a);
        currentPassengers--;
    }

    // GETTERS AND SETTERS

    public int getMaxPassengers()
    {
        return maxPassengers;
    }

    public int getCurrentPassengers()
    {
        return currentPassengers;
    }

    public Vector<Passenger> getPassengers()
    {
        return passengers;
    }

    public void setPassengers(Vector<Passenger> passengers)
    {
        this.passengers = passengers;
    }
}
