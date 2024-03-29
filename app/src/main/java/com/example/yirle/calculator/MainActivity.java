package com.example.yirle.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private Button[] buttons = new Button[17];
    private Double operand1 = null;
    private static final String TAG = "MainActivity";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        initListeners();
    }

    //for saving vars in bundle when Actitivy gets destoryed
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        operandDisplay.setText(pendingOperation);
    }

    //load bundle when recreated
    @Override
    protected void onSaveInstanceState(Bundle b) {
        b.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            b.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(b);
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
        buttons[16] = findViewById(R.id.buttonNeg);


        newNumber = findViewById(R.id.newNumber);
        result = findViewById(R.id.result);
        operandDisplay = findViewById(R.id.operandDisplay);
    }

    //init ClickListeners
    public void initListeners() {
        //when clicked add Number (buttontext) to newNumber(displays the users input)
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnPressed = (Button) v;
                newNumber.append(btnPressed.getText().toString());
            }
        };
        //when clicked set operandDisplay(displays the users choice of diff calcs.) to the chosen operand(buttontext)
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btnPressed = (Button) v;
                String op = btnPressed.getText().toString();
                try {
                    Double value = Double.valueOf(newNumber.getText().toString());
                    performCalculation(value, op);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "onClick: wrong inputformat");
                    newNumber.setText("");
                }
                pendingOperation = op;
                operandDisplay.setText(pendingOperation);
            }
        };
        //when clicked neg number
        View.OnClickListener negListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if (value.length() == 0) {
                    newNumber.setText("-");
                } else {
                    try {
                        Double dValue = Double.valueOf(value);
                        dValue *= -1;
                        newNumber.setText(dValue.toString());
                    } catch (NumberFormatException e) {
                        newNumber.setText("");
                    }
                }
            }
        };
        //set 'listener' on button 0-9 and buttonDot
        for (int i = 0; i <= 10; i++) {
            buttons[i].setOnClickListener(listener);
        }
        //set 'opListener' on button 11-15(+, -, *, /, =)
        for (int i = 11; i <= 15; i++) {
            buttons[i].setOnClickListener(opListener);
        }
        //set 'negListener' on button neg
        buttons[16].setOnClickListener(negListener);
    }

    //calculate method
    private void performCalculation(Double value, String op) {
        if (null == operand1) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "+":
                    operand1 += value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "x":
                    operand1 *= value;
                    break;
                case "/":
                    if (value == 0.0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "=":
                    operand1 = value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
