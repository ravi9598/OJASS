package in.nitjsr.ojass19.Modals;

//model class for ojass team format

public class Department {

    private String departmentName;
    //private String departmentImage;

    public Department(String departmentName/*, String departmentImage*/) {
        this.departmentName = departmentName;
        //this.departmentImage = departmentImage;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(){
    }
}
