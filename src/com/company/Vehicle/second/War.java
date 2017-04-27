package com.company.Vehicle.second;

import com.company.Place.City;
import com.company.Vehicle.utilities.EnumArmament;
import com.company.Vehicle.first.Vehicle;

import java.util.Vector;

/**
 * Created by johnny1024 on 2015-10-25.
 */
public abstract class War extends Vehicle
{
    EnumArmament weapon;

    // CONSTRUCTOR

    public War(String id, int crewNumber, int maxSpeed, int fuelMax, Vector<City> Cities, EnumArmament weapon)
    {
        super(id, crewNumber, maxSpeed, fuelMax, Cities);
        this.weapon = weapon;
    }

    // GETTERS AND SETTERS

    public EnumArmament getWeapon()
    {
        return weapon;
    }
}
