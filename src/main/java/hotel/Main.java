package hotel;

import db.persistance.DataBase;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DataBase db = new DataBase();
        db.connect();
        db.disconnect();
    }
}
