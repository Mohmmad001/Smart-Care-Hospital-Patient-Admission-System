package Final;

public class NonJordanianInfo extends ResidenceDecorator {

    private String residenceNo;
    private String nationality;

    public NonJordanianInfo(ResidenceInfo residence, String residenceNo, String nationality){
        super(residence);
        this.residenceNo = residenceNo;
        this.nationality = nationality;
    }

    @Override
    public String getInfo(){
        return super.getInfo()
                + "\nNationality: " + nationality
                + "\nResidence No: " + residenceNo;
    }
}
