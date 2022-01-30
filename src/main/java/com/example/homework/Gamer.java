package com.example.homework;

public class Gamer {

    private String money;
    private String gamerlevelAutoClick;
    private String id;
    private String price_improvement;

    public String getPrice_improvement() {
        return price_improvement;
    }

    public void setPrice_improvement(String price_improvement) {
        this.price_improvement = price_improvement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGamerlevelAutoClick() {
        return gamerlevelAutoClick;
    }

    public void setGamerlevelAutoClick(String gamerlevelAutoClick) {
        this.gamerlevelAutoClick = gamerlevelAutoClick;
    }
    public Gamer(){

    }
    public Gamer(String money, String gamerlevelAutoClick, String id, String price_improvement) {
        this.money = money;
        this.gamerlevelAutoClick = gamerlevelAutoClick;
        this.id = id;
        this.price_improvement = price_improvement;

    }
}
