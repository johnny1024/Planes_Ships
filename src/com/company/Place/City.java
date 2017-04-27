package com.company.Place;

        import com.company.Passenger.Passenger;
        import com.company.Vehicle.third.CivilPlane;
        import com.company.Vehicle.third.CivilShip;
        import com.company.Vehicle.third.WarPlane;

        import java.util.Vector;
        import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public class City extends Place
{
    private String name;
    private Vector<City> directAirConnections;
    private Vector<Vector<Place>> extendedAirConnections;
    private Vector<City> directWaterConnections;
    private Vector<Vector<Place>> extendedWaterConnections;
    private Vector<Passenger> passengers = new Vector<>();
    private Vector<CivilPlane> civilPlanesIn = new Vector<>();
    private Vector<CivilPlane> civilPlanesWaitingForLanding = new Vector<>();
    private Vector<CivilShip> civilShipsIn = new Vector<>();
    private Vector<CivilShip> civilShipsWaitingForLanding = new Vector<>();
    private Vector<WarPlane> warPlanesIn = new Vector<>();
    private Vector<WarPlane> warPlanesWaitingForLanding = new Vector<>();
    private AtomicInteger civilPlanesSemaphore = new AtomicInteger(1);
    private AtomicInteger civilShipsSemaphore = new AtomicInteger(1);
    private AtomicInteger warPlanesSemaphore = new AtomicInteger(1);
    private int civilPlanesCountMax;
    private int civilShipsCountMax;
    private int warPlanesCountMax;
    private int civilPlanesCountNow;
    private int civilShipsCountNow;
    private int warPlanesCountNow;

    // CONSTRUCTOR

    public City(String id, double x, double y, String name, int civilPlanesCountMax, int civilShipsCountMax, int warPlanesCountMax)
    {
        super(id, x, y);
        this.name = name;
        this.civilPlanesCountMax = civilPlanesCountMax;
        this.civilShipsCountMax = civilShipsCountMax;
        this.warPlanesCountMax = warPlanesCountMax;
        this.civilPlanesCountNow = 0;
        this.civilShipsCountNow = 0;
        this.warPlanesCountNow = 0;
    }

    // METHODS

    public void passengerAdd(Passenger a)
    {
        passengers.add(a);
    }

    public void passengerSub(Passenger a)
    {
        passengers.remove(a);
    }

    public void civilPlaneAdd(CivilPlane x)
    {
        while(!civilPlanesSemaphore.compareAndSet(1, 0));

        if(civilPlanesCountNow < civilPlanesCountMax)
        {
            civilPlanesCountNow++;
            civilPlanesIn.add(x);
            civilPlanesSemaphore.compareAndSet(0, 1);
        }
        else
        {
            civilPlanesWaitingForLanding.add(x);
            civilPlanesSemaphore.compareAndSet(0, 1);
            synchronized (x) {x.waiting();}
        }
    }

    public void civilPlaneSub(CivilPlane x)
    {
        while(!civilPlanesSemaphore.compareAndSet(1, 0));

        civilPlanesIn.remove(x);
        if(!civilPlanesWaitingForLanding.isEmpty())
        {
            CivilPlane z = civilPlanesWaitingForLanding.get(0);
            civilPlanesWaitingForLanding.remove(z);
            civilPlanesIn.add(z);
            synchronized (z) {z.notify();}
        }
        else
        {
            civilPlanesCountNow--;
        }

        civilPlanesSemaphore.compareAndSet(0, 1);
    }

    public void civilShipAdd(CivilShip x)
    {
        while(!civilShipsSemaphore.compareAndSet(1, 0));

        if(civilShipsCountNow < civilShipsCountMax)
        {
            civilShipsCountNow++;
            civilShipsIn.add(x);
            civilShipsSemaphore.compareAndSet(0, 1);
        }
        else
        {
            civilShipsWaitingForLanding.add(x);
            civilShipsSemaphore.compareAndSet(0, 1);
            synchronized (x) {x.waiting();}
        }
    }

    public void civilShipSub(CivilShip x)
    {
        while(!civilShipsSemaphore.compareAndSet(1, 0));

        civilShipsIn.remove(x);
        if(!civilShipsWaitingForLanding.isEmpty())
        {
            CivilShip z = civilShipsWaitingForLanding.get(0);
            civilShipsWaitingForLanding.remove(z);
            civilShipsIn.add(z);
            synchronized (z) {z.notify();}
        }
        else
        {
            civilShipsCountNow--;
        }

        civilShipsSemaphore.compareAndSet(0, 1);
    }

    public void warPlaneAdd(WarPlane x)
    {
        while(!warPlanesSemaphore.compareAndSet(1, 0));

        if(warPlanesCountNow < warPlanesCountMax)
        {
            warPlanesCountNow++;
            warPlanesIn.add(x);
            warPlanesSemaphore.compareAndSet(0, 1);
        }
        else
        {
            warPlanesWaitingForLanding.add(x);
            warPlanesSemaphore.compareAndSet(0, 1);
            synchronized (x) {x.waiting();}
        }
    }

    public void warPlaneSub(WarPlane x)
    {
        while(!warPlanesSemaphore.compareAndSet(1, 0));

        warPlanesIn.remove(x);
        if(!warPlanesWaitingForLanding.isEmpty())
        {
            WarPlane z = warPlanesWaitingForLanding.get(0);
            warPlanesWaitingForLanding.remove(z);
            warPlanesIn.add(z);
            synchronized (z) {z.notify();}
        }
        else
        {
            warPlanesCountNow--;
        }

        warPlanesSemaphore.compareAndSet(0, 1);
    }

    // GETTERS AND SETTERS


    public Vector<Vector<Place>> getExtendedWaterConnections()
    {
        return extendedWaterConnections;
    }

    public void setExtendedWaterConnections(Vector<Vector<Place>> extendedWaterConnections)
    {
        this.extendedWaterConnections = extendedWaterConnections;
    }

    public Vector<City> getDirectWaterConnections()
    {
        return directWaterConnections;
    }

    public void setDirectWaterConnections(Vector<City> directWaterConnections)
    {
        this.directWaterConnections = directWaterConnections;
    }

    public Vector<Vector<Place>> getExtendedAirConnections()
    {
        return extendedAirConnections;
    }

    public void setExtendedAirConnections(Vector<Vector<Place>> extendedAirConnections)
    {
        this.extendedAirConnections = extendedAirConnections;
    }

    public Vector<City> getDirectAirConnections()
    {
        return directAirConnections;
    }

    public void setDirectAirConnections(Vector<City> directAirConnections)
    {
        this.directAirConnections = directAirConnections;
    }

    public Vector<CivilPlane> getCivilPlanesIn()
    {
        return civilPlanesIn;
    }

    public Vector<CivilShip> getCivilShipsIn()
    {
        return civilShipsIn;
    }

    public Vector<WarPlane> getWarPlanesIn()
    {
        return warPlanesIn;
    }

    public Vector<Passenger> getPassengers()
    {
        return passengers;
    }
}
