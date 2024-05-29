package trains;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipDiningCarTest {
    @Test
    void getIdReturnsTheTrainsId(){
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        int expected = mixedTrain.getId();

        assertEquals(expected, equipDiningCar.getId());
    }

    @Test
    void getModelReturnsTrainsModelWithDiningCar(){
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        String expected = mixedTrain.getModel() + " with dining car";

        assertEquals(expected, equipDiningCar.getModel());
    }

    @Test
    void getAverageSpeedReturnsTrainsSpeedMinusTwenty() throws NotValidSpeedException {
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        int expected = mixedTrain.GetAverageSpeed() - 20;

        assertEquals(expected, equipDiningCar.GetAverageSpeed());
    }

    @Test
    void getAverageSpeedThrowsExceptionWhenTrainSpeedIsTooLow() {
        List<TrainBase> trains = TrainDatabaseManager.getAllTrains();
        ShuttleTrain shuttleTrain = (ShuttleTrain) trains.get(0);
        EquipDiningCar equipDiningCar = new EquipDiningCar(shuttleTrain);

        assertThrows(NotValidSpeedException.class,
                () -> {
                    equipDiningCar.GetAverageSpeed();
                });
    }

    @Test
    void getSafetyLevelReturnsTrainsSafetyLevelMinusOne(){
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        int expected = mixedTrain.GetSafetyLevel() - 1;

        assertEquals(expected, equipDiningCar.GetSafetyLevel());
    }

    @Test
    void getNumberOfWagonsReturnsTrainsNumberOfWagonsPlusOne(){
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        int expected = mixedTrain.GetNumberOfWagons() + 1;

        assertEquals(expected, equipDiningCar.GetNumberOfWagons());
    }

    @Test
    void saveToDatabaseSavesTheTrainIntoDatabase(){
        boolean result = false;
        MixedTrain mixedTrain = new MixedTrain();
        EquipDiningCar equipDiningCar = new EquipDiningCar(mixedTrain);

        TrainDatabaseManager.saveToDatabase
                (equipDiningCar.getModel(), equipDiningCar.GetAverageSpeed(),
                        equipDiningCar.GetSafetyLevel(), equipDiningCar.GetNumberOfWagons());

        for (TrainBase train : TrainDatabaseManager.getAllTrains()) {
            if (train.getModel().equals(equipDiningCar.getModel())){
                result = true;
            }

        }

        assertTrue(result);
    }
}