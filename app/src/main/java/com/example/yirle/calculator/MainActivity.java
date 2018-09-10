package com.example.yirle.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //vars
    private EditText newNumber;
    private EditText result;
    private TextView operandDisplay;
    private String pendingOperation = "=";
    private View.OnClickListener listener;
    private View.OnClickListener opListener;
    private Button[] buttons = new Button[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initListeners();
    }

    //linking all vars to the XML vars including the buttonarray used to store all buttons the calculator provides
    public void initVars() {
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);
        buttons[10] = findViewById(R.id.buttonDot);
        buttons[11] = findViewById(R.id.buttonMulti);
        buttons[12] = findViewById(R.id.buttonDiv);
        buttons[13] = findViewById(R.id.buttonMinus);
        buttons[14] = findViewById(R.id.buttonPlus);
        buttons[15] = findViewById(R.id.buttonEquals);

        newNumber = findViewById(R.id.newNumber);
        result = findViewById(R.id.result);
        operandDisplay = findViewById(R.id.operandDisplay);
    }

    //init ClickListeners
    public void initListeners() {
        //when clicked add Number (buttontext) to newNumber(displays the users input)
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnPressed = (Button) v;
                newNumber.append(btnPressed.getText().toString());
            }
        };
        //when clicked set operandDisplay(displays the users choice of diff calcs.) to the chosen operand(buttontext)
        opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnPressed = (Button) v;
                operandDisplay.setText(btnPressed.getText().toString());
            }
        };
        buttons[2].setOnClickListener(listener);
        //set 'listener' on button 0-9 and buttonDot
        for (int i = 0; i <= 10; i++) {
            buttons[i].setOnClickListener(listener);
        }
        //set 'opListener' on button 11-15(+, -, *, /, =)
        for (int i = 11; i <= 15; i++) {
            buttons[i].setOnClickListener(opListener);
        }
    }
}
