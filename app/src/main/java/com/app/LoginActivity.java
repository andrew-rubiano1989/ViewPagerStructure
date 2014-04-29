package com.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;


public class LoginActivity extends Activity {


    private SignInButton gPlusSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Button mEmailSignInButton = (Button) findViewById(R.id.loginButton);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        // Google+ Sign in Start
        // Find the Google+ sign in button.
        gPlusSignInButton = (SignInButton) findViewById(R.id.plus_sign_in_button);

        gPlusSignInButton.setEnabled(true);
        gPlusSignInButton.setSize(SignInButton.SIZE_ICON_ONLY);// wide button style

            // Set a listener to connect the user when the G+ button is clicked.
            gPlusSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //signIn();
                    Context context = getApplicationContext();
                    CharSequence text = "No one uses Google+!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
    }
    // End google+ sign in stuff

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
