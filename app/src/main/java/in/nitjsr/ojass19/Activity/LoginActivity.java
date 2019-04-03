package in.nitjsr.ojass19.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import in.nitjsr.ojass19.R;
import in.nitjsr.ojass19.Utils.SharedPrefManager;

import static in.nitjsr.ojass19.Utils.Constants.FIREBASE_REF_USERS;

public class LoginActivity extends AppCompatActivity {

    public static final int RequestSignInCode = 7;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ImageView btnLoginGoogle;
    private ProgressDialog pd;
    int isProgressShowing = 1;
    private static final String TAG = "LoginActivity";
    private String userName = "";
    private SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPrefs = getSharedPreferences("First", MODE_PRIVATE);
        SharedPreferences.Editor edit = mSharedPrefs.edit();
        edit.putBoolean("FirstTime", false);
        edit.commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Picasso.with(this).load(R.drawable.login_bg).fit().into((ImageView)findViewById(R.id.iv_login));

        mAuth = FirebaseAuth.getInstance();

        btnLoginGoogle = findViewById(R.id.btn_login_google);

        pd = new ProgressDialog(this);
        pd.setTitle("Please wait...");
        pd.setMessage("Logging in...");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin();
            }
        });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPrefManager sm = new SharedPrefManager(LoginActivity.this);
                            sm.setIsLoggedIn(true);
                            userName = mAuth.getCurrentUser().getDisplayName();
                            isRegisteredUser();
                        } else {
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }
                            Log.d(TAG, "Authentication failed. Reason: " + task.getException());
                        }
                    }
                });

    }

    private void googleLogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RequestSignInCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestSignInCode) {
            pd.show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Google Sign in failed. Reason: " + e.getMessage());
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
        }
    }


//    private void isRegisteredUser() {
//        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
//        if(sharedPrefManager.isRegistered())
//        {
//            Toast.makeText(this, "Welcome to Ojass'19! "+userName, Toast.LENGTH_SHORT).show();
//            moveToHomeActivity();
//        }
//        else
//        {
//            Toast.makeText(LoginActivity.this, "Hey "+userName+"! Let us know you better.", Toast.LENGTH_LONG).show();
//            moveToRegisterActivity();
//        }
//    }

    private void moveToHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void moveToRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    private void isRegisteredUser() {
        final String fName = mAuth.getCurrentUser().getDisplayName().split(" ")[0];
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(FIREBASE_REF_USERS).child(mAuth.getCurrentUser().getUid());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        moveToHomeActivity();
                        Toast.makeText(LoginActivity.this, "Welcome to Ojass Dashboard! " + fName, Toast.LENGTH_LONG).show();
                    } else {
                        moveToRegisterActivity();
                        Toast.makeText(LoginActivity.this, "Hey " + fName + "! Let us know you better.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
