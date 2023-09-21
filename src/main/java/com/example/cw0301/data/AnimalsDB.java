package com.example.cw0301.data;

import com.example.cw0301.model.Animals;
import com.example.cw0301.model.Cat;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class AnimalsDB {
    private final String HOST = "217.151.230.118";
    private final String PORT = "3306";
    private final String DB_NAME = "humans_friends";
    private final String LOGIN = "new";
    private final String PASS = "password";

    private Connection dbConn = null;

    // Метод для подключения к БД с использованием значений выше
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public ArrayList<String> getNamesFromTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + tableName +";";
        ArrayList<String> names = new ArrayList<>();

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()) {
            names.add((res.getString("name")));
        }
        return names;
    }

    public ArrayList<String> getDescriptionFromTable(String tableName, int key) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + tableName +" WHERE id=" + Integer.toString(key) + ";";
        ArrayList<String> result = new ArrayList<>(3);

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        while(res.next()) {
            for (int i = 2; i < 5; i++)
                result.add(res.getString(i));
        }
        return result;
    }

    public void addNewCommand(String tableName, String command, int key)
            throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT commands FROM " + tableName + " WHERE id=" + Integer.toString(key) + ";";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql1);
        while (res.next()) {
            String existingCommands = res.getString("commands");
            if (existingCommands.contains(command))
                System.out.println("Уже знаю такую команду");
            else {
                if(!existingCommands.equals(""))
                    command = ", " + command;
                String sql2 = "UPDATE " + tableName + " SET commands = CONCAT(commands, '" + command +"')" +
                        " WHERE id=" + Integer.toString(key) + ";";
                PreparedStatement prSt = getDbConnection().prepareStatement(sql2);
                prSt.executeUpdate();
            }
        }
    }

    public void pushNewToBase(Animals newAnimal, String table) throws SQLException, ClassNotFoundException {
        if(newAnimal!=null) {
            String sql = "INSERT " + table + "(`name`, birth_day, commands) VALUES ('" + newAnimal.getName() +
                    "','" + newAnimal.getBirthDate() + "', '" + newAnimal.getCommands() + "');";
            System.out.println("Запрос: " + sql);
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.executeUpdate();
        }
    }
}
