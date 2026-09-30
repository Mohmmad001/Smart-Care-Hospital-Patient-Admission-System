package Final;

public abstract class Actor {

    protected int id;
    protected String name;
    protected String phone;
    protected String password;  

    protected ResidenceInfo residence;   

    public Actor(int id, String name, String phone, String password){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public void setResidence(ResidenceInfo residence){
        this.residence = residence;
    }

    public String getResidenceInfo(){
        return residence.getInfo();
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getPassword(){ return password; }
}