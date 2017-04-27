package com.company.Passenger;

import com.company.Place.City;
import com.company.Vehicle.second.Civil;
import com.company.Vehicle.third.CivilPlane;
import com.company.Vehicle.third.CivilShip;
import com.company.Vehicle.utilities.EnumState;

import java.io.Serializable;
import java.util.*;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public class Passenger extends Thread implements Serializable
{
    private EnumName passengerName; // 200
    private EnumLastName passengerLastName; // 200
    private int age;
    private String pesel;
    private Vector<City> cities;
    private int[][] connectionsTab;
    private City homeCity;
    private City cityFrom;
    private City cityTo;
    private City cityNow;
    private City cityNext;
    private Civil vehicleNow;
    private Vector<City> route = new Vector<>();
    private int routeCount;
    private EnumTrip tripType; // 2
    private boolean threadRun;

    // CONSTRUCTOR

    public Passenger(Vector<City> cities, int[][] connectionsTab)// losowanie wszystkich parametrów
    {
        Random generator = new Random();
        this.passengerName = EnumName.values()[generator.nextInt(200)];
        this.passengerLastName = EnumLastName.values()[generator.nextInt(200)];
        this.age = generator.nextInt(60) + 18;
        peselRand();
        this.cities = cities;
        this.connectionsTab = connectionsTab;
        this.homeCity = cities.get(generator.nextInt(cities.size()));
        while(homeCity.getId().substring(3, 4).equals("0") && homeCity.getId().substring(4, 5).equals("0")) this.homeCity = cities.get(generator.nextInt(cities.size()));
        this.cityNow = homeCity;
        this.cityNext = null;
        this.vehicleNow = null;
        this.threadRun = true;
    }

    private void peselRand()
    {
        Random generator = new Random();
        this.pesel = "";
        pesel += Integer.toString(115 - this.age) + Integer.toString(generator.nextInt(12) + 1) + Integer.toString(generator.nextInt(28) + 1)
                + Integer.toString(generator.nextInt(9) + 1) + Integer.toString(generator.nextInt(9) + 1)
                + Integer.toString(generator.nextInt(9) + 1) + Integer.toString(generator.nextInt(9) + 1)
                + Integer.toString(generator.nextInt(9) + 1);
    }

    // MAIN

    public void run()
    {
        while(threadRun)
        {
            tripRand();
            travel();
        }
    }

    // PERIODIC METHODS

    private void tripRand()
    {
        routeCount = 1;
        //route = null;
        Random generator = new Random();
        if(cityNow == homeCity)
        {
            this.tripType = EnumTrip.values()[generator.nextInt(2)];
            cityFrom = homeCity;
            cityTo = cities.get(generator.nextInt(cities.size()));
            while(cityTo.getId().substring(3, 4).equals("0") && cityTo.getId().substring(4, 5).equals("0")) cityTo = cities.get(generator.nextInt(cities.size()));
        }
        else if(cityNow == cityTo)
        {
            cityFrom = cityTo;
            cityTo = homeCity;
        }
        else // emergency
        {
            cityFrom = cityNow;
            cityTo = homeCity;
        }
        tripRandRoute();
    }

    private void tripRandRoute() // BFS
    {
        int from = cities.indexOf(cityFrom);
        int to = cities.indexOf(cityTo);

        Queue<String> queue = new PriorityQueue<>();
        int[] visited = new int[15];
        for (int i = 0; i < 15; i++) visited[i] = 0;
        int[] parent = new int[15];

        visited[from] = 1;
        parent[from] = -1;
        queue.offer(Integer.toString(from));
        while(!queue.isEmpty())
        {
            String pom;
            pom = queue.poll();
            int u = Integer.valueOf(pom);
            for (int i = 0; i < 15; i ++)
            {
                if (connectionsTab[u][i] == 1 && visited[i] == 0)
                {
                    visited[i] = 1;
                    parent[i] = u;
                    queue.offer(Integer.toString(i));
                }
            }
            visited[u] = 2;
        }
        route.removeAllElements();
        while(to != -1)
        {
            route.add(cities.get(to));
            to = parent[to];
        }
        //Collections.reverse(route);
    }

    private void travel()
    {
        while(cityNow != cityTo)
        {
            transportSearch();
            nextCity();
        }
    }

    public void transportSearch()
    {
        while(vehicleNow == null)
        {
            for (CivilShip x : cityNow.getCivilShipsIn())
            {
                if(x.getNextCity() == cityNext && x.getstate() == EnumState.PASSENGERCHANGE)
                {
                    cityNow.passengerSub(this);
                    x.passengerAdd(this);
                    cityNow = null;
                    vehicleNow = x;
                    break;
                }
            }
            if (vehicleNow != null) break;

            for (CivilPlane x : cityNow.getCivilPlanesIn())
            {
                if(x.getNextCity() == cityNext && x.getstate() == EnumState.PASSENGERCHANGE)
                {
                    cityNow.passengerSub(this);
                    x.passengerAdd(this);
                    cityNow = null;
                    vehicleNow = x;
                    break;
                }
            }
            if (vehicleNow != null) break;

            synchronized (this) {waiting();}
        }
        synchronized (this) {waiting();}
    }

    public void nextCity()
    {
        cityNow = cityNext;
        vehicleNow = null;
        cityNext = route.get(routeCount % route.size());
        routeCount++;
    }

    // OTHER METHODS

    public void waiting()
    {
        try
        {
            wait();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    // GETTERS AND SETTERS

    public EnumName getPassengerNameName()
    {
        return passengerName;
    }

    public EnumLastName getPassengerLastName()
    {
        return passengerLastName;
    }

    public int getAge()
    {
        return age;
    }

    public String getPesel()
    {
        return pesel;
    }

    public EnumTrip getTripType()
    {
        return tripType;
    }

    public City getHomeCity()
    {
        return homeCity;
    }

    public City getCityNow()
    {
        return cityNow;
    }

    public void setCityNow(City cityNow)
    {
        this.cityNow = cityNow;
    }

    public Civil getVehicleNow()
    {
        return vehicleNow;
    }

    public void setVehicleNow(Civil vehicleNow)
    {
        this.vehicleNow = vehicleNow;
    }

    public boolean isThreadRun()
    {
        return threadRun;
    }

    public void setThreadRun(boolean threadRun)
    {
        this.threadRun = threadRun;
    }
}
