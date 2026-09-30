package Final;

public class MedicalProcedure {

    private int id;
    private String name;
    private boolean done;

    public MedicalProcedure(int id, String name){
        this.id = id;
        this.name = name;
        this.done = false;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public boolean isDone(){
        return done;
    }

    public void markDone(){
        done = true;
    }
}