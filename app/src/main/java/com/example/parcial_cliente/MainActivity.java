package com.example.parcial_cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial_cliente.model.Particle;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements IObserver{

    private Communication com;
    private EditText groupNameTxt, amountTxt, xPosTxt, yPosTxt;
    private Button redBtn, greenBtn, blueBtn, createBtn, deleteBtn;
    private String groupName, amount, x, y, json;
    private Particle particleGroup;
    private int amountInt, xInt, yInt;
    private boolean colorSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elements
        groupNameTxt = findViewById(R.id.groupNameTxt);
        amountTxt = findViewById(R.id.amountTxt);
        xPosTxt = findViewById(R.id.xPosTxt);
        yPosTxt = findViewById(R.id.yPosTxt);
        redBtn = findViewById(R.id.redBtn);
        greenBtn = findViewById(R.id.greenBtn);
        blueBtn = findViewById(R.id.blueBtn);
        createBtn = findViewById(R.id.createBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        //Communication
        com = Communication.getInstance();
        com.setObserver(this);

        //GSON
        Gson gson = new Gson();
        particleGroup = new Particle();

        //Variables
        colorSelected = false;

        //BUTTONS
        //Set particle color
        redBtn.setOnClickListener((view)->{
                    colorSelected = true;
                    chooseColor(223, 20, 88);
                }
        );

        //Set particle color
        greenBtn.setOnClickListener((view)->{
                    colorSelected = true;
                    chooseColor(174, 241, 19);
                }
        );

        //Set particle color
        blueBtn.setOnClickListener((view)->{
                    colorSelected = true;
                    chooseColor(19, 225, 241);
                }
        );

        //Send message to create particle and GSON
        createBtn.setOnClickListener((view)->{
            variablesToValidate();

            //Make sure all the text boxes are full
            if (checkBoxes()){
                Toast.makeText(this, "Llenar todos los campos", Toast.LENGTH_SHORT).show();
            } else if (colorSelected == false) {
                Toast.makeText(this, "Escoge un color", Toast.LENGTH_SHORT).show();
            } else {
                //Turn variables into int for object
                amountInt = Integer.parseInt(amount);
                xInt = Integer.parseInt(x);
                yInt = Integer.parseInt(y);

                //Set variables in object
                particleGroup.setAmount(amountInt);
                particleGroup.setName(groupName);
                particleGroup.setxPos(xInt);
                particleGroup.setyPos(yInt);

                //Send json
                json = gson.toJson(particleGroup);
                com.sendMessage(json);

                //Empty boxes for next group
                blankBoxes();

                //Set boolean to false to select new color
                colorSelected = false;
            }
                }
        );

        //Just send message to delete list
        deleteBtn.setOnClickListener((view)->{
                    com.sendMessage("delete");
                }
        );
    }

    public void variablesToValidate(){
        //Add variables to the text to validate
        groupName = groupNameTxt.getText().toString();
        amount = amountTxt.getText().toString();
        x = xPosTxt.getText().toString();
        y = yPosTxt.getText().toString();
    }

    public boolean checkBoxes(){
        //Check each text box to make sure they're not empty
        if (groupName.isEmpty() && amount.isEmpty() && x.isEmpty() && y.isEmpty()){
            return true;
        }
        return false;
    }

    public void chooseColor(int r, int g, int b){
        particleGroup.setR(r);
        particleGroup.setG(g);
        particleGroup.setB(b);
    }

    public void blankBoxes(){
        groupNameTxt.getText().clear();
        amountTxt.getText().clear();
        xPosTxt.getText().clear();
        yPosTxt.getText().clear();
    }

    @Override
    public void notifyMessage(String message) {}
}