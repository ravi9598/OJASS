package in.nitjsr.ojass19.Utils;

//New Updated project

import in.nitjsr.ojass19.R;

public class Constants {
    public static final String NOT_REGISTERED = "Not Registered";
    public static final String PAYMENT_DUE = "Payment Due";

    public static final int EVENT_FLAG = 1;
    public static final int GURU_GYAN_FLAG = 2;
    public static final int SPONSORS_FLAG = 3;

    public static final int RECT_PLACEHOLDER = 0;
    public static final int SQUA_PLACEHOLDER = 1;

    public static final String FIREBASE_REF_USERS = "Users";
    public static final String FIREBASE_REF_EMAIL = "email";
    public static final String FIREBASE_REF_NAME = "name";
    public static final String FIREBASE_REF_PHOTO = "photo";
    public static final String FIREBASE_REF_MOBILE = "mobile";
    public static final String FIREBASE_REF_COLLEGE = "college";
    public static final String FIREBASE_REF_COLLEGE_REG_ID = "regID";
    public static final String FIREBASE_REF_BRANCH = "branch";
    public static final String FIREBASE_REF_TSHIRT_SIZE = "tsirtSize";
    public static final String FIREBASE_REF_OJASS_ID = "ojassID";
    public static final String FIREBASE_REF_TSHIRT = "isTshirt";
    public static final String FIREBASE_REF_KIT = "isKit";
    public static final String FIREBASE_REF_PARTICIPATED_EVENTS = "Events";
    public static final String FIREBASE_REF_PARTICIPATED_EVENT_NAME = "name";
    public static final String FIREBASE_REF_PARTICIPATED_EVENT_RESULT = "result";
    public static final String FIREBASE_REF_PARTICIPATED_EVENT_BRANCH = "branch";
    public static final String FIREBASE_REF_PARTICIPATED_EVENT_TIME = "time";

    public static final String FIREBASE_REF_NOTIFICATIONS = "Notifications";
    public static final String FIREBASE_REF_OJASS_CHANNEL = "OJASS";
    public static final String FIREBASE_REF_NOTIFICATIONS_BODY = "ans";
    public static final String FIREBASE_REF_NOTIFICATIONS_TITLE = "question";

    public static final String FIREBASE_REF_POSTERIMAGES = "PosterImages";
    public static final String FIREBASE_REF_IMG_SRC = "img_url";
    public static final String FIREBASE_REF_IMG_CLICK = "click_url";

    public static final String FIREBASE_REF_GURU_GYAN = "GuruGyan";
    public static final String FIREBASE_REF_GURU_GYAN_IMAGE = "image";
    public static final String FIREBASE_REF_GURU_GYAN_LONG_DESC = "longDesc";
    public static final String FIREBASE_REF_GURU_GYAN_SHORT_DESC = "shortDesc";
    public static final String FIREBASE_REF_GURU_GYAN_SHORT_IMAGE = "shortImage";
    public static final String FIREBASE_REF_GURU_GYAN_TITLE = "title";
    public static final String FIREBASE_REF_GURU_GYAN_DATE = "date";

    public static final String[] ITINARY_IMAGES = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/itinaryDetailsImages%2Fitinary_day1.jpeg?alt=media&token=3266368a-2063-4bcb-9d10-4035787c3fd9",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/itinaryDetailsImages%2Fitinary_day2.jpeg?alt=media&token=9a7879af-60dc-405b-91b9-2d61c5118f51",
            "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/itinaryDetailsImages%2Fitinary_day3.jpeg?alt=media&token=494b8aae-dc91-4374-863e-e44fef0d3f19"
    };

    public static final String[] homeEvent = new String[]{
            "http://www.ojass.in/app/Images/HomeEvents/major_business.png",
            "http://www.ojass.in/app/Images/HomeEvents/major_directorscut.png",
            "http://www.ojass.in/app/Images/HomeEvents/major_codemania.png",
            "http://www.ojass.in/app/Images/HomeEvents/noground.png",
            "http://www.ojass.in/app/Images/HomeEvents/major_robowar.png",
            "http://www.ojass.in/app/Images/HomeEvents/major_dota.jpg"
    };

    public static final String[] smallEventImage = new String[]{
            "http://www.ojass.in/app/Images/EventsNew/machine.png",
            "http://www.ojass.in/app/Images/EventsNew/code.png",
            "http://www.ojass.in/app/Images/EventsNew/circuit.png",
            "http://www.ojass.in/app/Images/EventsNew/siliconvalley.png",
            "http://www.ojass.in/app/Images/EventsNew/arthsastra.png",
            "http://www.ojass.in/app/Images/EventsNew/akiriti.png",
            "http://www.ojass.in/app/Images/EventsNew/duexmachina.png",
            "http://www.ojass.in/app/Images/EventsNew/produs.png",
            "http://www.ojass.in/app/Images/EventsNew/parpanalia.png",
            "http://www.ojass.in/app/Images/EventsNew/neodhristi.png",
            "http://www.ojass.in/app/Images/EventsNew/avtaran.png",
            "http://www.ojass.in/app/Images/EventsNew/armageddor.png",
            "http://www.ojass.in/app/Images/EventsNew/pryas.png",
            "http://www.ojass.in/app/Images/EventsNew/nogroundzone.png",
            "http://www.ojass.in/app/Images/EventsNew/ncet.png",
            "http://www.ojass.in/app/Images/EventsNew/livecs.png",
            "http://www.ojass.in/app/Images/EventsNew/exposition.png",
            "https://firebasestorage.googleapis.com/v0/b/ojass18-1cb0d.appspot.com/o/EventPageImages150x150%2Fschoolsquare.png?alt=media&token=9eb79d67-7dd5-4f9b-9cf6-6f94a6ad039f"
    };

    public static final String[] largeEventImage = new String[]{
            "http://www.ojass.in/app/Images/EventLarge/RiseofMachines.jpg",
            "http://www.ojass.in/app/Images/EventLarge/VishwaCodeGenesis.jpg",
            "http://www.ojass.in/app/Images/EventLarge/Circuit%20House.jpg",
            "http://www.ojass.in/app/Images/EventLarge/Siliconvalley.jpg",
            "http://www.ojass.in/app/Images/EventLarge/Arthashastra.jpg",
            "http://www.ojass.in/app/Images/EventLarge/Aakriti.jpg",
            "http://www.ojass.in/app/Images/EventLarge/DeusXMachina.jpg",
            "http://www.ojass.in/app/Images/EventLarge/produs.jpg",
            "http://www.ojass.in/app/Images/EventLarge/parapharnalia.jpg",
            "http://www.ojass.in/app/Images/EventLarge/NeoDrishti.jpg",
            "http://www.ojass.in/app/Images/EventLarge/avartan.jpg",
            "http://www.ojass.in/app/Images/EventLarge/Armageddon1.jpg",
            "http://www.ojass.in/app/Images/EventLarge/prayas.jpg",
            "http://www.ojass.in/app/Images/EventLarge/NoGroundZone.jpg",
            "http://www.ojass.in/app/Images/EventLarge/NSCET.jpg",
            "http://www.ojass.in/app/Images/EventLarge/LiveCS.jpg",
            "http://www.ojass.in/app/Images/EventLarge/exposition.jpg",
            "https://firebasestorage.googleapis.com/v0/b/ojass18-1cb0d.appspot.com/o/EventPageImages2%3A1%2Fschool.png?alt=media&token=5bbaf5ca-7c0f-415b-83cf-b6043802ad7c"
    };

    public static final String eventNames[] = new String[]{
            "Rise of Machines",
            "Vishwa CodeGenesis",
            "Circuit House",
            "Silicon Valley",
            "ArthaShastra",
            "Aakriti",
            "Deus-X-Machina",
            "Produs",
            "Paraphernalia",
            "NeoDrishti",
            "Avartan",
            "Armageddon",
            "Prayas",
            "No Ground Zone",
            "NSCET",
            "LiveCS",
            "School Events"
    };
    public static final String[][] EventList = new String[][]{
            {"Gurutwa", "iANSYST", "Auto Quiz", "Box-Cipher", "Corporate Bytes","Junkyard Wars","Prakshepan"},
            {"Codemania", "Hack-De-Science", "Code-o-Soccer", "Comp Geeks", "Sudo-Code", "Imitation Game"},
            {"High Voltage Concepts", "Elixir of Electricity", "Electrospection", "Electro Scribble", "MAT-SIM", "Electro-Q", "Pro-Lo-Co"},
            {"Tukvilla", "Jigyasa", "Codesense", "Analog Hunter", "Digizone", "Netkraft", "Embetrix"},
            {"Toddler to Tycoon(TTT)", "Prabandh Yojana", "Let's Start Up", "Bizzathlon", "Wolf of Dalal Street", "Teenpreneur", "Corporate Roadies"},
            {"Acumen", "Sanrachna", "Archmadeease", "Exempler", "Pipe-o-Mania", "Metropolis"},
            {"360 Mania", "Bot-A Maze", "Battleship", "Kurukshetra", "MAC FIFA", "Hurdles Hunter"},
            {"Industrial Tycoon","DronaGyan" ,"Utpreksh", "Crack the Case", "M&I Quiz"},
            {"Director's Cut","Lens View","Mad-Ad","Sci-Fi", "Light, Camera, Ojass!"},
            {"Codiyapa", "Game of Troves", "Code Relay", "Simply Sql", "Tame The Python"},
            {"Spectra", "Agnikund", "Metal Trivia", "Innovision", "Knock out"},
            {"FIFA 18","Counter Strike- Global Offensive", "NFS Most Wanted", "LUDO King", "PUBG"},
            {"Jagriti", "Samvedna", "Pratibimb"},
            {"Touch Down the plane", "MICAV"},
            {"NSCET"},
            {"LiveCS"},
            {"Teenpreneur", "Scifari", "Scrap Star", "The Wiz Craft"}

    };
    public static final int[] eventImageName = new int[]{
            R.mipmap.rise_of_machines,
            R.mipmap.code_genesis,
            R.mipmap.circuit_house,
            R.mipmap.silicon_valley,
            R.mipmap.arthashastra,
            R.mipmap.akriti,
            R.mipmap.dxm,
            R.mipmap.produs,
            R.mipmap.paraphernalia,
            R.mipmap.neo_drishti,
            R.mipmap.avartan,
            R.mipmap.armagedon,
            R.mipmap.prayas,
            R.mipmap.no_ground_zone,
            R.mipmap.nscet,
            R.mipmap.csgolive,
            R.mipmap.school_events
    };
    public static final String[][] branchHeadName = new String[][]{
            {"Manish Mishra", "Pawan Kumar", "Sanath Himasekhar"},
            {"Ritesh Prasad","Rishabh", "Shubham Kumar Singh"},
            {"Adarsh Rajak","Pragya","Pranav Tiwari"},
            {"Balram Vishwakarma", "Harsh Tulsyan", "Sukriti Raj"},
            {"Nikhil Rai", "Rolee Agarwal"},
            {"Drishti Singh","Rishabh Chandra", "Rishabh Ratnam"},
            {"Abhishek", "Shiva Ram Krishna", "Sumit"},
            {"Anurag Priyadarshi", "Komal Kumari", "Vivek Sharma"},
            {"Monu Kumar"},
            {"Mohit Tarini", "Kanika Parnami", "Abhishek Sharma"},
            {"Anuj Gupta", "Ishani Sarkar", "Rahul Raj"},
            {"Atul Kumar", "Saurav Kumar"},
            {"Vikash", "Manoj", "Ayushi"},
            {"Shubham Burnwal"},
            {"Ritu Raj"},
            {"Balwant"},
            {"Ritu Raj"},

    };

    public static final String[][] branchHeadNum = new String[][]{
            {"7004575192", "913593065", "8074473589"},
            {"8936892334", "7004916976", "7004743106"},
            {"9471126529", "7070654097", "8092348666"},
            {"7070613942", "7631184347", "8789522609"},
            {"7828077280", "7004871347"},
            {"7783825879", "9973949841", "8406080962"},
            {"8008458784","9471105567", "9905183679"},
            {"7050544344", "9304018524", "9304359578"},
            {"8797361542"},
            {"9504047586", "9651010322", "9711487046"},
            {"9931167630", "8340674404", "9777988058"},
            {"9470177612", "7992455725"},
            {"8757080513", "8541833888", "9771935189"},
            {"9304022913"},
            {"7980335651", "9963745816"},
            {"9530476929"},
            {"9693944942", "9031649537"}
    };


}
