package arkham.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import arkham.app.datamodel.APIError;
import arkham.app.datamodel.Session;
import arkham.app.preferences.SessionPreferences;
import arkham.app.service.ServiceGenerator;
import arkham.app.service.SunscreenAPI;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.login_errors) TextView _loginErrors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        SunscreenAPI apiService = ServiceGenerator.createService(SunscreenAPI.class);

        Session loginParams = new Session(email, password);
        Call<Session> call = apiService.login(loginParams);
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if (response.isSuccess()) {
                    Log.d(TAG, "Logged in");
                    Session session = response.body();
                    save_token(session.getData().getAuthToken());
                    onLoginSuccess();
                    openDashoard();
                    progressDialog.dismiss();
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    List<String> errors = error.getErrors();
                    String e = errors.get(0);
                    _loginErrors.setText(e);
                    onLoginFailed();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {

            }
        });



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run () {
                        progressDialog.dismiss();
                        onLoginFailed();
                        _loginButton.setEnabled(true);
                    }
                }

                ,10000);
    }

    private void openDashoard(){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
    private void save_token(String token){
        SessionPreferences.saveSessionToken(token, MyApplication.getAppContext());
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            _passwordText.setError("must be longer than 6 characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
