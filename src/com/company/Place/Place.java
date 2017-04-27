package com.company.Place;

import com.company.Vehicle.first.Vehicle;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public class Place implements Serializable
{
    private String id;
    // city - 123456 - 1:city or inter, 23: number, 4: isCivilAirPort, 5: isCivilPort, 6: isWarAirPort
    // intersection - 123 - 1:city or inter, 23: number
    private double x;
    private double y;
    private AtomicInteger semaphore = new AtomicInteger(1);
    private String semaphoreId;
    private Vector<Vehicle> vehiclesWaiting = new Vector<>();

    public Place(String id, double x, double y)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void getSemaphore(Vehicle x)
    {
        if(semaphore.compareAndSet(1, 0))
        {
            semaphoreId = x.getid();
        }
        else
        {
            vehiclesWaiting.add(x);
            synchronized (x) {x.waiting();}
        }
    }

    public void releaseSemaphore()
    {
        semaphoreId = null;
        if(!vehiclesWaiting.isEmpty())
        {
            semaphoreId = vehiclesWaiting.get(0).getid();
            //vehiclesWaiting.get(0).notify();
            Vehicle z = vehiclesWaiting.get(0);
            synchronized (z) {z.notify();}
            vehiclesWaiting.remove(z);
        }
        else
        {
            semaphore.compareAndSet(0, 1);
        }
    }

    // GETTERS AND SETTERS

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public String getId()
    {
        return id;
    }
}
