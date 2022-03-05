package com.example.breathalyzerapp.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.breathalyzerapp.Models.User;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;

import java.util.Objects;

public class InsertUserDialogueFragment extends DialogFragment {
    protected EditText firstnameEditText, lastNameEditText, ageEditText, genderEditText, weightEditText;
    protected Button userSaveButton, userCancelButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert_user,container,false);

        // find views by ID
        firstnameEditText = view.findViewById(R.id.firstName_editText);
        lastNameEditText = view.findViewById(R.id.lastName_editText);
        ageEditText = view.findViewById(R.id.age_editText);
        genderEditText = view.findViewById(R.id.gender_editText);
        weightEditText = view.findViewById(R.id.weight_fragment_editText);
        userCancelButton = view.findViewById(R.id.userCancelButton);
        userSaveButton = view.findViewById(R.id.userSaveButton);

        userSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = firstnameEditText.getText().toString().trim();
                String lastname = lastNameEditText.getText().toString().trim();
                String age = ageEditText.getText().toString().trim();
                String gender = genderEditText.getText().toString().trim();
                String weight = weightEditText.getText().toString().trim();

                // If valid inputs, enter user into user table
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                if(validInput(firstname,lastname,age,gender,weight)) {
                    databaseHelper.insertUser(new User(firstname,lastname,Integer.parseInt(age),Double.parseDouble(weight),gender));
                    ((ProfilesActivity) getActivity()).loadUsersListView();
                    Objects.requireNonNull(getDialog()).dismiss();
                }
            }
        });

        userCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        return view;
    }

    // TODO: make adjustments to invalid options -- need to check if weight is valid (decimal place etc)
    private boolean validInput(String firstname, String lastname, String age, String gender, String weight){
        // Lastname & firstname taken care of in XML -- just check if empty
        if(firstname.equals("") || lastname.equals("")){
            Toast.makeText(getActivity(), "Cannot leave First or Last name blank",Toast.LENGTH_LONG).show();
            return false;
        }
        // Check if age is empty
        else if(age.length() == 0){
            Toast.makeText(getActivity(), "Cannot leave age empty",Toast.LENGTH_LONG).show();
            Log.d("Error","age empty!");
            return false;
        }
        // Check if gender is empty
        else if(gender.length() == 0){
            Toast.makeText(getActivity(), "Cannot leave gender empty",Toast.LENGTH_LONG).show();
            Log.d("Error","gender empty!");
            return false;
        }
        // Check weight is empty
        else if(weight.length() == 0){
            Toast.makeText(getActivity(), "Cannot weight empty",Toast.LENGTH_LONG).show();
            Log.d("Error","Weight empty!");
            return false;
        }
        return true;
    }
}
