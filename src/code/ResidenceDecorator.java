package Final;

public abstract class ResidenceDecorator implements ResidenceInfo {

    protected ResidenceInfo residence;

    public ResidenceDecorator(ResidenceInfo residence){
        this.residence = residence;
    }

    public String getInfo(){
        return residence.getInfo();
    }
}
