package com.example.homework;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StoreController {




    @FXML
    private ImageView backToMain;

    @FXML
    private Button buttonToBuy;

    @FXML
    private Label counterTwo;

    @FXML
    private Label price;
    public static int price_int = 30;


    @FXML
    void initialize() {
        price.setText("" + price_int);
        counterTwo.setText("" + MainController.counter_int);
        buttonToBuy.setOnAction(actionEvent -> {
            if (MainController.counter_int >= price_int) levelUp();


        });

        backToMain.setOnMouseClicked(mouseEvent -> backToMain.getScene().getWindow().hide());

    }
    @FXML
    void levelUp() {
        MainController.counter_int -= price_int;
        counterTwo.setText("" + MainController.counter_int);
        price_int *= 1.5;
        price.setText("" + price_int);
        MainController.level++;
    }


}