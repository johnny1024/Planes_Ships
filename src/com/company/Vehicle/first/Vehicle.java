package com.company.Vehicle.first;

import com.company.Place.City;
import com.company.Place.Place;
import com.company.Vehicle.utilities.EnumState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public abstract class Vehicle extends Thread implements Serializable
{
    private String id;
    // id - XX12 - XX: CA-civilPlane, CS-civilShip, WA-warPlane, WS-warShip, 12: number
    private double x;
    private double y;
    private int crewNumber;
    private int maxSpeed;
    private int fuelMax;
    private int fuelLevel;
    private Vector<City> Cities;
    private Vector<City> route = new Vector<>();
    private City nowCity;
    private City nextCity;
    private int nextCityNumber;
    private Vector<Place> smallRoute;
    private Place nowStation;
    private Place nextStation;
    private int nextStationNumber;
    private EnumState state;
    private boolean threadRun;
    private Canvas icon;
    private GraphicsContext gcIcon;
    private Image imageIcon;

    // CONSTRUCTOR

    public Vehicle(String id, int crewNumber, int maxSpeed, int fuelMax, Vector<City> Cities)
    {
        this.id = id;
        this.crewNumber = crewNumber;
        this.maxSpeed = maxSpeed;
        this.fuelMax = fuelMax;
        this.fuelLevel = fuelMax;
        this.Cities = Cities;
        firstCityRand();
        routeRand();
        this.x = route.get(0).getX();
        this.y = route.get(0).getY();
        this.nowCity = route.get(0);
        this.nextCity = route.get(0);
        this.nextCityNumber = 0;
        //this.smallRoute = this.nowCity.getExtendedConnections().get(this.nowCity.getDirectConnections().indexOf(this.nextCity));
        this.nowStation = this.nowCity;
        //this.nextStation = this.smallRoute.get(0);
        //this.nextStationNumber = 0;
        this.state = EnumState.PARKING;
        this.threadRun = true;
    }

    // PERIODIC METHODS

    protected void travel() // trygonometry
    {
        if(state != EnumState.EMERGENCY) this.state = EnumState.TRAVELING;
        int jump = 2; // pixels

        double arc;
        if (nextStation.getX() - x == 0) arc = 0;
        else
        {
            double a = 0;
            double b = 0;


            arc = Math.atan((nextStation.getY() - y) / (nextStation.getX() - x));
            arc = Math.atan(b / a);
        }

        while(Math.abs(x - nextStation.getX()) > 5 && Math.abs(y - nextStation.getY()) > 5)
        {
            if(this.state == EnumState.EMERGENCY)
            {
                break;
            }

            this.x += Math.cos(arc) * jump;
            this.y += Math.sin(arc) * jump;

            this.fuelLevel -= jump;

            //repaint

            try
            {
                sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if(this.state == EnumState.EMERGENCY)
        {
            emergencyLanding();
        }
        else
        {
            this.x = this.nextStation.getX();
            this.y = this.nextStation.getY();
            nowStation = nextStation;
        }
    }

    protected void travel2() // tales
    {
        if(state != EnumState.EMERGENCY) this.state = EnumState.TRAVELING;
        double jump = 2; // pixels

        double travelLength = Math.sqrt(Math.pow((getX() - nextStation.getX()), 2) + Math.pow((getY() - nextStation.getY()), 2));

        int xplus = (int)(Math.round((jump * (nextStation.getX() - getX())) / travelLength));
        int yplus = (int)(Math.round((jump * (nextStation.getY() - getY())) / travelLength));

        while(Math.abs(getX() - nextStation.getX()) > 5 && Math.abs(getY() - nextStation.getY()) > 5)
        {
            if(this.state == EnumState.EMERGENCY)
            {
                break;
            }

            this.x += xplus;
            this.y += yplus;

            this.fuelLevel -= jump;

            //repaint

            try
            {
                sleep(50);
                //sleep(1/this.maxSpeed * 10000);
                //sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

        if(this.state == EnumState.EMERGENCY)
        {
            emergencyLanding();
        }
        else
        {
            this.x = this.nextStation.getX();
            this.y = this.nextStation.getY();
            nowStation = nextStation;
        }
    }

    protected void travel3() // working
    {
        if(state != EnumState.EMERGENCY) this.state = EnumState.TRAVELING;
        double jump = 2; // pixels

        double travelDistance = Math.sqrt(Math.pow((getX() - nextStation.getX()), 2) + Math.pow((getY() - nextStation.getY()), 2));;
        int n = (int)Math.ceil(travelDistance / jump);
        double xAdd = (nextStation.getX() - x) / n;
        double yAdd = (nextStation.getY() - y) / n;

        for (int i = 0; i < n; i++)
        {
            if(this.state == EnumState.EMERGENCY)
            {
                break;
            }

            x += xAdd;
            y += yAdd;

            this.fuelLevel -= jump;

            //repaint

            try
            {
                sleep(5000 / maxSpeed);
                //sleep(1/this.maxSpeed * 10000);
                //sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if(this.state == EnumState.EMERGENCY)
        {
            emergencyLanding();
        }
        else
        {
            this.x = this.nextStation.getX();
            this.y = this.nextStation.getY();
            nowStation = nextStation;
        }
    }

    protected void starting()
    {
        this.state = EnumState.STARTING;
        try
        {
            sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void parking()
    {
        this.state = EnumState.PARKING;
        try
        {
            sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void refueling()
    {
        this.state = EnumState.REFUELING;
        try
        {
            sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.fuelLevel = this.fuelMax;
    }

    protected void nextPoint()
    {
        nowStation = nextStation;
        if(!smallRoute.isEmpty()) nextStationNumber = (nextStationNumber + 1) % smallRoute.size();
        nextStation = smallRoute.get(nextStationNumber);
    }

    protected void nextCity()
    {
        nowCity = nextCity;
        nextCityNumber = (nextCityNumber + 1) % route.size();
        nextCity = getRoute().get(nextCityNumber);

        nextStationNumber = 0;
        downloadSmallRoute();
    }

    protected void downloadSmallRoute()
    {
        if (id.substring(1, 2).equals("P"))
        {
            smallRoute = null;
            smallRoute = nowCity.getExtendedAirConnections().get(nowCity.getDirectAirConnections().indexOf(nextCity));
            setNowStation(getNowCity());
            setNextStation(getSmallRoute().get(0));
            setNextStationNumber(0);
        }
        else
        {
            smallRoute = null;
            smallRoute = nowCity.getExtendedWaterConnections().get(nowCity.getDirectWaterConnections().indexOf(nextCity));
            setNowStation(getNowCity());
            setNextStation(getSmallRoute().get(0));
            setNextStationNumber(0);
        }
    }

    // OTHER METHODS

    public void firstCityRand()
    {
        Random gen = new Random();
        City x = Cities.get(gen.nextInt(Cities.size()));
        // ship
        if (id.substring(1, 2).equals("S")) while (x.getId().substring(4, 5).equals("0")) x = Cities.get(gen.nextInt(Cities.size()));
        // civil plane
        else if (id.substring(0, 1).equals("C")) while (x.getId().substring(3, 4).equals("0")) x = Cities.get(gen.nextInt(Cities.size()));
        // war plane
        else while (x.getId().substring(5, 6).equals("0")) x = Cities.get(gen.nextInt(Cities.size()));
        route.add(x);
    }

    public void routeRand()
    {
        nextCityNumber = 0;
        int count = 1;
        Random gen = new Random();
        City adding;
        if (id.substring(1, 2).equals("P"))
        {
            adding = route.get(count - 1).getDirectAirConnections().get(gen.nextInt(route.get(count - 1).getDirectAirConnections().size()));
            route.add(adding);
            count++;
            while(count < 5)
            {
                while (adding == route.get(count - 2) || adding == route.get(count - 1)) adding = route.get(count - 1).getDirectAirConnections().get(gen.nextInt(route.get(count - 1).getDirectAirConnections().size()));
                route.add(adding);
                count++;
            }
            //back
            for (int i = count - 2; i >= 1; i--) route.add(route.get(i));
        }
        else
        {
            adding = route.get(count - 1).getDirectWaterConnections().get(gen.nextInt(route.get(count - 1).getDirectWaterConnections().size()));
            route.add(adding);
            count++;
            while(count < 5)
            {
                while (adding == route.get(count - 2) || adding == route.get(count - 1)) adding = route.get(count - 1).getDirectWaterConnections().get(gen.nextInt(route.get(count - 1).getDirectWaterConnections().size()));
                route.add(adding);
                count++;
            }
            // back
            for (int i = count - 2; i >= 1; i--) route.add(route.get(i));
        }
    }

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

    public void emergencyLanding() {}

    // GETTERS AND SETTERS

    public String getid()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public int getCrewNumber()
    {
        return crewNumber;
    }

    public void setCrewNumber(int crewNumber)
    {
        this.crewNumber = crewNumber;
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }

    public int getFuelMax()
    {
        return fuelMax;
    }

    public void setFuelMax(int fuelMax)
    {
        this.fuelMax = fuelMax;
    }

    public int getFuelLevel()
    {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel)
    {
        this.fuelLevel = fuelLevel;
    }

    public Vector<City> getCities()
    {
        return Cities;
    }

    public void setCities(Vector<City> cities)
    {
        Cities = cities;
    }

    public Vector<City> getRoute()
    {
        return route;
    }

    public void setRoute(Vector<City> route)
    {
        this.route = route;
    }

    public City getNowCity()
    {
        return nowCity;
    }

    public void setNowCity(City nowCity)
    {
        this.nowCity = nowCity;
    }

    public City getNextCity()
    {
        return nextCity;
    }

    public void setNextCity(City nextCity)
    {
        this.nextCity = nextCity;
    }

    public int getNextCityNumber()
    {
        return nextCityNumber;
    }

    public void setNextCityNumber(int nextCityNumber)
    {
        this.nextCityNumber = nextCityNumber;
    }

    public Vector<Place> getSmallRoute()
    {
        return smallRoute;
    }

    public void setSmallRoute(Vector<Place> smallRoute)
    {
        this.smallRoute = smallRoute;
    }

    public Place getNowStation()
    {
        return nowStation;
    }

    public void setNowStation(Place nowStation)
    {
        this.nowStation = nowStation;
    }

    public Place getNextStation()
    {
        return nextStation;
    }

    public void setNextStation(Place nextStation)
    {
        this.nextStation = nextStation;
    }

    public int getNextStationNumber()
    {
        return nextStationNumber;
    }

    public void setNextStationNumber(int nextStationNumber)
    {
        this.nextStationNumber = nextStationNumber;
    }

    public EnumState getstate()
    {
        return state;
    }

    public void setState(EnumState state)
    {
        this.state = state;
    }

    public boolean isThreadRun()
    {
        return threadRun;
    }

    public void setThreadRun(boolean threadRun)
    {
        this.threadRun = threadRun;
    }

    public Canvas getIcon()
    {
        return icon;
    }

    public void setIcon(Canvas icon)
    {
        this.icon = icon;
    }

    public GraphicsContext getGcIcon()
    {
        return gcIcon;
    }

    public void setGcIcon(GraphicsContext gcIcon)
    {
        this.gcIcon = gcIcon;
    }

    public Image getImageIcon()
    {
        return imageIcon;
    }

    public void setImageIcon(Image imageIcon)
    {
        this.imageIcon = imageIcon;
    }
}
