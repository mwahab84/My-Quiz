package com.example.android.myquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Global boolean variables that hold question 2 checkboxes' answers
     * Question 2- What are the main resources of income of Qatar? (Choose all that apply)
     * 1- hasAgri for Agriculture choice
     * 2- hasGas for Gas choice
     * 3- hasPetro for Petroleum choice
     */
    boolean hasAgri,hasGas,hasPetro=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Create a method that shows the final quiz result upon clicking
     * "Get My Results!" button
     * @param view that's associated with the Button fires the method
     */
    public void showResult(View view){
        //The text view that holds the result grade of the quiz taker
        TextView result_text_view = (TextView)findViewById(R.id.text_view_result);
        //The view that accepts the quiz taker name
        EditText test_taker_name_edit_text = (EditText)findViewById(R.id.s_name);
        //A String variable to hold the quiz taker name if inserted and empty string if not supplied
        String test_taker_name = test_taker_name_edit_text.getText().toString().trim();
        //An integer variable that calls the calcResult method which returns the quiz result for a test taker
        int result = calcResult();
        //Update the result text view so that it show the quiz take name with its result
        //if no name is supplied, it will show "No Name" with the result
        result_text_view.setText(test_taker_name.length()>0?test_taker_name+" : "+result
                :getString(R.string.no_name)+" : "+result);
        //Create a Toast message to show the quiz taker name with its result
        Toast.makeText(getApplicationContext(),getString(R.string.toast_result,test_taker_name)+
                        " "+getString(R.string.is)+" : "+result,Toast.LENGTH_SHORT).show();
    }

    /**
     * Create a method that checks the quiz taker answers and then calculate the result grade
     * I've evaluated the questions' answers with 5-points bases for each correct answer
     * @return the quiz result is an integer to be shown to the quiz taker
     */
    private int calcResult(){
        //An integer variable initialized to 0 and will hold the result value as it's updated
        int result = 0;
        //A RadioGroup object that groups question 1 answers listed as radio options
        //Question 1- In which continent does state of Qatar reside?
        RadioGroup rGroup = (RadioGroup)findViewById(R.id.r_group);
        //The view that accept the open answer text for question 3
        //Question 3- Write down the city name colored in red on the map below.
        EditText cityName = (EditText)findViewById(R.id.city_name);
        //The view that accept the written answer in digits for question 4
        //Question 4- When did Qatar achieved its independence?
        EditText indYear = (EditText)findViewById(R.id.ind_year);

        //Check whether the selected option is "Asia" (Question 1) so the result will be incremented with 5 points
        //if not, no changes will be applied to the result
        if(rGroup.getCheckedRadioButtonId() == R.id.rdo_asia){
            result+=5;
        }

        //Check that the quiz taker has checked only "Gas" and "Petroleum" answers (Question 2) so that
        //the result will be incremented with 5 points
        if (hasGas && hasPetro && !hasAgri)
            result += 5;

        //Check if the answer text contains the city name "doha" (Question 3) so that
        //the result will be incremented with 5 points
        if(cityName.getText().toString().toLowerCase().contains("doha"))
            result += 5;

        //Check if the answer text contains the year "1971" (Question 4) so that
        //the result will be incremented with 5 points
        if(indYear.getText().toString().contains("1971"))
            result += 5;

        //return the final result
        return result;
    }

    /**
     * Create a void method that listens to checkboxes click
     * @param view that's associated with the CheckBox fires the method
     */
    public void onCheckBoxClicked(View view){
        //Create a generic CheckBox object named chk
        CheckBox chk;
        //Get the Id of the CheckBox that fired the method and then check if it's
        //one of the 3 possible answers for question 2
        switch (view.getId()){
            //If the CheckBox has the text Agriculture then
            //assign it to the object chk and then assign its checked state to the hasAgri variable
            //then stop running the method
            case R.id.chk_agri:
                chk = (CheckBox)findViewById(R.id.chk_agri);
                hasAgri=chk.isChecked();
                break;
            //If the CheckBox has the text Gas then
            //assign it to the object chk and then assign its checked state to the hasGas variable
            //then stop running the method
            case R.id.chk_gas:
                chk = (CheckBox)findViewById(R.id.chk_gas);
                hasGas=chk.isChecked();
                break;
            //If the CheckBox has the text Petroleum then
            //assign it to the object chk and then assign its checked state to the hasPetro variable
            //then stop running the method
            case R.id.chk_petro:
                chk = (CheckBox)findViewById(R.id.chk_petro);
                hasPetro=chk.isChecked();
                break;
        }
    }
}
