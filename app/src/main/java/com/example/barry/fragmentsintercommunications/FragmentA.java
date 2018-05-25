package com.example.barry.fragmentsintercommunications;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * click com.ex > new > java class
 * type Fragment in Suoer class and select the v4 one
 * this will create a new fragment with support v4 included
 */
public class FragmentA extends Fragment {

    //6 create a var for fragment a listener
    private FragmentAListener listener;

    //1. declare the widgets
    private EditText editText;
    private Button buttonOk;

    // 5. for communications we create an interface
    // note that there is no bracket in the interface, only curly braces
    public interface FragmentAListener {
        // later we shall implement this method in our activity and also the onInputASent
        // and pass the CharSequence to it
        // an interface is similar to an onClickListener

        void onInputASent(CharSequence input);

    }


    //2. override on createView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //3. define the root view
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        //4. init the views
        editText = view.findViewById(R.id.edit_text);
        buttonOk = view.findViewById(R.id.button_ok);

        //7. create a click listener for the button
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get the values of our edit text with CarSequence
                CharSequence input = editText.getText();
                listener.onInputASent(input);
                // append the onInputASent with the listener above to send
                // the data to where ever it will be implemented, main activity in this case
                // now go and override onAttach() and attach the listener var
            }
        });
        return view;
    }

    //10 method to update our edit text when we receive the input
    public void updateEditText(CharSequence newtext) {
        // this is how we get our data from activity into the fragment
        editText.setText(newtext);
    }

    //8 override onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // check is our activity implements the interface
        if (context instanceof FragmentAListener ) {

            listener = (FragmentAListener) context;

            // this means we implemented our interface in our activity
            // and if we forget we want to catch the exception
        } else {
            throw new RuntimeException(context.toString()
            + "Must implement FragmentAListener"); // if we forgot, this msg will show
        }
    }

    //9. also override on deatach when we remove the interface
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;  // just set the listener to null
    }
}
