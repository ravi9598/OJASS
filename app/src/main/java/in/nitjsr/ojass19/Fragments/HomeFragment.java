package in.nitjsr.ojass19.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import in.nitjsr.ojass19.R;

public class HomeFragment extends Fragment {

    ImageButton fb;
    ImageButton insta;
    ImageButton twitter;
    ImageButton share, helpdesk_phone, helpdesk_whatsapp, rateUs;

    private static final String AKASH_WHATSAPP = "9534034604";
    private static final String AKASH_CALLING = "7488650379";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        fb = view.findViewById(R.id.fb);
        insta = view.findViewById(R.id.insta);
        twitter = view.findViewById(R.id.twitter);
        share = view.findViewById(R.id.ib_app_share);
        helpdesk_phone = view.findViewById(R.id.ib_helpdesk_phone);
        helpdesk_whatsapp = view.findViewById(R.id.ib_helpdesk_whatsapp);
        rateUs = view.findViewById(R.id.ib_app_rate_us);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Ojassnitjamshedpur/?ref=br_rs"));
                startActivity(intent);
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

        return view;
    }
}
