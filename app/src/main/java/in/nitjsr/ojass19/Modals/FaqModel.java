package in.nitjsr.ojass19.Modals;

public class FaqModel {

    String ques,ans;

    public FaqModel(String ques, String ans) {

        this.ques=ques;
        this.ans=ans;
    }
    public FaqModel()
    {

    }

    public String getAns() {
        return ans;
    }

    public String getQues() {
        return ques;
    }
}
