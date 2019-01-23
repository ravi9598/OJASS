package in.nitjsr.ojass19.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import in.nitjsr.ojass19.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnLoginGoogle, btnLoginFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnLoginGoogle.setOnClickListener(this);
        btnLoginFB.setOnClickListener(this);

    }

    private void init() {
        btnLoginGoogle=findViewById(R.id.btn_login_google);
        btnLoginFB=findViewById(R.id.btn_login_fb);
    }

    @Override
    public void onClick(View view) {
        if(view==btnLoginGoogle)
        {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            finish();
        }
        else if(view==btnLoginFB)
        {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            finish();
        }
    }
}
