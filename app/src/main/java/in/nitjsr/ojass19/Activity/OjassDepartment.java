package in.nitjsr.ojass19.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Adapters.DepartmentAdapter;
import in.nitjsr.ojass19.Adapters.MemberAdapter;
import in.nitjsr.ojass19.Modals.Department;
import in.nitjsr.ojass19.Modals.Member;
import in.nitjsr.ojass19.R;

public class OjassDepartment extends AppCompatActivity {

    public static ViewPager mViewPager;
    static List<Department> departmentList;
    RecyclerView recyclerView;
    public static GridLayoutManager layoutManager;
    static  int pos=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ojass_department);



        //for list of departments
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(0);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setHasFixedSize(true);
        departmentList = new ArrayList<>();

    //adding departments to the list
    departmentList.add(new Department("Administration"));
    departmentList.add(new Department("Core"));
    departmentList.add(new Department("Planning & Development"));
    departmentList.add(new Department("Public Relation"));
    departmentList.add(new Department("Digital PR"));
    departmentList.add(new Department("Corporate Affairs"));
    departmentList.add(new Department("Event Management"));
    departmentList.add(new Department("Hospitality"));
    departmentList.add(new Department("Logistics"));
    departmentList.add(new Department("App Team"));
    departmentList.add(new Department("Web Team"));
    departmentList.add(new Department("Team Coordinator"));
    departmentList.add(new Department("Media Relation"));
    departmentList.add(new Department("Creative Team"));
    departmentList.add(new Department("Robotics"));
    departmentList.add(new Department("Decorations"));


        DepartmentAdapter departmentAdapter = new DepartmentAdapter(this, departmentList);
        recyclerView.setAdapter(departmentAdapter);

        // adapter that will return a fragment for each of the 16
        ViewPagerFragmentAdapter mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                layoutManager.scrollToPositionWithOffset(position-1, 1);
                /*Toast.makeText(OjassDepartment.this,
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();*/
            }
            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public static class ViewPagerFragment extends Fragment {

        List<Member> memberList;
        RecyclerView gridRecyclerView;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public ViewPagerFragment() {
        }

        public static ViewPagerFragment newInstance(int sectionNumber) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_member, container, false);

            //for grid view
            gridRecyclerView = (RecyclerView) rootView.findViewById(R.id.grid_recycler_view);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            gridRecyclerView.setLayoutManager(gridLayoutManager);
            gridRecyclerView.setHasFixedSize(true);
            //OjassDepartment.layoutManager.scrollToPositionWithOffset(getArguments().getInt(ARG_SECTION_NUMBER)+1 , 1);

            memberList = new ArrayList<>();

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 0: //Administration
                    memberList.add(new Member("Prof. K. K. Shukla", "Director", "06572373375", "https://api.whatsapp.com/send?phone=06522373375","director@nitjsr.ac.in" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fdirector.jpg?alt=media&token=c67455de-1ee2-4184-9e51-9cab623841e3"));
                    memberList.add(new Member("Prof. Sanjay", "Student's welfare Dean", "9430738551", "https://api.whatsapp.com/send?phone=+919430738551","sanjay.me@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsanjay.jpeg?alt=media&token=a308800a-5906-441f-b7eb-2a7ecb6a48b5"));
                    memberList.add(new Member("Dilip Kumar", "P I Incharge", "9431330646", "https://api.whatsapp.com/send?phone=+919431330646","dilip.ece@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fdileep.jpg?alt=media&token=da3e3fd5-9368-40c6-844b-471704897a9a"));
                    memberList.add(new Member("Vishesh Ranjan Kar", "CO P I Incharge", "9430738551", "https://api.whatsapp.com/send?phone=+919430738551","vishesh.me@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fvishes.jpeg?alt=media&token=400e6e02-8f05-43b6-9c0e-22d9a14abacc"));
                    memberList.add(new Member("Vinit Kumar", "PRESIDENT", "9128086712", "https://api.whatsapp.com/send?phone=+919128086712","PRESIDENT.sc@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fpresident.jpeg?alt=media&token=2068b90f-5d99-419c-9d3f-7897805b09c6"));
                    memberList.add(new Member("Atul Sagar", "TECHNICAL SECRETARY", "7992473534", "https://api.whatsapp.com/send?phone=+919661995903", "techsec.sc@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fatul%20sagar-min.jpg?alt=media&token=df1758a6-3fdc-493f-848b-3509e849214d"));
                    memberList.add(new Member("Nivedita Mishra", "JOINT TECHNICAL SECRETARY", "8299606561", "https://api.whatsapp.com/send?phone=+918299606561", "jtechsec.sc@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fnivedita-min.jpg?alt=media&token=83387bbb-879a-4661-bfe0-c23a53691b58"));

                    break;

                case 1: //Core
                    memberList.add(new Member("Atul Sagar", "TECHNICAL SECRETARY", "7992473534", "https://api.whatsapp.com/send?phone=+919661995903", "atulfreakinsagar@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fatul%20sagar-min.jpg?alt=media&token=df1758a6-3fdc-493f-848b-3509e849214d"));
                    memberList.add(new Member("Nivedita Mishra", "JOINT TECHNICAL SECRETARY", "8299606561", "https://api.whatsapp.com/send?phone=+918299606561", "jtechsec.sc@nitjsr.ac.in", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fnivedita-min.jpg?alt=media&token=83387bbb-879a-4661-bfe0-c23a53691b58"));
                    memberList.add(new Member("Malle Sainikhil", "JOINT SECRETARY", "7209973992", "https://api.whatsapp.com/send?phone=+917416346973", "sainikhilmalle@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FNikhil-min.jpg?alt=media&token=81cc2cf0-f271-4c75-95a1-c49c0a8742bd"));
                    memberList.add(new Member("Anmol Singh", "EXTERNAL GENERAL SECRETARY", "9523915806", "https://api.whatsapp.com/send?phone=+919523915806", "Singh.anmol48@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fanmol%2022-min.jpg?alt=media&token=4f528b81-0656-4e5f-83cd-504cf8e958dd"));
                    memberList.add(new Member("Mudit Tayal", "INTERNAL GENERAL SECRETARY", "9304255652", "https://api.whatsapp.com/send?phone=+917838193234", "mudittayal21@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fmudit-min.jpg?alt=media&token=7ad588e2-b55f-4df7-8767-e96cfd394372"));
                    memberList.add(new Member("Ritu Raj", "TREASURER", "9006299179", "https://api.whatsapp.com/send?phone=+919006299179", "razzritu4@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FRitu-min.jpg?alt=media&token=65badbe1-631b-43e4-a311-759e67955085"));
                    memberList.add(new Member("Gautam Kumar", "EXECUTIVE SECRETARY", "7909057090", "https://api.whatsapp.com/send?phone=+919693190466", "gautamsingh722@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fgautam.jpg?alt=media&token=141b5efa-b837-4174-a2f3-895ab1ef8b6b"));
                    memberList.add(new Member("Gandhe Arun sai ", "EXECUTIVE SECRETARY", "9494996691", "https://api.whatsapp.com/send?phone=+919494996691", "gandhearunsai7@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Farun%20sai-min.jpg?alt=media&token=bb8c4ff7-7740-475f-b8c7-e336ac835657"));
                    memberList.add(new Member("Katravath Raja Naik", "SPOKESPERSON", "7337472523", "https://api.whatsapp.com/send?phone=+917337472523", "KATRAVATH.RAJA@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FKATRAVATH_RAJA_NAIK.jpg?alt=media&token=29e58b2d-bc03-4393-94d7-b418d5b6d38e"));
                    break;

                case 2: //PnD
                    memberList.add(new Member("Ankit Anand Singh", "PLANNING AND DEVELOPMENT", "7978900026", "https://api.whatsapp.com/send?phone=+918102796460", "ankitchampcr7@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAnkit_Anand_Singh.jpg?alt=media&token=1d270e5c-ce3d-41e9-86e1-a363e2febe4f"));
                    memberList.add(new Member("Gopal Agrawal", "PLANNING AND DEVELOPMENT", "9955268864", "https://api.whatsapp.com/send?phone=+919955268864", "gopal.aug29@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fgopal-1-min.jpg?alt=media&token=efd1e772-e766-4a32-9d30-3c123fd46529"));
                    memberList.add(new Member("Shubham Shaswat", "PLANNING AND DEVELOPMENT", "7004734057", "https://api.whatsapp.com/send?phone=+918298605040", "mailsshaswat@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fshaswat2-min.jpg?alt=media&token=4accc4b8-18f8-43fc-96ba-aca748b37436"));
                    memberList.add(new Member("Vinit Vaibhav", "PLANNING AND DEVELOPMENT", "9135947185", "https://api.whatsapp.com/send?phone=+919135947185", "vinitvaibhav9@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fvinit%202-min.jpg?alt=media&token=9b24d45c-2b80-4650-8420-7f9a84e49654"));
                    break;


                case 3: //Public Relation
                    memberList.add(new Member("AKASH GUPTA", "PUBLIC RELATIONS", "7488650379", "https://api.whatsapp.com/send?phone=+919534034604", "akashgupta2228@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fakash%20PR-min.jpg?alt=media&token=70cde5fb-e092-4936-97b7-1755dde433e9"));
                    memberList.add(new Member("G. Anil Naik", "PUBLIC RELATIONS", "9490798528", "https://api.whatsapp.com/send?phone=+919490798528", "2015ugec077@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAnil-min.jpg?alt=media&token=97c18ccd-139f-46e3-91bd-aa090b44e6cf"));
                    memberList.add(new Member("Harshita Agarwal", "PUBLIC RELATIONS", "9939220540", "https://api.whatsapp.com/send?phone=+919939220540", "harshitaagarwaljsr@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FHarshita-min.jpg?alt=media&token=dc84d84b-e449-4b7e-87f7-f2f8c9cc6037"));
                    memberList.add(new Member("Kumar Ritu Raj Singh", "PUBLIC RELATIONS", "7294860904", "https://api.whatsapp.com/send?phone=+917294860904", "skritu13@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Frituraj%20singh-min.jpg?alt=media&token=ee3f0846-a884-4497-9849-a5211c06c5c7"));
                    memberList.add(new Member("Rupav Tiwari", "PUBLIC RELATIONS", "7903617735", "https://api.whatsapp.com/send?phone=+918797956170", "tiwarirupav@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Frupav-min.jpg?alt=media&token=d2b6da19-114a-4245-a7b3-3b31eaca0c62"));
                    break;

                case 4: //Digital PR
                    memberList.add(new Member("Akash Kumar Singh ", "DIGITAL PR", "7319706486", "https://api.whatsapp.com/send?phone=+917319706486", "akashsingh700@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fakash-min.jpg?alt=media&token=1dbffd40-0e27-4ae7-b233-0f20aef4f3a6"));
                    memberList.add(new Member("Sonal Mishra", "DIGITAL PR", "7978556017", "https://api.whatsapp.com/send?phone=+917978556017", "snlmishra408@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsonal-min.jpg?alt=media&token=6044650b-c43e-47c7-be89-f0d00a13b468"));
                    break;

                case 5: //Corporate
                    memberList.add(new Member("Adya Tewary", "CORPORATE AFFAIRS", "6204659068", "https://api.whatsapp.com/send?phone=+918084655144", "adyatewary96@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fadya-min.jpg?alt=media&token=8f1609e4-0695-4bcc-bc7d-ef8b06abcbf1"));
                    memberList.add(new Member("Ashutosh Anshu", "CORPORATE AFFAIRS", "8210156955", "https://api.whatsapp.com/send?phone=+918987717520", "ashutosh3194@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fashutosh-min.jpg?alt=media&token=a21b8f18-7a7c-41d6-aefa-9119a491e551"));
                    memberList.add(new Member("Shalini Srivastava", "CORPORATE AFFAIRS", "7260944610", "https://api.whatsapp.com/send?phone=+917260944610", "shalini.vastava@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FShalini_Srivastava.jpg?alt=media&token=f4f90a1b-9c39-4a3c-9d63-b17f8bc7c475"));
                    memberList.add(new Member("Vishal Kumar Jha", "CORPORATE AFFAIRS", "8271719884", "https://api.whatsapp.com/send?phone=+918271719884", "vishaljha001@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fvishal%20jha-min.jpg?alt=media&token=b02e9f27-8505-4623-b99a-4982d9df0fd5"));
                    break;

                case 6: //event management
                    memberList.add(new Member("Anup Chaki", "EVENT MANAGEMENT", "7903775385", "https://api.whatsapp.com/send?phone=+919613370060", "chakianup1997@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fanup%20chaki-min.jpg?alt=media&token=adc5d585-be3d-48ff-9c89-a0cbbc0be49f"));
                    memberList.add(new Member("Dileep Gunnam", "EVENT MANAGEMENT", "6281503454", "https://api.whatsapp.com/send?phone=+919493254214", "dileep.gunnam999@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fgunnam%20dileep-min.jpg?alt=media&token=0f08cd06-ec83-4b31-b7b8-1cf8c74ff7fe"));
                    memberList.add(new Member("J H M PAVAN KUMAR", "EVENT MANAGEMENT", "7981884433", "https://api.whatsapp.com/send?phone=+919030568293", "pavanpk2210@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FJHM_PAVAN_KUMAR.jpg?alt=media&token=600cef69-ddd9-4157-84d3-2b796dc07d35"));
                    memberList.add(new Member("Pasupuleti Sai Siddhartha", "EVENT MANAGEMENT", "7004098152", "https://api.whatsapp.com/send?phone=+917004098152", "pssiddhardha@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FSiddharth-min.jpg?alt=media&token=82908eac-71c1-47ee-87d4-0c977d29f78b"));
                    memberList.add(new Member("Satyam Prabhat", "EVENT MANAGEMENT", "7004067943", "https://api.whatsapp.com/send?phone=+918578078236", "Satyam.prabhat621@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsatyam-min.jpg?alt=media&token=75f11827-7939-49cf-8fab-4fa5bc1e9fdc"));

                    break;

                case 7: //Hospitality
                    memberList.add(new Member("Jay Anand", "HOSPITALITY", "7545069093", "https://api.whatsapp.com/send?phone=+917545069093", "jayanand2015@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FJAY_ANAND.jpg?alt=media&token=b14b586d-ec59-46d5-a91c-dc315367fe0a"));
                    memberList.add(new Member("Ravindra Kumar", "HOSPITALITY", "7903689652", "https://api.whatsapp.com/send?phone=+919801320899", "rk2ravindra@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fravindra-min.jpg?alt=media&token=34b9cc8b-042b-43c1-934e-9e9ad8acc277"));
                    memberList.add(new Member("Siddhant Kandulna", "HOSPITALITY", "7762803375", "https://api.whatsapp.com/send?phone=+917762803375", "2015ugce035@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FSiddhanth-min.jpg?alt=media&token=ebe63bae-0322-4b1c-8a99-abf083521635"));
                    memberList.add(new Member("Siney", "HOSPITALITY", "9113102404", "https://api.whatsapp.com/send?phone=+918235378454", "siney.303@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsiney-min.jpg?alt=media&token=60ccab43-7f5d-4b8f-85b9-603914ce90e1"));
                    memberList.add(new Member("Tadem Karthik", "HOSPITALITY", "9431342954", "https://api.whatsapp.com/send?phone=+919431342954", "karthiktadem34@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Ftadem%20karthik-min.jpg?alt=media&token=9ea91836-2397-4ef3-9a24-a9013fe5dfb8"));
                    break;

                case 8: //Logistics
                    memberList.add(new Member("Boda Hari Chandra Prasad", "LOGISTICS", "9182672576", "https://api.whatsapp.com/send?phone=+918434230248", "harichandraprasadnit@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FBoda_Hari_Chandra_Prasad%20(2).jpg?alt=media&token=4ab5e1f7-be61-4d49-aa6b-4250daa0d523"));
                    memberList.add(new Member("Boddu Viswa Rama Harish", "LOGISTICS", "8074478743", "https://api.whatsapp.com/send?phone=+918074478743","bodduviswaramaharish@gmail.com" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FHarish-min.jpg?alt=media&token=5a5230bd-e369-4ed0-b762-859ec6a60523"));
                    memberList.add(new Member("K S K P Chandu", "LOGISTICS", "9542806119", "https://api.whatsapp.com/send?phone=+919542806119", "kskpchandu1997@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Funnamed%206-min.jpg?alt=media&token=c3cbee1a-b212-4297-8d47-7f2a79d17823"));
                    memberList.add(new Member("N. Sai Mani Rohith Reddy", "LOGISTICS", "8620926663 " , " https://api.whatsapp.com/send?phone=+918520926663", "saimanirohith111@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FRohith%20Reddy-min.jpg?alt=media&token=60a8ff97-b7f2-497c-a5be-7a3cd217b1ce"));
                    break;

                case 9: //App Team
                    memberList.add(new Member("Ravi Prakash", "APP", "7004635855", "https://api.whatsapp.com/send?phone=+919771179116","raviprakash9598@gmail.com" ,"https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fravi-min.jpg?alt=media&token=3c9e67ba-6c25-45c9-b747-f697701b4f61"));
                    break;

                case 10: //web team
                    memberList.add(new Member("Aamir Hussain", "WEB", "7004295171", "https://api.whatsapp.com/send?phone=+918797956170", "aamir1997hussain@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAamir-min.jpg?alt=media&token=e242d1d0-8605-4e6c-ac2e-aa855665b08e"));
                    memberList.add(new Member("Roshan Kumar", "WEB", "7004328730", "https://api.whatsapp.com/send?phone=+917004328730", "roshankrtiwary@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FRoshan_Kumar.jpg?alt=media&token=c38b2993-a331-4fdd-b117-8c9e2dbe6fab"));
                    memberList.add(new Member("Satyam Raj", "WEB", "7991184034", "https://api.whatsapp.com/send?phone=+918235639917", "satyammast@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsatyam%20ece-min.jpg?alt=media&token=fb939550-17c9-4a8b-8430-d305899366c0"));
                    break;

                case 11: //Team Coordinator
                    memberList.add(new Member("Sravan Kumar Surla", "COORDINATOR", "8919432739", "https://api.whatsapp.com/send?phone=+918500870735", "sravan.kumar.sk.512@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Funnamed%204-min.jpg?alt=media&token=145082b3-d603-4494-8c28-5190b7151ff0"));
                    break;

                case 12: //Media Relations
                    memberList.add(new Member("Rashi", "SOCIAL MEDIAL AND RELATIONS", "7667405612", "https://api.whatsapp.com/send?phone=+919473003159", "rashiverma.2908@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FRashi-min.jpg?alt=media&token=07f3811a-b294-43b6-91ae-9ce9d0d229f8"));
                    memberList.add(new Member("Sanskriti", "SOCIAL MEDIAL AND RELATIONS", "8210474992", "https://api.whatsapp.com/send?phone=+918271356696", "mishrasanskriti.sm@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fsanskriti-min.jpg?alt=media&token=a4dafe5e-718d-4f4e-aed3-bde101ecbeaf"));
                    break;

                case 13: //Creative Team
                    memberList.add(new Member("Abhijeet Gorai", "CREATIVE", "9113760151", "https://api.whatsapp.com/send?phone=+919097830275", "abhijeet.gorai1@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAbhijit-min.jpg?alt=media&token=db71431c-b1e5-415b-8705-311e5a13c9ca"));
                    memberList.add(new Member("Prasoon Kumar Parihar", "CREATIVE", "7903664132", "https://api.whatsapp.com/send?phone=+917280934249", "prasoonparihar97@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Fprasoon-min.jpg?alt=media&token=585628ca-1534-4e5c-9c87-a3c10d05e0d0"));
                    memberList.add(new Member("Pratik Sinha", "CREATIVE", "7004088259", "https://api.whatsapp.com/send?phone=+919661946944", "sank.sinha@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FPratik-min.jpg?alt=media&token=d96041c4-702e-466f-a01b-adc36bb95831"));
                    break;

                case 14: //Robotics
                    memberList.add(new Member("Abhishek Kumar", "ROBOTICS", "9162155052", "https://api.whatsapp.com/send?phone=+919162155052", "ab371295@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAbhishek-min.jpg?alt=media&token=067c0e20-d147-4124-baf5-8def2a30af9f"));
                    memberList.add(new Member("Ampolu Sivaramakrishna", "ROBOTICS", "8500821495", "https://api.whatsapp.com/send?phone=+919949843675","siva.ampolu1208@gmail.com" , "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FAMPOLU%20SIVARAMAKRISHNA.jpg?alt=media&token=de13820b-69c5-412a-963d-de299bb07c22"));
                    memberList.add(new Member("Himanshu Kumar", "ROBOTICS", "9570606083", "https://api.whatsapp.com/send?phone=+919570606083", "himanshuraja1283@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FHimanshu-min.jpg?alt=media&token=073b55d8-cb2c-47d3-acd5-3324212aaff6"));
                    memberList.add(new Member("Kumar Akkireddy", "ROBOTICS", "7004053411", "https://api.whatsapp.com/send?phone=+917032045544", "292kumar@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2Funnamed%205-min.jpg?alt=media&token=d1d0cf0c-0fd1-43ca-99bf-f33dcfdd0d5b"));
                    memberList.add(new Member("Sumit Kumar", "ROBOTICS", "7004851228", "https://api.whatsapp.com/send?phone=+917079260923", "Sumitkumar060198@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FSumit-min.jpg?alt=media&token=5fb29a63-ee7a-4410-a97a-6d5d311c038c"));
                    break;

                case 15: //Decorations
                    memberList.add(new Member("Suraj Das K", "DECORATION", "8500959520", "https://api.whatsapp.com/send?phone=+918500959520", "pssiddhardha@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FSuraj_Das_K.jpg?alt=media&token=401497c8-5bcd-4b0b-8051-90173088a421"));
                    memberList.add(new Member("S V S Gowtham Reddy", "DECORATION", "9494092743", "https://api.whatsapp.com/send?phone=+919494092743", "gowthamsvsr@gmail.com", "https://firebasestorage.googleapis.com/v0/b/ojass-19.appspot.com/o/coreTeamOfficialPhotoShootImages%2FSVS_GOWTHAM_REDDY.jpg?alt=media&token=c56bb30c-97f6-479a-81e6-dacd24dfc504"));

                    break;
            }

            MemberAdapter myAdapter = new MemberAdapter(getActivity() , memberList);
            gridRecyclerView.setAdapter(myAdapter);

            return rootView;
        }

    }

    public  class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

        public ViewPagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return ViewPagerFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // 16 total pages.
            return 16;
        }
    }

    //for navigating back
    @Override
    public void onBackPressed() {
        finish();
    }



}
