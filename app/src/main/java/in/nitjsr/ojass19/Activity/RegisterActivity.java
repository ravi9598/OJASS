package in.nitjsr.ojass19.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.Constants;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tvSkipThis;
    private Spinner spinner;
    private CardView verifybtn_card;
    private Button btnRegister,verify_btn;
    private EditText inputName, inputEmail, inputMobile, inputCollege, inputRegId, inputBranch;
    private String tshirtSize;
    private FirebaseUser mUser;
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
    private static final int APP_REQUEST_CODE=99;
    private boolean numberVerified=false;
    private FirebaseAuth mAuth;
    private String mVerificationID;
    private EditText etCode;
    private Button btnVerify;
    private String mobileNumber;
    private boolean numVerified=false;
    private boolean mobileVerified=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUser= FirebaseAuth.getInstance().getCurrentUser();
        mAuth=FirebaseAuth.getInstance();

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

        verify_btn.setOnClickListener(this);

        inputMobile.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 10){
                    verifybtn_card.setVisibility(View.VISIBLE);
                } else {
                    verifybtn_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvSkipThis.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        checkSMSPermission();
    }

    private void autoFill() {
        inputName.setText(mUser.getDisplayName());
        inputEmail.setText(mUser.getEmail());
        inputMobile.setText(mUser.getPhoneNumber());
        inputEmail.setEnabled(false);
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
        verify_btn=findViewById(R.id.verify_button);
        verifybtn_card=findViewById(R.id.verifybtn_card);
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
            if(validate()&&mobileVerified) {
                registerUser();
            }
            else if (!mobileVerified){
                Toast.makeText(RegisterActivity.this, "Verify Mobile Number", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(RegisterActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view==verify_btn)
        {
            if (verify_btn.getText().toString().equals("Edit")) {
                verify_btn.setText("Verify");
                inputMobile.setEnabled(true);
                mobileVerified = false;
            } else phoneLogin();
        }
    }

    public void phoneLogin() {
        PhoneNumber phoneNumber=new PhoneNumber("+91",inputMobile.getText().toString(),"IN");
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE);
        configurationBuilder.setInitialPhoneNumber(phoneNumber);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }
                mobileVerified = true;
                inputMobile.setEnabled(false);
                verify_btn.setText("Edit");
            }

        }
    }



    private boolean validate() {
        boolean valid =true;

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
        if(spinner.getSelectedItemPosition()==0)
        {
            //Toast.makeText(RegisterActivity.this,"Please select your T-Shirt size",Toast.LENGTH_SHORT).show();
            valid=false;
        }
        return valid;
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
        new SharedPrefManager(this).setIsRegistered(true);
        startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
        finish();
    }
}
