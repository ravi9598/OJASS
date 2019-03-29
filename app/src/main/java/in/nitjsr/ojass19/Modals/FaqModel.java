package in.nitjsr.ojass19.Modals;

/**
 * Created by Aditya on 08-02-2017.
 */

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

    public String getQuestion() {
        return ques;
    }
}
