package in.nitjsr.ojass19.Modals;

import java.util.ArrayList;

public class EventModel {

    public String about;
    public String branch;
    public String details;
    public String name;



    public PrizeModel1 p1;
    public PrizeModel2 p2;
    public ArrayList<CoordinatorsModel> coordinator=new ArrayList<>();
    public ArrayList<RulesModel> rules=new ArrayList<>();

    public EventModel(String about, String branch, String details, String name,PrizeModel1 p1,PrizeModel2 p2, ArrayList<CoordinatorsModel> coordinatorsModelArrayList, ArrayList<RulesModel> rulesModels) {
        this.about=about;
        this.name = name;
        this.details=details;
        this.branch=branch;
        this.p1 = p1;
        this.p2=p2;
        this.coordinator=coordinatorsModelArrayList;
        this.rules=rulesModels;
    }
    public EventModel()
    {

    }

    public String getAbout() {
        return about;
    }

    public String getBranch() {
        return branch;
    }

    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }

    public PrizeModel1 getP1() {
        return p1;
    }

    public PrizeModel2 getP2() {
        return p2;
    }

    public ArrayList<CoordinatorsModel> getCoordinatorsModelArrayList() {
        return coordinator;
    }

    public ArrayList<RulesModel> getRulesModels() {
        return rules;
    }

}