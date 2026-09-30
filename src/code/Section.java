package Final;

import java.util.ArrayList;

public class Section {

    private int id;
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Section(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getId() {
    	return id;
    }

    public Room findAvailableRoom(){
        for(Room r : rooms){
            if(r.hasSpace()){
                return r;
            }
        }
        return null;
    }
}
