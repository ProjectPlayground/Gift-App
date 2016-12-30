package com.example.adrien.gift_app;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout navbar = (TabLayout)findViewById(R.id.id_tab);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("MainActivity","hello: "+ user.getUid());

        navbar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager();
                final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                switch(navbar.getSelectedTabPosition()) {
                    case 0:
                        Log.d("MainActivity", "Hello : c'est la case 1");
                        break;
                    case 1:
                        Fragment addIdeasFragment = new IdeasFormFragment();
                        fragmentTransaction.replace(R.id.id_frame, addIdeasFragment);
                        Log.d("MainActivity", "Hello : c'est la case 2");
                        break;
                    case 2:
                        Log.d("MainActivity", "Hello : c'est la case 3");
                        break;
                    default:
                        Log.d("MainActivity", "Hello : c'est un test");
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                
            }
        });

    }



}


