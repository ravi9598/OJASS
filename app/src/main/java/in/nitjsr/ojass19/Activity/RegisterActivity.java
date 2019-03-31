package in.nitjsr.ojass19.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button btnRegister;
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

        tvSkipThis.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        checkSMSPermission();
    }

    private void autoFill() {
        inputName.setText(mUser.getDisplayName());
        inputEmail.setText(mUser.getEmail());
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
            if(validate() /*&& verifyNumber()*/)
            {
                //if(numberVerified)
                //{
                    registerUser();
                //}
            }
        }
    }

    private boolean verifyNumber() {
        mobileNumber="+91 "+inputMobile.getText().toString().trim();
        sendVerificationCode(mobileNumber);
        Dialog dialog=new Dialog(RegisterActivity.this);
        dialog.setContentView(R.layout.verify_number_layout);
        dialog.show();
        etCode=dialog.findViewById(R.id.verify_code);
        btnVerify=dialog.findViewById(R.id.verify_btn);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=etCode.getText().toString().trim();
                if(code.isEmpty() || code.length()<6)
                {
                    etCode.setError("Enter valid code");
                    etCode.requestFocus();
                }
                else
                {
                    verifyVerificationCode(code);
                }
            }
        });
        return true;
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                etCode.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationID = s;
        }
    };

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            numberVerified=true;
                        }
                        else
                        {
                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
                            numVerified=false;
                            Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validate() {
        boolean valid =true;
//        if(inputEmail.getText().toString().trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString().trim()).matches())
//        {
//            inputEmail.setError("Please enter a valid Email address");
//            valid=false;
//        }
//        if(inputMobile.getText().toString().trim().isEmpty() || Patterns.PHONE.matcher(inputMobile.getText().toString().trim()).matches())
//        {
//            inputMobile.setError("Please enter valid Mobile Number");
//            valid=false;
//        }
//        if(inputName.getText().toString().trim().isEmpty())
//        {
//            inputName.setError("Please enter your Name");
//            valid=false;
//        }
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
