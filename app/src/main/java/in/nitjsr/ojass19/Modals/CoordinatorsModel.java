package in.nitjsr.ojass19.Modals;

public class CoordinatorsModel {
    public String name;
    public String phone;
    public CoordinatorsModel(String name, String phone) {
        this.name = name;
        this.phone=phone;
    }
    public CoordinatorsModel()
    {

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }


}