package com.company.Place;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by johnny1024 on 2015-12-28.
 */
public class World implements Serializable
{
    private Vector<City> Cities = new Vector<>();
    private int[][] connectionsTab = new int[15][15];

    // CONSTRUCTOR

    public World()
    {
        //tablica po³¹czeñ
        for(int i = 0; i < 15; i++) for(int j = 0; j < 15; j++) connectionsTab[i][j] = 0;
        connectionsTab[0][1] = 1; connectionsTab[0][3] = 1; connectionsTab[0][6] = 1;
        connectionsTab[1][0] = 1; connectionsTab[1][2] = 1; connectionsTab[1][7] = 1; connectionsTab[1][10] = 1; connectionsTab[1][11] = 1;
        connectionsTab[2][1] = 1; connectionsTab[2][3] = 1; connectionsTab[2][4] = 1;
        connectionsTab[3][0] = 1; connectionsTab[3][2] = 1; connectionsTab[3][5] = 1; connectionsTab[3][8] = 1;
        connectionsTab[4][2] = 1; connectionsTab[4][5] = 1; connectionsTab[4][7] = 1; connectionsTab[4][9] = 1;
        connectionsTab[5][3] = 1; connectionsTab[5][4] = 1; connectionsTab[5][12] = 1;
        connectionsTab[6][0] = 1; connectionsTab[6][8] = 1; connectionsTab[6][13] = 1;
        connectionsTab[7][1] = 1; connectionsTab[7][4] = 1;
        connectionsTab[8][3] = 1; connectionsTab[8][6] = 1; connectionsTab[8][14] = 1;
        connectionsTab[9][4] = 1; connectionsTab[9][11] = 1; connectionsTab[9][12] = 1;
        connectionsTab[10][1] = 1; connectionsTab[10][11] = 1;
        connectionsTab[11][1] = 1; connectionsTab[11][9] = 1; connectionsTab[11][10] = 1; connectionsTab[11][12] = 1; connectionsTab[11][13] = 1;
        connectionsTab[12][5] = 1; connectionsTab[12][9] = 1; connectionsTab[12][11] = 1; connectionsTab[12][14] = 1;
        connectionsTab[13][6] = 1; connectionsTab[13][11] = 1; connectionsTab[13][14] = 1;
        connectionsTab[14][8] = 1; connectionsTab[14][12] = 1; connectionsTab[14][13] = 1;

        // miasta
        int capacity = 2;
        City x = new City("C00001", 142, 76, "Boston", 0, 0, capacity);
        Cities.add(x);
        x = new City("C01100", 248, 112, "London", capacity, 0, 0);
        Cities.add(x);
        x = new City("C02100", 324, 85, "York", capacity, 0, 0);
        Cities.add(x);
        x = new City("C03110", 403, 52, "Carlin", capacity, capacity, 0);
        Cities.add(x);
        x = new City("C04100", 561, 99, "Thais", capacity, 0, 0);
        Cities.add(x);
        x = new City("C05001", 769, 27, "Venore", 0, 0, capacity);
        Cities.add(x);
        x = new City("C06110", 84, 190, "Kazordoon", capacity, capacity, 0);
        Cities.add(x);
        x = new City("C07001", 377, 221, "Tristram", 0, 0, capacity);
        Cities.add(x);
        x = new City("C08110", 639, 203, "Gondor", capacity, capacity, 0);
        Cities.add(x);
        x = new City("C09100", 533, 298, "BaradDur", capacity, 0, 0);
        Cities.add(x);
        x = new City("C10100", 43, 407, "Endor", capacity, 0, 0);
        Cities.add(x);
        x = new City("C11101", 360, 435, "Tatooine", capacity, 0, capacity);
        Cities.add(x);
        x = new City("C12100", 707, 443, "Corusant", capacity, 0, 0);
        Cities.add(x);
        x = new City("C13011", 101, 518, "SouthHampton", 0, capacity, capacity);
        Cities.add(x);
        x = new City("C14111", 544, 480, "Birmingham", capacity, capacity, capacity);
        Cities.add(x);

        // skrzy¿owania powietrzne
        Place y = new Place("I00", 193, 192);
        Vector<Place> airIntersections = new Vector<>();
        airIntersections.add(y);
        y = new Place("I01", 276, 194);
        airIntersections.add(y);
        y = new Place("I02", 348, 196);
        airIntersections.add(y);
        y = new Place("I03", 413, 197);
        airIntersections.add(y);
        y = new Place("I04", 469, 94);
        airIntersections.add(y);
        y = new Place("I05", 519, 127);
        airIntersections.add(y);
        y = new Place("I06", 555, 149);
        airIntersections.add(y);
        y = new Place("I07", 548, 199);
        airIntersections.add(y);
        y = new Place("I08", 591, 347);
        airIntersections.add(y);
        y = new Place("I09", 559, 439);
        airIntersections.add(y);
        y = new Place("I10", 94, 410);
        airIntersections.add(y);
        y = new Place("I11", 91, 338);
        airIntersections.add(y);

        // skrzy¿owania morskie
        y = new Place("I20", 312, 196);
        Vector<Place> waterIntersections = new Vector<>();
        waterIntersections.add(y);
        y = new Place("I21", 452, 199);
        waterIntersections.add(y);
        y = new Place("I22", 483, 295);
        waterIntersections.add(y);
        y = new Place("I23", 361, 366);
        waterIntersections.add(y);
        y = new Place("I24", 247, 294);
        waterIntersections.add(y);

        // miasto 0
        Vector<City> a00 = new Vector<>();
        Vector<Vector<Place>> ax00 = new Vector<>();
        a00.add(Cities.get(1));
        a00.add(Cities.get(3));
        a00.add(Cities.get(6));
        Cities.get(0).setDirectAirConnections(a00);
        ax00.add(new Vector<>());
        ax00.add(new Vector<>());
        ax00.add(new Vector<>());
        ax00.get(0).add(Cities.get(1));
        ax00.get(1).add(Cities.get(3));
        ax00.get(2).add(Cities.get(6));
        Cities.get(0).setExtendedAirConnections(ax00);
        Vector<City> w00 = new Vector<>();
        Vector<Vector<Place>> wx00 = new Vector<>();
        Cities.get(0).setDirectWaterConnections(w00);
        Cities.get(0).setExtendedWaterConnections(wx00);

        // miasto 1
        Vector<City> a01 = new Vector<>();
        Vector<Vector<Place>> ax01 = new Vector<>();
        a01.add(Cities.get(0));
        a01.add(Cities.get(2));
        a01.add(Cities.get(7));
        a01.add(Cities.get(10));
        a01.add(Cities.get(11));
        Cities.get(1).setDirectAirConnections(a01);
        ax01.add(new Vector<>());
        ax01.add(new Vector<>());
        ax01.add(new Vector<>());
        ax01.add(new Vector<>());
        ax01.add(new Vector<>());
        ax01.get(0).add(Cities.get(0));
        ax01.get(1).add(Cities.get(2));
        ax01.get(2).add(airIntersections.get(2)); ax01.get(2).add(Cities.get(7));
        ax01.get(3).add(airIntersections.get(0)); ax01.get(3).add(airIntersections.get(11)); ax01.get(3).add(Cities.get(10));
        ax01.get(4).add(airIntersections.get(1)); ax01.get(4).add(Cities.get(11));
        Cities.get(1).setExtendedAirConnections(ax01);
        Vector<City> w01 = new Vector<>();
        Vector<Vector<Place>> wx01 = new Vector<>();
        Cities.get(1).setDirectWaterConnections(w01);
        Cities.get(1).setExtendedWaterConnections(wx01);

        // miasto 2
        Vector<City> a02 = new Vector<>();
        Vector<Vector<Place>> ax02 = new Vector<>();
        a02.add(Cities.get(1));
        a02.add(Cities.get(3));
        a02.add(Cities.get(4));
        Cities.get(2).setDirectAirConnections(a02);
        ax02.add(new Vector<>());
        ax02.add(new Vector<>());
        ax02.add(new Vector<>());
        ax02.get(0).add(Cities.get(1));
        ax02.get(1).add(Cities.get(3));
        ax02.get(2).add(airIntersections.get(4)); ax02.get(2).add(Cities.get(4));
        Cities.get(2).setExtendedAirConnections(ax02);
        Vector<City> w02 = new Vector<>();
        Vector<Vector<Place>> wx02 = new Vector<>();
        Cities.get(2).setDirectWaterConnections(w02);
        Cities.get(2).setExtendedWaterConnections(wx02);

        // miasto 3
        Vector<City> a03 = new Vector<>();
        Vector<Vector<Place>> ax03 = new Vector<>();
        a03.add(Cities.get(0));
        a03.add(Cities.get(2));
        a03.add(Cities.get(5));
        a03.add(Cities.get(8));
        Cities.get(3).setDirectAirConnections(a03);
        ax03.add(new Vector<>());
        ax03.add(new Vector<>());
        ax03.add(new Vector<>());
        ax03.add(new Vector<>());
        ax03.get(0).add(Cities.get(0));
        ax03.get(1).add(Cities.get(2));
        ax03.get(2).add(Cities.get(5));
        ax03.get(3).add(Cities.get(8));
        Cities.get(3).setExtendedAirConnections(ax03);
        Vector<City> w03 = new Vector<>();
        Vector<Vector<Place>> wx03 = new Vector<>();
        w03.add(Cities.get(13));
        w03.add(Cities.get(14));
        Cities.get(3).setDirectWaterConnections(w03);
        wx03.add(new Vector<>());
        wx03.add(new Vector<>());
        wx03.get(0).add(waterIntersections.get(0)); wx03.get(0).add(waterIntersections.get(4)); wx03.get(0).add(Cities.get(13));
        wx03.get(1).add(waterIntersections.get(1)); wx03.get(1).add(waterIntersections.get(2)); wx03.get(1).add(Cities.get(14));
        Cities.get(3).setExtendedWaterConnections(wx03);

        // miasto 4
        Vector<City> a04 = new Vector<>();
        Vector<Vector<Place>> ax04 = new Vector<>();
        a04.add(Cities.get(2));
        a04.add(Cities.get(5));
        a04.add(Cities.get(7));
        a04.add(Cities.get(9));
        Cities.get(4).setDirectAirConnections(a04);
        ax04.add(new Vector<>());
        ax04.add(new Vector<>());
        ax04.add(new Vector<>());
        ax04.add(new Vector<>());
        ax04.get(0).add(airIntersections.get(4)); ax04.get(0).add(Cities.get(2));
        ax04.get(1).add(Cities.get(5));
        ax04.get(2).add(airIntersections.get(5)); ax04.get(2).add(airIntersections.get(3)); ax04.get(2).add(Cities.get(7));
        ax04.get(3).add(airIntersections.get(6)); ax04.get(3).add(airIntersections.get(7)); ax04.get(3).add(Cities.get(9));
        Cities.get(4).setExtendedAirConnections(ax04);
        Vector<City> w04 = new Vector<>();
        Vector<Vector<Place>> wx04 = new Vector<>();
        Cities.get(4).setDirectWaterConnections(w04);
        Cities.get(4).setExtendedWaterConnections(wx04);

        // miasto 5
        Vector<City> a05 = new Vector<>();
        Vector<Vector<Place>> ax05 = new Vector<>();
        a05.add(Cities.get(3));
        a05.add(Cities.get(4));
        a05.add(Cities.get(12));
        Cities.get(5).setDirectAirConnections(a05);
        ax05.add(new Vector<>());
        ax05.add(new Vector<>());
        ax05.add(new Vector<>());
        ax05.get(0).add(Cities.get(3));
        ax05.get(1).add(Cities.get(4));
        ax05.get(2).add(Cities.get(12));
        Cities.get(5).setExtendedAirConnections(ax05);
        Vector<City> w05 = new Vector<>();
        Vector<Vector<Place>> wx05 = new Vector<>();
        Cities.get(5).setDirectWaterConnections(w05);
        Cities.get(5).setExtendedWaterConnections(wx05);

        // miasto 6
        Vector<City> a06 = new Vector<>();
        Vector<Vector<Place>> ax06 = new Vector<>();
        a06.add(Cities.get(0));
        a06.add(Cities.get(8));
        a06.add(Cities.get(13));
        Cities.get(6).setDirectAirConnections(a06);
        ax06.add(new Vector<>());
        ax06.add(new Vector<>());
        ax06.add(new Vector<>());
        ax06.get(0).add(Cities.get(0));
        ax06.get(1).add(airIntersections.get(0)); ax06.get(1).add(airIntersections.get(1)); ax06.get(1).add(airIntersections.get(2)); ax06.get(1).add(airIntersections.get(3)); ax06.get(1).add(airIntersections.get(7)); ax06.get(1).add(Cities.get(8));
        ax06.get(2).add(airIntersections.get(11)); ax06.get(2).add(airIntersections.get(10)); ax06.get(2).add(Cities.get(13));
        Cities.get(6).setExtendedAirConnections(ax06);
        Vector<City> w06 = new Vector<>();
        Vector<Vector<Place>> wx06 = new Vector<>();
        w06.add(Cities.get(8));
        w06.add(Cities.get(14));
        Cities.get(6).setDirectWaterConnections(w06);
        wx06.add(new Vector<>());
        wx06.add(new Vector<>());
        wx06.get(0).add(waterIntersections.get(0)); wx06.get(0).add(waterIntersections.get(1)); wx06.get(0).add(Cities.get(8));
        wx06.get(1).add(waterIntersections.get(4)); wx06.get(1).add(waterIntersections.get(3)); wx06.get(1).add(Cities.get(14));
        Cities.get(6).setExtendedWaterConnections(wx06);

        // miasto 7
        Vector<City> a07 = new Vector<>();
        Vector<Vector<Place>> ax07 = new Vector<>();
        a07.add(Cities.get(1));
        a07.add(Cities.get(4));
        Cities.get(7).setDirectAirConnections(a07);
        ax07.add(new Vector<>());
        ax07.add(new Vector<>());
        ax07.get(0).add(airIntersections.get(2)); ax07.get(0).add(Cities.get(1));
        ax07.get(1).add(airIntersections.get(3)); ax07.get(1).add(airIntersections.get(5)); ax07.get(1).add(Cities.get(4));
        Cities.get(7).setExtendedAirConnections(ax07);
        Vector<City> w07 = new Vector<>();
        Vector<Vector<Place>> wx07 = new Vector<>();
        Cities.get(7).setDirectWaterConnections(w07);
        Cities.get(7).setExtendedWaterConnections(wx07);

        // miasto 8
        Vector<City> a08 = new Vector<>();
        Vector<Vector<Place>> ax08 = new Vector<>();
        a08.add(Cities.get(3));
        a08.add(Cities.get(6));
        a08.add(Cities.get(14));
        Cities.get(8).setDirectAirConnections(a08);
        ax08.add(new Vector<>());
        ax08.add(new Vector<>());
        ax08.add(new Vector<>());
        ax08.get(0).add(airIntersections.get(6)); ax08.get(0).add(airIntersections.get(5)); ax08.get(0).add(airIntersections.get(4)); ax08.get(0).add(Cities.get(3));
        ax08.get(1).add(airIntersections.get(7)); ax08.get(1).add(airIntersections.get(3)); ax08.get(1).add(airIntersections.get(2)); ax08.get(1).add(airIntersections.get(1)); ax08.get(1).add(airIntersections.get(0)); ax08.get(1).add(Cities.get(6));
        ax08.get(2).add(airIntersections.get(8)); ax08.get(2).add(airIntersections.get(9)); ax08.get(2).add(Cities.get(14));
        Cities.get(8).setExtendedAirConnections(ax08);
        Vector<City> w08 = new Vector<>();
        Vector<Vector<Place>> wx08 = new Vector<>();
        w08.add(Cities.get(6));
        w08.add(Cities.get(13));
        Cities.get(8).setDirectWaterConnections(w08);
        wx08.add(new Vector<>());
        wx08.add(new Vector<>());
        wx08.get(0).add(waterIntersections.get(1)); wx08.get(0).add(waterIntersections.get(0)); wx08.get(0).add(Cities.get(6));
        wx08.get(1).add(waterIntersections.get(2)); wx08.get(1).add(waterIntersections.get(3)); wx08.get(1).add(Cities.get(13));
        Cities.get(8).setExtendedWaterConnections(wx08);

        // miasto 9
        Vector<City> a09 = new Vector<>();
        Vector<Vector<Place>> ax09 = new Vector<>();
        a09.add(Cities.get(4));
        a09.add(Cities.get(11));
        a09.add(Cities.get(12));
        Cities.get(9).setDirectAirConnections(a09);
        ax09.add(new Vector<>());
        ax09.add(new Vector<>());
        ax09.add(new Vector<>());
        ax09.get(0).add(airIntersections.get(7)); ax09.get(0).add(airIntersections.get(6)); ax09.get(0).add(Cities.get(4));
        ax09.get(1).add(Cities.get(11));
        ax09.get(2).add(airIntersections.get(8)); ax09.get(2).add(Cities.get(12));
        Cities.get(9).setExtendedAirConnections(ax09);
        Vector<City> w09 = new Vector<>();
        Vector<Vector<Place>> wx09 = new Vector<>();
        Cities.get(9).setDirectWaterConnections(w09);
        Cities.get(9).setExtendedWaterConnections(wx09);

        // miasto 10
        Vector<City> a10 = new Vector<>();
        Vector<Vector<Place>> ax10 = new Vector<>();
        a10.add(Cities.get(1));
        a10.add(Cities.get(11));
        Cities.get(10).setDirectAirConnections(a10);
        ax10.add(new Vector<>());
        ax10.add(new Vector<>());
        ax10.get(0).add(airIntersections.get(11)); ax10.get(0).add(airIntersections.get(0)); ax10.get(0).add(Cities.get(1));
        ax10.get(1).add(airIntersections.get(10)); ax10.get(1).add(Cities.get(11));
        Cities.get(10).setExtendedAirConnections(ax10);
        Vector<City> w10 = new Vector<>();
        Vector<Vector<Place>> wx10 = new Vector<>();
        Cities.get(10).setDirectWaterConnections(w10);
        Cities.get(10).setExtendedWaterConnections(wx10);

        // miasto 11
        Vector<City> a11 = new Vector<>();
        Vector<Vector<Place>> ax11 = new Vector<>();
        a11.add(Cities.get(1));
        a11.add(Cities.get(9));
        a11.add(Cities.get(10));
        a11.add(Cities.get(12));
        a11.add(Cities.get(13));
        Cities.get(11).setDirectAirConnections(a11);
        ax11.add(new Vector<>());
        ax11.add(new Vector<>());
        ax11.add(new Vector<>());
        ax11.add(new Vector<>());
        ax11.add(new Vector<>());
        ax11.get(0).add(airIntersections.get(1)); ax11.get(0).add(Cities.get(1));
        ax11.get(1).add(Cities.get(9));
        ax11.get(2).add(airIntersections.get(10)); ax11.get(2).add(Cities.get(10));
        ax11.get(3).add(airIntersections.get(9)); ax11.get(3).add(Cities.get(12));
        ax11.get(4).add(Cities.get(13));
        Cities.get(11).setExtendedAirConnections(ax11);
        Vector<City> w11 = new Vector<>();
        Vector<Vector<Place>> wx11 = new Vector<>();
        Cities.get(11).setDirectWaterConnections(w11);
        Cities.get(11).setExtendedWaterConnections(wx11);

        // miasto 12
        Vector<City> a12 = new Vector<>();
        Vector<Vector<Place>> ax12 = new Vector<>();
        a12.add(Cities.get(5));
        a12.add(Cities.get(9));
        a12.add(Cities.get(11));
        a12.add(Cities.get(14));
        Cities.get(12).setDirectAirConnections(a12);
        ax12.add(new Vector<>());
        ax12.add(new Vector<>());
        ax12.add(new Vector<>());
        ax12.add(new Vector<>());
        ax12.get(0).add(Cities.get(5));
        ax12.get(1).add(airIntersections.get(8)); ax12.get(1).add(Cities.get(9));
        ax12.get(2).add(airIntersections.get(9)); ax12.get(2).add(Cities.get(11));
        ax12.get(3).add(Cities.get(14));
        Cities.get(12).setExtendedAirConnections(ax12);
        Vector<City> w12 = new Vector<>();
        Vector<Vector<Place>> wx12 = new Vector<>();
        Cities.get(12).setDirectWaterConnections(w12);
        Cities.get(12).setExtendedWaterConnections(wx12);

        // miasto 13
        Vector<City> a13 = new Vector<>();
        Vector<Vector<Place>> ax13 = new Vector<>();
        a13.add(Cities.get(6));
        a13.add(Cities.get(11));
        a13.add(Cities.get(14));
        Cities.get(13).setDirectAirConnections(a13);
        ax13.add(new Vector<>());
        ax13.add(new Vector<>());
        ax13.add(new Vector<>());
        ax13.get(0).add(airIntersections.get(10)); ax13.get(0).add(airIntersections.get(11)); ax13.get(0).add(Cities.get(6));
        ax13.get(1).add(Cities.get(11));
        ax13.get(2).add(Cities.get(14));
        Cities.get(13).setExtendedAirConnections(ax13);
        Vector<City> w13 = new Vector<>();
        Vector<Vector<Place>> wx13 = new Vector<>();
        w13.add(Cities.get(3));
        w13.add(Cities.get(8));
        Cities.get(13).setDirectWaterConnections(w13);
        wx13.add(new Vector<>());
        wx13.add(new Vector<>());
        wx13.get(0).add(waterIntersections.get(4)); wx13.get(0).add(waterIntersections.get(0)); wx13.get(0).add(Cities.get(3));
        wx13.get(1).add(waterIntersections.get(3)); wx13.get(1).add(waterIntersections.get(2)); wx13.get(1).add(Cities.get(8));
        Cities.get(13).setExtendedWaterConnections(wx13);

        // miasto 14
        Vector<City> a14 = new Vector<>();
        Vector<Vector<Place>> ax14 = new Vector<>();
        a14.add(Cities.get(8));
        a14.add(Cities.get(12));
        a14.add(Cities.get(13));
        Cities.get(14).setDirectAirConnections(a14);
        ax14.add(new Vector<>());
        ax14.add(new Vector<>());
        ax14.add(new Vector<>());
        ax14.get(0).add(airIntersections.get(9)); ax14.get(0).add(airIntersections.get(8)); ax14.get(0).add(Cities.get(8));
        ax14.get(1).add(Cities.get(12));
        ax14.get(2).add(Cities.get(13));
        Cities.get(14).setExtendedAirConnections(ax14);
        Vector<City> w14 = new Vector<>();
        Vector<Vector<Place>> wx14 = new Vector<>();
        w14.add(Cities.get(3));
        w14.add(Cities.get(6));
        Cities.get(14).setDirectWaterConnections(w14);
        wx14.add(new Vector<>());
        wx14.add(new Vector<>());
        wx14.get(0).add(waterIntersections.get(2)); wx14.get(0).add(waterIntersections.get(1)); wx14.get(0).add(Cities.get(3));
        wx14.get(1).add(waterIntersections.get(3)); wx14.get(1).add(waterIntersections.get(4)); wx14.get(1).add(Cities.get(6));
        Cities.get(14).setExtendedWaterConnections(wx14);
    }

    // GETTERS AND SETTERS

    public Vector<City> getCities()
    {
        return Cities;
    }

    public int[][] getConnectionsTab()
    {
        return connectionsTab;
    }
}
