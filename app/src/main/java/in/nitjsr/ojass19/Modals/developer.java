package in.nitjsr.ojass19.Modals;

public class developer {
    public developer() {
    }

    private String developerName;
    private String developerMail;
    private String developerBranch;
    private String developerSession;
    private String developerLinkedn;
    private String developerFacebook;
    private String developerWhatsapp;
    private String developerImage;

    public developer(String developerName , String developerMail , String developerBranch , String developerSession , String developerLinkedn , String developerFacebook , String developerWhatsapp , String developerImage)
    {
        this.developerName = developerName;
        this.developerMail = developerMail;
        this.developerBranch = developerBranch;
        this.developerSession = developerSession;
        this.developerLinkedn = developerLinkedn;
        this.developerFacebook = developerFacebook;
        this.developerWhatsapp = developerWhatsapp;
        this.developerImage = developerImage;
    }

    public String getDeveloperImage() {
        return developerImage;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getDeveloperBranch() {
        return developerBranch;
    }

    public String getDeveloperLinkedn() {
        return developerLinkedn;
    }

    public String getDeveloperFacebook() {
        return developerFacebook;
    }

    public String getDeveloperSession() {
        return developerSession;
    }

    public String getDeveloperMail() {
        return developerMail;
    }

    public String getDeveloperWhatsapp() {
        return developerWhatsapp;
    }
}
