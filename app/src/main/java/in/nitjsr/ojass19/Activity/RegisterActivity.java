package in.nitjsr.ojass19.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.nitjsr.ojass19.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        btnSkip.setOnClickListener(this);
    }

    private void init() {
        btnSkip=findViewById(R.id.btn_reg_skip);
    }

    @Override
    public void onClick(View view) {
        if(view==btnSkip)
        {
            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
            finish();
        }
    }
}
