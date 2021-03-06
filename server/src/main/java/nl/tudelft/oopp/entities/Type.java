package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import nl.tudelft.oopp.entities.Room;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeId;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "whiteboard")
    private boolean whiteBoard;

    @NotNull
    @Column(name = "tv")
    private boolean tv;

    @NotNull
    @Column(name = "power_outlets")
    private boolean powerOutlets;

    @NotNull
    @Column(name = "clicker")
    private boolean clicker;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Room> listOfRooms = new ArrayList<>();

    public Type() {
    }

    /**.
     * constructor for type class
     * @param name name of the room type
     * @param whiteBoard availability of whiteboard
     * @param powerOutlets availability of poweroutlets
     * @param clicker availability of clicker
     * @Author Eli
     */
    public Type(String name, boolean whiteBoard, boolean powerOutlets, boolean clicker) {
        this.name = name;
        this.whiteBoard = whiteBoard;
        this.powerOutlets = powerOutlets;
        this.clicker = clicker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_id() {
        return typeId;
    }

    public boolean isWhiteBoard() {
        return whiteBoard;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isPowerOutlets() {
        return powerOutlets;
    }

    public boolean isClicker() {
        return clicker;
    }

    public void setWhiteBoard(boolean whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setPowerOutlets(boolean powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public void setClicker(boolean clicker) {
        this.clicker = clicker;
    }


    /*
    Room related methods
     */
    @JsonIgnore
    public List<Room> getListOfRooms() {
        return listOfRooms;
    }

    public void setListOfRooms(List<Room> listOfRooms) {
        this.listOfRooms = listOfRooms;
    }

    public void addRoom(Room room) {
        this.listOfRooms.add(room);
        room.setType(this);
    }

    public void removeRoom(Room room) {
        this.listOfRooms.remove(room);
        room.setType(null);
    }


    public String toString() {
        return "type_id: " + this.typeId + ", name: " + this.name + ", clicker: " + this.clicker + ", tv: " + this.tv
                + ", power_outlets: " + this.powerOutlets + ", whiteboard: " + this.whiteBoard;
    }
}
