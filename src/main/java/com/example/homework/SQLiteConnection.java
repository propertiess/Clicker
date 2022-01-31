package com.example.homework;

import java.sql.*;

public class SQLiteConnection {



    public static Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db");
        System.out.println("sqlite on!");
        return conn;

    }


    public  void newGamer(Gamer gamer) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO main . gamer (" + Const.GAMER_ID + "," +
                Const.GAMER_MONEY + "," + Const.GAMER_LEVEL_AUTO_CLICK + ", "
                + Const.GAMER_PRICE_IMPROVE + ")" + " VALUES(?,?,?,?)";

        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1, gamer.getId());
        statement.setString(2, gamer.getMoney());
        statement.setString(3, gamer.getGamerlevelAutoClick());
        statement.setString(4, gamer.getPrice_improvement());
        statement.executeUpdate();

        System.out.println("Таблица заполнена");
    }

    public void reWriteGamer(Gamer gamer) {
        String reWrite = "UPDATE main . gamer "  + "SET " +
                Const.GAMER_MONEY + "= ? ," + Const.GAMER_LEVEL_AUTO_CLICK +
                " = ? ," + Const.GAMER_PRICE_IMPROVE + " = ? WHERE ("
                 + Const.GAMER_ID + " =? )";


        //       UPDATE `clicker`.`gamer` SET `money` = '65665', `gamerlevelAutoClick` = '333' WHERE (`idgamer` = '1');
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(reWrite);

            prSt.setString(1, gamer.getMoney());
            prSt.setString(2,gamer.getGamerlevelAutoClick());
            prSt.setString(3,gamer.getPrice_improvement());
            prSt.setString(4,gamer.getId());


            prSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLEreWriteGamer");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundreWriteGamer");
        }

    }

    public ResultSet getGamer(String id){
        ResultSet resSet = null;
        String select = "SELECT * FROM main . gamer WHERE " +
                Const.GAMER_ID + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, id);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQLgetGamer");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundgetGamer");

        }
        return resSet;
    }
}

