package dk.dtu.f21_02327;

import java.sql.*;



class Connector {

        final String HOST     = "localhost";
        final int    PORT     = 3306;
        final String DATABASE = "examproject";
        final String USERNAME = "root";
        final String PASSWORD = "****";
        String cp = "utf8";
        final String DELIMITER = ";;";

        Connection connection;

        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?characterEncoding=" + cp;

        Connector(){
            try{
                //Class.forName("java.sql.DriverManager");
                connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
                //Statement stmt = connection.createStatement();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

}
