package com.example.plantplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

public class UserPageActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ImageView pfp;
    private TextView name;
    private TextView email;
    private Button signOutBtn;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    private ImageButton calendarBtn;
    private ImageButton searchBtn;
    private ImageButton curPlantsBtn;
    private ImageButton helpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        pfp = findViewById(R.id.profileIcon);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.sign_outBtn);

        name.setText("Micah Warner");
        email.setText("micahawarner04@gmail.com");

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API , gso).build();

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            goToSignUpPage();
                        }
                        else {
                            Toast.makeText(UserPageActivity.this, "Log Out Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        // Navigates to Calendar Page
        calendarBtn = (ImageButton)findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPageActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Search Plants Page
        searchBtn = (ImageButton)findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPageActivity.this, SearchPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to Current Plants Page
        curPlantsBtn = (ImageButton)findViewById(R.id.plantButton);
        curPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPageActivity.this, CurrentPlantsActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Navigates to HowToUse Page
        helpBtn = (ImageButton)findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserPageActivity.this, HowToUseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void goToSignUpPage() {
        startActivity(new Intent(UserPageActivity.this, SignUpPageActivity.class));
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();

            name.setText(account.getDisplayName());
            email.setText(account.getEmail());
            Picasso.get().load(account.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(pfp);
        }
        else
        {
            //goToSignUpPage();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }
        else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}