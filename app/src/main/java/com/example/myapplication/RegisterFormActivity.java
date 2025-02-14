package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterFormActivity extends AppCompatActivity {

    private Button btnDatePicker;
    private int year, month, day;
    String bodInput = "";

    boolean tennisChecked = false;
    boolean footballChecked = false;
    boolean othersChecked = false;

    String genderStr = "";
    String hobbies = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        EditText editText3 = findViewById(R.id.editText3);
        EditText editText4 = findViewById(R.id.editText4);

        RadioGroup genderGroup = findViewById(R.id.genderType);

        CheckBox checkBoxTennis = findViewById(R.id.tennis);
        CheckBox checkBoxFootball = findViewById(R.id.football);
        CheckBox checkBoxOthers = findViewById(R.id.others);

        Button resetButton = findViewById(R.id.reset);
        Button signUpButton = findViewById(R.id.signup);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");

                genderGroup.clearCheck();

                checkBoxTennis.setChecked(false);
                checkBoxFootball.setChecked(false);
                checkBoxOthers.setChecked(false);
            }
        });

        // Initialize current date
        btnDatePicker = findViewById(R.id.selectButton);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterFormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                editText4.setText(selectedDate);
                                bodInput = selectedDate;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup genderGroup, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                genderStr = rb.getText().toString();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //check password
                boolean check = true;
                if(!editText2.getText().toString().equals(
                        editText3.getText().toString()))
                {
                    Toast.makeText(v.getContext(), "Please retype password!",
                            Toast.LENGTH_SHORT).show();
                    check = false;
                }

                //check dob
                bodInput = editText4.getText().toString();
                if(!isValidDate(bodInput))
                {
                    Toast.makeText(v.getContext(), "Please retype birthdate!",
                            Toast.LENGTH_SHORT).show();
                    check = false;
                }

                //check condition
                if(genderStr.isEmpty())
                {
                    Toast.makeText(v.getContext(), "Please check gender!",
                            Toast.LENGTH_SHORT).show();
                    check = false;
                }


                if(check)
                {
                    String username = editText1.getText().toString();
                    String password = editText2.getText().toString();
                    String birthdate = bodInput;
                    String gender = genderStr;
                    List<String> hobbiesList = new ArrayList<>();

                    if (tennisChecked) {
                        hobbiesList.add("Tennis");
                    }
                    if (footballChecked) {
                        hobbiesList.add("Football");
                    }
                    if (othersChecked) {
                        hobbiesList.add("Others");
                    }

                    // Join the elements with ", "
                    hobbies = String.join(", ", hobbiesList);
                    Intent i = new Intent(getApplicationContext()
                            , ResultFormActivity.class);

                    i.putExtra("username", username);
                    i.putExtra("password", password);
                    i.putExtra("birthdate", birthdate);
                    i.putExtra("gender", gender);
                    i.putExtra("hobbies", hobbies);
                    startActivity(i);
                }
            }
        });
    }

    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        //which checkbox was clicked
        if(view.getId() == R.id.tennis)
        {
            if(checked)
                tennisChecked = true;
            else
                tennisChecked = false;
        }
        if(view.getId() == R.id.football)
        {
            if (checked)
                footballChecked = true;
            else
                footballChecked = false;
        }
        if(view.getId() == R.id.others)
        {
            if(checked)
                othersChecked = true;
            else
                othersChecked = false;
        }
    }

    public boolean isValidDate(String dateStr)
    {
        // Split the string by "/"
        String[] parts = dateStr.split("/");

        // Must have exactly 3 parts: day, month, year
        if (parts.length != 3) {
            return false;
        }

        // Convert parts to integers
        int day, month, year;
        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false; // If not numbers, invalid date
        }

        // Validate year range
        if (year < 1 || year > 9999) {
            return false;
        }

        // Validate month range
        if (month < 1 || month > 12) {
            return false;
        }

        // Days in each month
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Leap year check
        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }

        // Validate day range
        return day >= 1 && day <= daysInMonth[month];
    }

    // Leap year check
    public boolean isLeapYear(int year)
    {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}


