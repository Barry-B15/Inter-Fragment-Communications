package com.example.barry.fragmentsintercommunications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//4. implement the 2 fragments
public class MainActivity extends AppCompatActivity
        implements FragmentA.FragmentAListener, FragmentB.FragmentBListener{

    //1. create variables for the 2 fragments
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. create iinstances of the fragments
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        //3. display the 2 fragments in the frame layout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
    }

    //5 ctrl + I or alt + enter will implement the methods
    @Override
    public void onInputASent(CharSequence input) {
        // update fragmentB in FragmentA
        fragmentB.updateEditText(input);
    }

    @Override
    public void onInputBSent(CharSequence input) {

        // update fragmentB in FragmentA
        fragmentA.updateEditText(input);
    }
}
