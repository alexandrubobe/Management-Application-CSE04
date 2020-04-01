package nl.tudelft.oopp.communication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant r1;
    private Restaurant r2;
    private Menu menu;
    private Building building;

    @BeforeEach
    void setUp() {
        Time time = new Time(6450);
        Time time2 = new Time(2100);
        menu = new Menu(4);
        building = new Building("DW", true, 50, "Nice building", time, time2);

        r1 = new Restaurant(1);
        r2 = new Restaurant();

        r1.setBuilding(building);
        r1.setMenu(menu);
    }

    @Test
    void emptyConstructorTest() {
        assertNotNull(r2);
    }

    @Test
    void constructorTest() {
        assertNotNull(r1);
    }

    @Test
    void getMenu() {
        assertEquals(menu, r1.getMenu());
    }

    @Test
    void getBuilding() {
        assertEquals(building, r1.getBuilding());
    }

    @Test
    void setBuilding() {
        Time time = new Time(1234);
        Time time2 = new Time(4571);
        Building b = new Building("CSE", false, 50, "Nice building", time, time2);
        r1.setBuilding(b);
        assertEquals(b, r1.getBuilding());
    }

    @Test
    void getResId() {
        assertEquals(1, r1.getResId());
    }

    @Test
    void setResId() {
        r1.setResId(10);
        assertEquals(10, r1.getResId());
    }

    @Test
    void setMenu() {
        Menu m2 = new Menu(7654);
        r1.setMenu(m2);
        assertEquals(m2, r1.getMenu());
    }
}