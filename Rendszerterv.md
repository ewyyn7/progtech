# Menetrendek Oldal

## Cél

A Menetrendek oldal célja, hogy a felhasználók számára lehetőséget biztosítson a vonatok menetrendjének megtekintésére, szerkesztésére és karbantartására. Az oldal lehetővé teszi a vonatok, állomások és vonalak közötti kapcsolatok nyomon követését és kezelését, valamint új menetrendi bejegyzések hozzáadását és meglévők törlését.

## Funkciók

- **Menetrendek megtekintése**: A felhasználók megtekinthetik a vonatok menetrendjét, beleértve az indulási és érkezési állomásokat, vonat azonosítókat, valamint az indulási és érkezési időpontokat.
- **Menetrendi bejegyzések hozzáadása**: Új menetrendi bejegyzések hozzáadása egy külön ablakban, ahol a felhasználók kiválaszthatják a vonalat és a vonatot.
- **Menetrendi bejegyzések törlése**: A felhasználók törölhetik a kiválasztott menetrendi bejegyzéseket a táblázatból.
- **Menetrendi bejegyzések frissítése**: Az adatok módosítása és a menetrend automatikus frissítése a bejegyzések hozzáadása és törlése után.

## Rendszerarchitektúra

A rendszer három fő komponensből áll:
1. **Felhasználói felület (UI)**: JavaFX segítségével készült, amely lehetővé teszi a felhasználók számára az adatok megtekintését és kezelését.
2. **Adatkezelés (DAO réteg)**: Az adatbázis műveletek végrehajtására szolgál, mint például a menetrendi bejegyzések hozzáadása, frissítése, törlése és lekérdezése.
3. **Adatbázis**: MySQL adatbázis tárolja a menetrendi adatokat, beleértve az állomásokat, vonatokat és vonalakat.

## Adatbázis séma

- **stations**: Az állomásokat tárolja.
  - id (INT)
  - name (VARCHAR)

- **trains**: A vonatokat tárolja.
  - id (INT)
  - model (VARCHAR)
  - average_speed (INT)
  - safety_level (INT)
  - number_of_wagons (INT)

- **lines**: A vonalakat tárolja.
  - id (INT)
  - length (INT)
  - base_station_id (INT)
  - final_station_id (INT)

- **schedules**: A menetrendi bejegyzéseket tárolja.
  - id (INT)
  - train_id (INT)
  - line_id (INT)
  - time (INT)

## Felhasznált technológiák

- **JavaFX**: A grafikus felhasználói felület fejlesztéséhez.
- **MySQL**: Az adatbázis tárolásához és kezeléséhez.
- **JDBC**: Az adatbázis kapcsolatok és műveletek végrehajtásához.
- **FXML**: A JavaFX alkalmazások felületének meghatározásához.

## Rendszerfolyamatok

1. **Menetrendek betöltése**: Az alkalmazás indításakor az adatbázisból betölti az összes menetrendi bejegyzést és megjeleníti őket a táblázatban.
2. **Új menetrendi bejegyzés hozzáadása**: A felhasználó az "Add" gombra kattintva megnyit egy új ablakot, ahol kiválaszthatja a vonalat és a vonatot, majd megadhatja a menetidőt. Az új bejegyzést az adatbázisba menti és frissíti a táblázatot.
3. **Menetrendi bejegyzés törlése**: A felhasználó kiválaszt egy menetrendi bejegyzést a táblázatban, majd a "Delete" gombra kattintva törli azt az adatbázisból és frissíti a táblázatot.
4. **Menetrend frissítése**: A felhasználói műveletek után a táblázat automatikusan frissül, hogy tükrözze az adatbázis aktuális állapotát.

## Feladatkövetés

A fejlesztés folyamán a feladatok nyomon követése és kezelése GitHubon történik, ahol egy Git repositoryban tároljuk a kódot és a fejlesztési feladatokat.
