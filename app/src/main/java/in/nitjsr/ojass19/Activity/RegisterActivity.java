package in.nitjsr.ojass19.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvSkipThis;
    private Spinner spinner;
    private Button btnRegister;
    private EditText inputName, inputEmail, inputMobile, inputCollege, inputRegId, inputBranch;
    private String tshirtSize;
    private FirebaseUser mUser;
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
    private static final int APP_REQUEST_CODE=99;
    private boolean numberVerified=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUser= FirebaseAuth.getInstance().getCurrentUser();

        init();
        autoFill();
        inputMobile.requestFocus();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tshirtSize=parent.getResources().getStringArray(R.array.tshirt_size)[position].split(" ")[0];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvSkipThis.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        checkSMSPermission();
    }

    private void autoFill() {
        inputName.setText(mUser.getDisplayName());
        inputEmail.setText(mUser.getEmail());
    }

    private void init() {
        toolbar=findViewById(R.id.reg_toolbar);
        tvSkipThis=findViewById(R.id.skip_this);
        inputName=findViewById(R.id.input_name);
        inputEmail=findViewById(R.id.input_email);
        inputMobile=findViewById(R.id.input_mobile);
        inputCollege=findViewById(R.id.input_college);
        inputRegId=findViewById(R.id.input_reg_id);
        inputBranch=findViewById(R.id.input_branch);
        btnRegister=findViewById(R.id.btn_register);
        spinner=findViewById(R.id.spinner_tshirt_size);
    }

    @Override
    public void onClick(View view) {
        if(view==tvSkipThis)
        {
            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
            finish();
        }
        else if(view==btnRegister)
        {
            if(validate())
            {
                registerUser();
            }
//            else if(!numberVerified)
//            {
//
//            }
        }
    }

    private boolean validate() {
        boolean valid =true;
        if(inputEmail.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString().trim()).matches())
        {
            inputEmail.setError("Please enter a valid Email address");
            valid=false;
        }
//        if(inputMobile.getText().toString().trim().isEmpty() || Patterns.PHONE.matcher(inputMobile.getText().toString().trim()).matches())
//        {
//            inputMobile.setError("Please enter valid Mobile Number");
//            valid=false;
//        }
        if(inputName.getText().toString().trim().isEmpty())
        {
            inputName.setError("Please enter your Name");
            valid=false;
        }
        if(inputCollege.getText().toString().trim().isEmpty())
        {
            inputCollege.setError("Please enter your College Name");
            valid=false;
        }
        if(inputRegId.getText().toString().trim().isEmpty())
        {
            inputRegId.setError("Please enter your Registration Id");
            valid=false;
        }
        if(inputBranch.getText().toString().trim().isEmpty())
        {
            inputBranch.setError("Please enter your Branch");
            valid=false;
        }
        return valid && numberVerified;
    }

    private void checkSMSPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_SMS
            }, 101);
        }
    }

    private void registerUser()
    {
        userRef = userRef.child(mUser.getUid());
        userRef.child(Constants.FIREBASE_REF_EMAIL).setValue(mUser.getEmail());
        userRef.child(Constants.FIREBASE_REF_NAME).setValue(mUser.getDisplayName());
        userRef.child(Constants.FIREBASE_REF_PHOTO).setValue(mUser.getPhotoUrl().toString());
        userRef.child(Constants.FIREBASE_REF_MOBILE).setValue(inputMobile.getText().toString());
        userRef.child(Constants.FIREBASE_REF_COLLEGE).setValue(inputCollege.getText().toString());
        userRef.child(Constants.FIREBASE_REF_COLLEGE_REG_ID).setValue(inputRegId.getText().toString());
        userRef.child(Constants.FIREBASE_REF_BRANCH).setValue(inputBranch.getText().toString());
        userRef.child(Constants.FIREBASE_REF_TSHIRT_SIZE).setValue(tshirtSize);
        Toast.makeText(this, "Welcome to Ojass'19 Dashboard!", Toast.LENGTH_LONG).show();
        new SharedPrefManager(this).setIsLoggedIn(true);
        startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
        finish();
    }
}
