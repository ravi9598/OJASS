package in.nitjsr.ojass19.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import in.nitjsr.ojass19.R;

public class AboutActivity extends AppCompatActivity {

    ImageButton fb, insta, twitter, helpdesk_phone, helpdesk_whatsapp, share, rateUs, webpage;

    private static final String AKASH_WHATSAPP = "9534034604";
    private static final String AKASH_CALLING = "7488650379";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        fb = findViewById(R.id.fb);
        insta = findViewById(R.id.insta);
        twitter = findViewById(R.id.twitter);
        helpdesk_phone = findViewById(R.id.ib_helpdesk_phone);
        helpdesk_whatsapp = findViewById(R.id.ib_helpdesk_whatsapp);
        share=findViewById(R.id.ib_app_share);
        rateUs=findViewById(R.id.ib_app_rate_us);
        webpage = (ImageButton) findViewById(R.id.web);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Ojassnitjamshedpur/?ref=br_rs"));
                startActivity(intent);
            }
        });

        findViewById(R.id.ib_back_faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ojass.in"));
                startActivity(intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playStoreURL = "https://play.google.com/store/apps/details?id=in.nitjsr.ojass19";
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Ojass");
                i.putExtra(Intent.EXTRA_TEXT, playStoreURL);
                startActivity(Intent.createChooser(i, ""));
            }
        });

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appPackageName = "in.nitjsr.ojass19";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ojass_techfest/"));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ojass_nitjsr?s=08"));
                startActivity(intent);
            }
        });


        helpdesk_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91"+AKASH_CALLING));
                startActivity(intent);
            }
        });

        helpdesk_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=91" + AKASH_WHATSAPP + "&text=Hey! I'm "+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName()+".";
                sendIntent.setData(Uri.parse(url));
                startActivity(sendIntent);
            }
        });

    }
}
