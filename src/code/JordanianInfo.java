package Final;

public class JordanianInfo extends ResidenceDecorator {

    private String nationalNumber;
    private String idCardNumber;

    public JordanianInfo(ResidenceInfo residence, String nationalNumber, String idCardNumber){
        super(residence);
        this.nationalNumber = nationalNumber;
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String getInfo(){
        return super.getInfo()
                + "\nNationality: Jordanian"
                + "\nNational No: " + nationalNumber
                + "\nID Card: " + idCardNumber;
    }
}
