package in.nitjsr.ojass19.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Adapters.DeveloperAdapter;
import in.nitjsr.ojass19.Modals.developer;
import in.nitjsr.ojass19.R;

public class DeveloperView extends AppCompatActivity {

    List<developer> developerList;
    private RecyclerView recyclerView;
    private DeveloperAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);

        recyclerView = findViewById(R.id.dev_recycler);
        developerList = new ArrayList<>();

        findViewById(R.id.ib_back_developers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new DeveloperAdapter(this , developerList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        getDeveloperData();

    }
    public  void getDeveloperData()
    {
        developerList.add(new developer("Ravi Prakash" ,"2015ugec012@nitjsr.ac.in" , "Electronics and Communication Engineering" , "B.Tech 2015-2019" , "https://www.linkedin.com/in/ravi-prakash-654b0a112" , "https://www.facebook.com/ravip9598" ,"https://api.whatsapp.com/send?phone=+919771179116" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fravi-min.jpg?alt=media&token=d673db01-9546-42e9-a82f-9d3090c58c4f")) ;
        developerList.add(new developer("Sahil Kaushik" ,"nitjsr.sk@gmail.com" , "Master of Computer Applications" , "MCA 2018-2021" , "https://www.linkedin.com/in/sahil-kaushik-700332171" , "https://www.facebook.com/sahil.kaushik2" ,"https://api.whatsapp.com/send?phone=+919599058311" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fsahil.jpg?alt=media&token=885247a6-d07b-4029-8814-aec5022032a6")) ;
        developerList.add(new developer("Shipra Jain" ,"shiprajain230@gmail.com" , "Master of Computer Applications" , "MCA 2018-2021" , "https://www.linkedin.com/in/shipra-jain-914a43169" , "https://www.facebook.com/shipra97" ,"https://api.whatsapp.com/send?phone=+919871875139" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fshipra.jpg?alt=media&token=36cd06fe-464c-4d87-a2de-e9d0c3ff9c57")) ;
        developerList.add(new developer("Aditya Prakash" ,"prakash.aditya@programmer.net" , "Electronics and Communication Engineering" , "B.Tech 2016-2020" , "https://www.linkedin.com/in/prakash-aditya/" , "https://www.facebook.com/aditya.prakash.71216" ,"https://api.whatsapp.com/send?phone=+919973414858" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Faditya.jpg?alt=media&token=590ac4f3-185e-4cb1-a16a-5b19e515225b")) ;
        developerList.add(new developer("Aman Srivastava" ,"amankumarsrivastava01@gmail.com" , "Computer Science Engineering" , "B.Tech 2016-2020" , "https://www.linkedin.com/in/aman-srivastava-27152113a" , "https://www.facebook.com/echt.srivastava" ,"https://api.whatsapp.com/send?phone=+919097319928" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2FAman%20Srivastava.jpg?alt=media&token=7668abc3-9482-4bef-9ee7-1077bddc09a0")) ;
        developerList.add(new developer("Gaurav Sethi" ,"Gaurav.sethi32@gmail.com" , "Computer Science Engineering" , "B.Tech 2016-2020" , "https://www.linkedin.com/in/gaurav-sethi-176a80138/" , "https://www.facebook.com/gaurav.sethi.102" ,"https://api.whatsapp.com/send?phone=+917351530721" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fgauravsethi.jpg?alt=media&token=524e5c2e-d5e8-41ab-916e-14c4e7de66c1")) ;
        developerList.add(new developer("Kumar Naman" ,"naman7kr@gmail.com" , "Computer Science Engineering" , "B.Tech 2016-2020" , "https://www.linkedin.com/in/kumar-naman-55b356128" , "https://www.facebook.com/kumar.naman.707" ,"https://api.whatsapp.com/send?phone=+918102226243" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fnaman.jpg?alt=media&token=134705cb-c665-434d-b674-ca67a5dc165d")) ;
        developerList.add(new developer("Sanay" ,"sanay.nitjsr@gmail.com" , "Computer Science Engineering" , "B.Tech 2016-2020" , "https://www.linkedin.com/in/sanay-dev-817aa8147/" , "https://www.facebook.com/Fake.aarav" ,"https://api.whatsapp.com/send?phone=+918986739905" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fsanay.jpg?alt=media&token=322fd420-5bb0-4256-9eb0-4521a630dc9a")) ;
        developerList.add(new developer("Ayush Dubey" ,"ayushdubey957@gmail.com" , "Computer Science Engineering" , "B.Tech 2017-2021" , "https://www.linkedin.com/in/ayush-dubey-b04a86149" , "https://www.facebook.com/profile.php?id=100018089040154" ,"https://api.whatsapp.com/send?phone=+919140735086" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fayushdubey.jpg?alt=media&token=96a3b1be-1f07-4ea9-ab8e-fcb3c5672a88")) ;
        developerList.add(new developer("Prince" ,"prince11rajput@gmail.com" , "Computer Science Engineering" , "B.Tech 2017-2021" , "https://www.linkedin.com/in/prince-kumar-032785147" , "https://m.facebook.com/singh.saheb11" ,"https://api.whatsapp.com/send?phone=+918107048217" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fprincekumar.jpg?alt=media&token=38888d57-90ae-470f-90cb-eb597f0ddd7b")) ;
        developerList.add(new developer("Purusharth Verma" ,"purusharthugra@gmail.com" , "Computer Science Engineering" , "B.Tech 2017-2021" , "https://www.linkedin.com/in/purusharth-verma-65b70a17a" , "https://m.facebook.com/purusharth.verma.92" ,"https://api.whatsapp.com/send?phone=+917651883057" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fpurusharth.jpg?alt=media&token=d3038a90-0154-4017-8847-914bb02b2fba")) ;
        developerList.add(new developer("Ritik Raj" ,"rajlevel97@gmail.com" , "Computer Science Engineering" , "B.Tech 2017-2021" , "https://www.linkedin.com/in/ritik-raj-67870817a/" , "https://www.facebook.com/profile.php?id=100012986703892" ,"https://api.whatsapp.com/send?phone=+917366986560" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/DeveloperImages%2Fritik.jpg?alt=media&token=9baa4eec-fc1a-4e08-89bf-9fe9e30e261e")) ;
    }
}
