package in.nitjsr.ojass19.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class WalkthroughActivity extends FancyWalkthroughActivity {

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_walkthrough);
        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Dashboard", "Immerse into the brand new user dashboard.", R.drawable.ic_dashboard_black_24dp);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Location", "Enjoy the new Maps Location Feature.", R.drawable.ic_location_on_black_24dp);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Notifications", "Get Notifications on the event of your choice and never miss out on an event ever again.", R.drawable.ic_notifications_active_black_24dp);
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("So, why Wait ?", "Let's make this Official.", R.drawable.ic_person_add_black_24dp);


        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        //fancywalkthroughCard1.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        //fancywalkthroughCard2.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        //fancywalkthroughCard3.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancywalkthroughCard4.setBackgroundColor(R.color.white);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            fancywalkthroughCard4.setBackgroundColor(R.color.white);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setImageBackground(R.drawable.bg_4);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.black);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.isLoggedIn())
            moveToHome();
        else
            moveToLogin();
    }

    private void moveToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void moveToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}