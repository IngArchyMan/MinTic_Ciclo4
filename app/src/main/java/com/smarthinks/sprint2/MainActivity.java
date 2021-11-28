package com.smarthinks.sprint2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;

public class MainActivity extends Activity {

    protected EditText mUserText;
    protected EditText mPassText;
    private CardView mBtnIngresar;
    private ContentLoadingProgressBar progress_circular_loading;

    InputMethodManager methodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserText = findViewById(R.id.editUsuario);
        mPassText = findViewById(R.id.editPassword);
        mBtnIngresar = findViewById(R.id.btnIngresar);
        progress_circular_loading = findViewById(R.id.progress_circular_loading);
        methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        mBtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validacionDeUsuario();
            }
        });

    }

    private boolean validacionDeUsuario() {

        final String usuario = mUserText.getText().toString();
        mUserText.setError(null);
        mPassText.setError(null);

        if(usuario.trim().isEmpty()){
            mUserText.setError("Los campos no pueden estar vacios");
            mUserText.requestFocus();
            return false;
        }else {
            final String password = mPassText.getText().toString();
            if (usuario.length() <= 4) {
                mPassText.setError("Los campos no deben ser mayor o igual a 5");
                mPassText.requestFocus();
                return false;
            } else if (password.trim().isEmpty()) {
                mPassText.setError("Los campos no pueden estar vacios");
                mPassText.requestFocus();
                return false;
            } else if (password.length() <= 4) {
                mPassText.setError("Los campos no deben ser mayor o igual a 5");
                mPassText.requestFocus();
                return false;
            } else if (!"pueba".equals(usuario) && !"12345".equals(password)) {
                methodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                progress_circular_loading.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Usuario/contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        mUserText.setText("");
                        mPassText.setText("");
                        mUserText.requestFocus();
                        progress_circular_loading.setVisibility(View.INVISIBLE);
                    }
                }, 7000);

                return false;
            }
        }
        methodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        progress_circular_loading.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Usuario autorizado", Toast.LENGTH_SHORT).show();
            }
        }, 7000);

        return true;
    }
}