package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void constructorUser() {
        User user = new User(2, "email", "username", "password");
        assertNotNull(user);
    }

    @Test
    public void getSetAttributes() {
        User user = new User(2, "email", "username", "password");
        Role role = new Role(1, "admin");
        user.setEmail("notEmail");
        user.setUser_id(5);
        user.setUser_password("notPassword");
        user.setUser_name("notUsername");
        user.setRole(role);
        assertEquals("notEmail", user.getEmail());
        assertEquals("notPassword", user.getUser_password());
        assertEquals(5, user.getUser_id());
        assertEquals("notUsername", user.getUser_name());
        assertEquals(role, user.getRole());
    }

    @Test
    public void getSetRoomReservations() {
        User user = new User(2, "email", "username", "password");
        List<RoomReservation> roomReservList = new ArrayList<RoomReservation>();
        user.setRoomReservations(roomReservList);
        assertEquals(roomReservList, user.getRoomReservations());
    }

    @Test
    public void addRemoveRoomReservations() {
        User user = new User(2, "email", "username", "password");
        List<RoomReservation> roomReservList = new ArrayList<RoomReservation>();
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        user.setRoomReservations(roomReservList);
        user.addRoomReservation(rs);
        assertEquals(rs, user.getRoomReservations().get(0));
        user.removeRoomReservation(rs);
        assertEquals(0, user.getRoomReservations().size());
    }

    @Test
    public void toStringTest() {
        User user = new User(2, "email", "username", "password");
        Role role = new Role(1, "admin");
        user.setRole(role);
        String toString = "user_id: " + user.getUser_id() + " , email: " + user.getEmail()
                + " , user_name: " + user.getUser_name() + " , user_password: " + user.getUser_password() + " , role_fk: " + user.getRole().getRole_id();
        System.out.println(toString);
        System.out.println(user.toString());
        assertEquals(toString, user.toString());
    }

}