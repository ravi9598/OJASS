package in.nitjsr.ojass19.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import in.nitjsr.ojass19.R;

public class member_view extends Activity {

    ImageView img;
    TextView tvtitle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.member_view_card);
        img = (ImageView)findViewById(R.id.member_image);
        tvtitle = (TextView)findViewById(R.id.close) ;


       /* window = this.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/




      //  img = (ImageView) findViewById(R.id.book_img_id);


        // Recieve data
        Intent intent = getIntent();
        //    String Title = intent.getExtras().getString("Title");
        //String Description = intent.getExtras().getString("Description");
        String image = intent.getExtras().getString("Thumbnail") ;

        // Setting values

        //  tvtitle.setText(Title);
      //  tvdescription.setText(Description);
     /*   RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);



        Glide.with(this).load(image).apply(options).into(img);*/

        Glide.with(this)
                .load(image)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ojass_icon)
                        .fitCenter())
                .into(img);


        tvtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


    }
}

