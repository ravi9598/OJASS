package in.nitjsr.ojass19.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Field;

import in.nitjsr.ojass19.Fragments.EventsFragment;
import in.nitjsr.ojass19.Fragments.HomeFragment;
import in.nitjsr.ojass19.Fragments.ItinaryFragment;
import in.nitjsr.ojass19.Fragments.ProfileFragment;
import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.CustomViewPager;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class HomeActivity extends AppCompatActivity {

    private static final String urlOfApp = "https://play.google.com/store/apps/details?id=in.nitjsr.ojass19&hl=en";
    private String currentVersion;
    private BottomNavigationView navigation;
    private FirebaseAuth mAuth;
    private SharedPrefManager sharedPrefManager;
    private static CustomViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frame_container,new HomeFragment()).commit();
                    return true;
                case R.id.navigation_event:
                    transaction.replace(R.id.frame_container,new EventsFragment()).commit();
                    return true;
                case R.id.navigation_something:
                    transaction.replace(R.id.frame_container, new ProfileFragment()).commit();
                    return true;
                case R.id.navigation_itinerary:
                    transaction.replace(R.id.frame_container, new ItinaryFragment()).commit();
                    return true;
                case R.id.navigation_profile:
                    transaction.replace(R.id.frame_container, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container,new HomeFragment()).commit();
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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=in.nitjsr.ojass")));
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



