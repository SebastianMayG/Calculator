package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    double firstnum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button AC = findViewById(R.id.AC);
        Button DEL = findViewById(R.id.DEL);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button times = findViewById(R.id.times);
        Button div = findViewById(R.id.div);
        Button equal = findViewById(R.id.equal);
        Button point = findViewById(R.id.point);

        TextView Screen = findViewById(R.id.Screen);

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for(Button b : nums)
        {
            b.setOnClickListener(view ->
            {
                if(!Screen.getText().toString().equals("0"))
                {
                    Screen.setText(Screen.getText().toString() + b.getText().toString());
                } else
                {
                    Screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> opers = new ArrayList<>();
        opers.add(AC);
        opers.add(DEL);
        opers.add(plus);
        opers.add(minus);
        opers.add(times);
        opers.add(div);
        opers.add(point);
        opers.add(equal);

        for(Button b : opers)
        {
            b.setOnClickListener(view ->
            {
                firstnum = Double.parseDouble(Screen.getText().toString());
                operation = b.getText().toString();
                Screen.setText("0");
            });
        }

        AC.setOnClickListener(view ->
                {
                    firstnum = 0;
                    Screen.setText("0");
                });

        DEL.setOnClickListener(view ->
        {
            String num = Screen.getText().toString();
            if(num.length() > 1)
            {
                Screen.setText(num.substring(0,num.length()-1));
            } else if(num.length() == 1 && !num.equals("0"))
            {
                Screen.setText("0");
            }
        });

        point.setOnClickListener(view ->
        {
            if(!Screen.getText().toString().contains("."))
            {
                Screen.setText(Screen.getText().toString() + ".");
            }
        });

        equal.setOnClickListener(view ->
        {
            double secondNum = Double.parseDouble(Screen.getText().toString());
            double result;
            switch (operation)
            {
                case "+":
                    result = firstnum + secondNum;
                    break;
                case "-":
                    result = firstnum - secondNum;
                    break;
                case "x":
                    result = firstnum * secondNum;
                    break;
                case "/":
                    result = firstnum / secondNum;
                    break;
                default:
                    result = firstnum + secondNum;
            }

            Screen.setText(String.valueOf(result));
            firstnum = result;
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}