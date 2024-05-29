package stations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.*;



class StationDatabaseManagerTest {

    @Test
    void deleteStation_NotExistId() {
        int id = -1;
        Executable code=()->{
            StationDatabaseManager.deleteStation(id);
        };
        Class<? extends Throwable> expected = ZeroLineException.class;
        assertThrows(expected,code);

    }

    @Test
    void addStation_NewStation(){
        Station station = new Station("teszteles");

        StationDatabaseManager.addStation(station);
        boolean result= false;
        for (Station stat : StationDatabaseManager.getAllStations()
             ) {
            if (stat.getName().equals( station.getName())){
                result=true;
            }

        }
        assertTrue(result);


    }

    @Test
    void updateStation_FirstStation(){

        boolean result= false;
        Station station=new Station(1,"updated");
        StationDatabaseManager.updateStation(station);
        for (Station stat : StationDatabaseManager.getAllStations()
        ) {
            if (stat.getId()==1 && stat.getName().equals( station.getName())){
                result=true;
            }

        }
        assertTrue(result);


    }

    @Test
    void getStation_StationName(){

        boolean result= false;
        Station station=new Station(4,"name");
        StationDatabaseManager.updateStation(station);
        result=StationDatabaseManager.getStation(station.getId()).getName().equals(station.getName());
        assertTrue(result);

    }

    @Test
    void deleteStation_ExistStation(){
        Station station = new Station("teszteles2");

        StationDatabaseManager.addStation(station);
        int deleteid = 0;
        boolean result= false;
        for (Station stat : StationDatabaseManager.getAllStations()
        ) {
            if (stat.getName().equals( station.getName())){
                deleteid=stat.getId();
            }
        }

        StationDatabaseManager.deleteStation(deleteid);
        for (Station stat : StationDatabaseManager.getAllStations()
        ) {
            if (stat.getName().equals( station.getName())){
                result=true;
            }
        }

        assertFalse(result);

    }





}