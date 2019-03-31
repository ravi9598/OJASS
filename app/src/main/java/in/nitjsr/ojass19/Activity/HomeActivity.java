package in.nitjsr.ojass19.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.Fragments.EventsFragment;
import in.nitjsr.ojass19.Fragments.HomeFragment;
import in.nitjsr.ojass19.Fragments.ItinaryFragment;
import in.nitjsr.ojass19.Modals.CoordinatorsModel;
import in.nitjsr.ojass19.Modals.EventModel;
import in.nitjsr.ojass19.Modals.PrizeModel1;
import in.nitjsr.ojass19.Modals.PrizeModel2;
import in.nitjsr.ojass19.Modals.RulesModel;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.CustomViewPager;
import in.nitjsr.ojass19.Utils.SharedPrefManager;
import in.nitjsr.ojass19.Utils.Utilities;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_OJASS_ID;
import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_USERS;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private static final String urlOfApp = "https://play.google.com/store/apps/details?id=in.nitjsr.ojass19&hl=en";
    private String currentVersion;
    private BottomNavigationView navigation;
    private FirebaseAuth mAuth;
    private SharedPrefManager sharedPrefManager;
    private SharedPreferences mSharedPrefs;
    private static CustomViewPager viewPager;
    private BottomSheetDialog bottomSheetDialog;
    //variables
    public static List<EventModel> data = new ArrayList<>();
    private DatabaseReference mRef;
    private ProgressDialog pDialog;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!(f instanceof HomeFragment)) {
                        transaction.replace(R.id.frame_container, new HomeFragment()).commit();
                        return true;
                    }
                    break;

                case R.id.navigation_event:
                    if (!(f instanceof EventsFragment)) {
                        transaction.replace(R.id.frame_container, new EventsFragment()).commit();
                        return true;
                    }
                    break;

                case R.id.navigation_map:
                    startActivity(new Intent(HomeActivity.this, MapsActivity.class));
                    break;

                case R.id.navigation_itinerary:
                    if (!(f instanceof ItinaryFragment)) {
                        transaction.replace(R.id.frame_container, new ItinaryFragment()).commit();
                        return true;
                    }
                    break;

                case R.id.navigation_notification:
                    startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPrefs=getSharedPreferences("First",MODE_PRIVATE);
        SharedPreferences.Editor edit=mSharedPrefs.edit();
        edit.putBoolean("FirstTime",false);
        edit.commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRef = FirebaseDatabase.getInstance().getReference("Events");
        mAuth = FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Please wait");
        pDialog.setMessage("Loading data...");
        pDialog.setCancelable(false);
        pDialog.show();
        data = getAllEventsData();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.getMenu().getItem(2).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, new HomeFragment()).commit();

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ojass");

        bottomSheetDialog = new BottomSheetDialog(HomeActivity.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.explore_dialog, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
        ImageView ivSponsors = bottomSheetDialogView.findViewById(R.id.exp_sponsors);
        ImageView ivOjassTeam = bottomSheetDialogView.findViewById(R.id.exp_ojass_team);
        ImageView ivGuruGyan = bottomSheetDialogView.findViewById(R.id.exp_guru_gyan);
        ImageView ivAppDev = bottomSheetDialogView.findViewById(R.id.exp_app_dev);
        ImageView ivFaq = bottomSheetDialogView.findViewById(R.id.exp_faq);

        ivSponsors.setOnClickListener(this);
        ivOjassTeam.setOnClickListener(this);
        ivGuruGyan.setOnClickListener(this);
        ivAppDev.setOnClickListener(this);
        ivFaq.setOnClickListener(this);

        compareAppVersion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.home_menu, menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_qr_code:
                ceateQRPopup();
                return true;
            case R.id.menu_about:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                return true;
            case R.id.menu_ojass_team:
                startActivity(new Intent(HomeActivity.this, OjassDepartment.class));
                return true;
            case R.id.menu_faq:
                startActivity(new Intent(HomeActivity.this,FAQsActivity.class));
                return  true;

            case R.id.menu_sponsors:
                startActivity(new Intent(HomeActivity.this, SponsorActivity.class));
                return true;
            case R.id.menu_app_dev:
                startActivity(new Intent(HomeActivity.this, DeveloperView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ceateQRPopup() {
        final Dialog QRDialog = new Dialog(this);
        QRDialog.setContentView(R.layout.dialog_qr);
        final TextView tvOjassId = QRDialog.findViewById(R.id.tv_ojass_id);
        final ImageView ivQR = QRDialog.findViewById(R.id.iv_qr_code);
        QRDialog.getWindow().getAttributes().windowAnimations = R.style.pop_up_anim;
        QRDialog.show();
        QRDialog.findViewById(R.id.rl_qr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvOjassId.getText().toString().equals(Constants.NOT_REGISTERED)) {
                    startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
                    finish();
                }
            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(FIREBASE_REF_USERS).child(mAuth.getCurrentUser().getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NewApi")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        Utilities.setPicassoImage(HomeActivity.this, "https://api.qrserver.com/v1/create-qr-code/?data=" + mAuth.getCurrentUser().getUid() + "&size=240x240&margin=10", ivQR, Constants.SQUA_PLACEHOLDER);
                        if (dataSnapshot.child(FIREBASE_REF_OJASS_ID).exists()) {
                            tvOjassId.setText(dataSnapshot.child(FIREBASE_REF_OJASS_ID).getValue().toString());
                            tvOjassId.setTextColor(getResources().getColor(R.color.forest_green));
                        } else {
                            tvOjassId.setText(Constants.PAYMENT_DUE);
                            tvOjassId.setTextColor(Color.RED);
                        }
                    } catch (Exception e) {

                    }
                } else {
                    Picasso.with(HomeActivity.this).load(R.drawable.notreg).fit().into(ivQR);
                    tvOjassId.setText(Constants.NOT_REGISTERED);
                    tvOjassId.setTextColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void compareAppVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            currentVersion = pInfo.versionName;
            new GetCurrentVersion().execute();
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

    }

    private List<EventModel> getAllEventsData() {
        List<EventModel> list = new ArrayList<>();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                //add progress dialog
                pDialog.dismiss();
                try {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String about = ds.child("about").getValue(String.class);
                        String details = ds.child("detail").getValue(String.class);
                        String branch = ds.child("branch").getValue(String.class);
                        ;
                        String name = ds.child("name").getValue(String.class);
                        //                Long prize1 = Long.valueOf(0),prize2 = Long.valueOf(0),prize3 = Long.valueOf(0),prizeT= Long.valueOf(0);
                        PrizeModel2 p2 = null;
                        PrizeModel1 p1 = null;
                        if (checkPrizeType(name)) {
                            Long prize1 = ds.child("prize").child("first").getValue(Long.class);
                            Long prize2 = ds.child("prize").child("second").getValue(Long.class);
                            Long prize3 = ds.child("prize").child("third").getValue(Long.class);
                            Long prize4 = ds.child("prize").child("fourth").getValue(Long.class);
                            Long prize5 = ds.child("prize").child("fifth").getValue(Long.class);
                            Long prize6 = ds.child("prize").child("sixth").getValue(Long.class);
                            Long prizeT = ds.child("prize").child("total").getValue(Long.class);
                            p1 = new PrizeModel1(prize1, prize2, prize3, prize4, prize5, prize6, prizeT);
                        } else {

                            Long prizeT, prize1_F, prize2_F, prize3_F, prize1_S, prize2_S, prize3_S, prize1_T, prize2_T, prize3_T;
                            prizeT = ds.child("prize").child("total").getValue(Long.class);
                            prize1_F = ds.child("prize").child("firstyear").child("first").getValue(Long.class);
                            prize2_F = ds.child("prize").child("firstyear").child("second").getValue(Long.class);
                            prize3_F = ds.child("prize").child("firstyear").child("third").getValue(Long.class);
                            prize1_S = ds.child("prize").child("secondyear").child("first").getValue(Long.class);
                            prize2_S = ds.child("prize").child("secondyear").child("second").getValue(Long.class);
                            prize3_S = ds.child("prize").child("secondyear").child("third").getValue(Long.class);
                            prize1_T = ds.child("prize").child("thirdyear").child("first").getValue(Long.class);
                            prize2_T = ds.child("prize").child("thirdyear").child("second").getValue(Long.class);
                            prize3_T = ds.child("prize").child("thirdyear").child("third").getValue(Long.class);
                            p2 = new PrizeModel2(prizeT, prize1_F, prize2_F, prize3_F, prize1_S, prize2_S, prize3_S, prize1_T, prize2_T, prize3_T);
                        }

                        ArrayList<CoordinatorsModel> coordinatorsModelArrayList = new ArrayList<>();
                        coordinatorsModelArrayList.clear();
                        ArrayList<RulesModel> rulesModelArrayList = new ArrayList<>();
                        rulesModelArrayList.clear();
                        for (DataSnapshot d : ds.child("coordinators").getChildren()) {
                            CoordinatorsModel coordinatorsModel = d.getValue(CoordinatorsModel.class);
                            coordinatorsModelArrayList.add(coordinatorsModel);
                        }
                        for (DataSnapshot d : ds.child("rules").getChildren()) {
                            RulesModel rulesModel = d.getValue(RulesModel.class);
                            rulesModelArrayList.add(rulesModel);
                        }
                        data.add(new EventModel(about, branch, details, name, p1, p2, coordinatorsModelArrayList, rulesModelArrayList));
                    }
                } catch (Exception e) {
                    Log.e("EXCEPTION", e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }


    private boolean checkPrizeType(String name) {
        if ((name.compareToIgnoreCase("embetrix") == 0) ||
                (name.compareToIgnoreCase("High Voltage Concepts") == 0) ||
                (name.compareToIgnoreCase("electrospection") == 0) ||
                (name.compareToIgnoreCase("Electro Scribble") == 0) ||
                (name.compareToIgnoreCase("matsim") == 0) ||
                (name.compareToIgnoreCase("Pro-Lo-Co") == 0) ||
                (name.compareToIgnoreCase("Hack-De-Science") == 0) ||
                (name.compareToIgnoreCase("agnikund") == 0) ||
                (name.compareToIgnoreCase("knockout") == 0)
                ) {
            return false;
        }
        return true;
    }

    private class GetCurrentVersion extends AsyncTask<Void, Void, Void> {

        private String latestVersion;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(urlOfApp).get();
                latestVersion = doc.getElementsByAttributeValue
                        ("itemprop", "softwareVersion").first().text();

            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!TextUtils.isEmpty(currentVersion) && !TextUtils.isEmpty(latestVersion)) {
                Log.d("AppVersion", "Current : " + currentVersion + " Latest : " + latestVersion);
                if (currentVersion.compareTo(latestVersion) < 0) {
                    if (!isFinishing()) {
                        showUpdateDialog();
                    }
                }
            }
            super.onPostExecute(aVoid);
        }
    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New update");
        builder.setMessage("We have changed since we last met. Let's get the updates.");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=in.nitjsr.ojass19")));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}



