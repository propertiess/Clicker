package com.example.homework;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController extends Thread {

    public static int level = 1;



    @FXML
    private Button buttonToStore;

    @FXML
    private ImageView CookieButton;

    @FXML
    private Text counterText;


    public static int counter_int = 0;
    boolean isActive = true;
    SQLiteConnection connection = new SQLiteConnection();


    @FXML
    void initialize() {
        loadGamer();
        if (level > 1) {
            start();


        }
        if(counter_int == 0) {
            SQLiteConnection connection = new SQLiteConnection();

            String money = counterText.getText();
            String gamerlevelAutoClick = level + "";
            String priceImprove = StoreController.price_int + "";
            Gamer gamer = new Gamer(money, gamerlevelAutoClick, "1", priceImprove);
            try {
                connection.newGamer(gamer);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        counterText.setText(counter_int + "");


        CookieButton.setOnMouseClicked(mouseEvent -> {
            clicker();
            reWriteGamer();


        });


        buttonToStore.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("store.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Store");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.initOwner(buttonToStore.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.showAndWait();


            StoreController controller = loader.getController();
            counterText.setText(controller.getText());
            if (level > 1) {
                start();

            }

        });


    }

    public void clicker() {

        counter_int++;
        counterText.setText("" + counter_int);

    }


    @Override
    public void run() {
        while (isActive) {
            counter_int++;
            if(level > 2){
                counter_int += 0.5;
            }
            counterText.setText("" + counter_int);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("ok");
            }

            if(!buttonToStore.getScene().getWindow().isShowing()){
                reWriteGamer();
                isActive = false;
            }



        }

    }
    protected void reWriteGamer(){
        String money = counterText.getText();
        String gamerlevelAutoClick = level + "";
        String priceImprove = StoreController.price_int + "";
        Gamer gamer = new Gamer(money, gamerlevelAutoClick, "1", priceImprove);

        connection.reWriteGamer(gamer);

    }
    private void loadGamer() {
        SQLiteConnection connection = new SQLiteConnection();

        ResultSet load = connection.getGamer("1");

        try {
            while (load.next()){
                Gamer gamer = new Gamer();
                gamer.setId(load.getString(1));

                gamer.setMoney(load.getString(2));
                counter_int = Integer.parseInt(gamer.getMoney());
                gamer.setGamerlevelAutoClick(load.getString(3));
                level = Integer.parseInt(gamer.getGamerlevelAutoClick());
                gamer.setPrice_improvement(load.getString(4));
                StoreController.price_int = Integer.parseInt(gamer.getPrice_improvement());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}