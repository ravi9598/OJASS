package in.nitjsr.ojass19.Modals;

//this is the model class for team members in a particular department of ojass team
public class Member {

    public Member() {
    }

    private String memberName;
    private String memberDesignation;
    private String memberPhone;
    private String memberWhatsapp;
    private String memberMail;
    private String memberImage;

    public Member(String memberName, String memberDesignation, String memberPhone, String memberWhatsapp, String memberMail , String memberImage) {
        this.memberName = memberName;
        this.memberDesignation = memberDesignation;
        this.memberPhone = memberPhone;
        this.memberWhatsapp = memberWhatsapp;
        this.memberMail = memberMail;
        this.memberImage = memberImage;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberMail() {
        return memberMail;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberDesignation() {
        return memberDesignation;
    }

    public void setMemberDesignation(String memberDesignation) {
        this.memberDesignation = memberDesignation;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberWhatsapp() {
        return memberWhatsapp;
    }

    public void setMemberWhatsapp(String memberWhatsapp) {
        this.memberWhatsapp = memberWhatsapp;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }
}