package com.example.cw0301.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.cw0301.data.AnimalsDB;
import com.example.cw0301.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AnimalsController {



    @FXML
    private Button btAddNew;
    @FXML
    private Button btStudy;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private ComboBox<String> cbAnimalType;


    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private TextField tfCommands;

    @FXML
    private TextField tfName;

    @FXML
    private ListView<String> lvNames;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextField tfNewCommand;

    AnimalsDB db = null;
    Alert alertError = new Alert(Alert.AlertType.ERROR);
    Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    ObservableList<String> listOfNames = null;
    String currentTable = "cats";
    String tableForNewAnimal = "cats";
    Counter counter = new Counter();

    @FXML
    void initialize() {
        db = new AnimalsDB();
        alertError.setTitle("Ошибка");

        String[] petTypes = {"Кошка", "Собака", "Хомяк"};
        String[] packTypes = {"Лошадь", "Осёл", "Верблюд"};


        cbType.setValue("Кошка");
        cbAnimalType.setValue("Кошка");

        ObservableList<String> items1 = FXCollections.observableArrayList(petTypes);
        ObservableList<String> items2 = FXCollections.observableArrayList(packTypes);
        cbType.getItems().addAll(items1);
        cbType.getItems().addAll(items2);
        cbAnimalType.getItems().addAll(items1);
        cbAnimalType.getItems().addAll(items2);
        cbType.setValue(items1.get(0));
        lvNames.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        try {
            showMeTheListOfNames(cbType.getValue());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Обработчики

        cbType.setOnAction(e ->
            {
                try {
                    showMeTheListOfNames(cbType.getValue());
                } catch (SQLException ex) {
                    System.out.println("Ошибка базы данных");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Ошибка драйвера базы данных");
                }
            });

        lvNames.getSelectionModel().selectedItemProperty().addListener(
                ov -> {
                    try {
                        showMeDescription(lvNames.getSelectionModel().getSelectedIndex()+1);
                    } catch (SQLException e) {
                        System.out.println("Ошибка базы данных");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Ошибка драйвера базы данных");
                    }
                }
        );

        btStudy.setOnAction(e ->{
            int id = lvNames.getSelectionModel().getSelectedIndex();
            if(id != -1 && !tfNewCommand.getText().equals("")) {
                try {
                    db.addNewCommand(currentTable, tfNewCommand.getText(), id+1);
                    showMeDescription(id+1);
                    alertInfo.setContentText("Команда изучена и добавлена в список");
                    alertInfo.show();
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                alertError.setContentText("Не выбрано животное или команда пуста");
                alertError.show();
            }
            tfNewCommand.clear();
        });

        btAddNew.setOnAction(e ->{
            try {
                db.pushNewToBase(createNewAnimal(cbAnimalType.getValue()), tableForNewAnimal);
                showMeTheListOfNames(cbAnimalType.getValue());
                counter.add();
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tfName.clear();
            tfCommands.clear();
        });

        assert btAddNew != null : "fx:id=\"btAddNew\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert btStudy != null : "fx:id=\"btStudy\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert cbAnimalType != null : "fx:id=\"cbAnimalType\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert cbType != null : "fx:id=\"cbType\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert dpDateOfBirth != null : "fx:id=\"dpDateOfBirth\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert tfCommands != null : "fx:id=\"tfCommands\" was not injected: check your FXML file 'animals-view.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'animals-view.fxml'.";
    }

    void showMeTheListOfNames(String type) throws SQLException, ClassNotFoundException {
        switch (type) {
            case "Кошка":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("cats"));
                currentTable = "cats";
                break;
            case "Собака":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("dogs"));
                currentTable = "dogs";
                break;
            case "Хомяк":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("hamsters"));
                currentTable = "hamsters";
                break;
            case "Лошадь":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("horses"));
                currentTable = "horses";
                break;
            case "Осёл":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("donkeys"));
                currentTable = "donkeys";
                break;
            case "Верблюд":
                listOfNames = FXCollections.observableArrayList(db.getNamesFromTable("camels"));
                currentTable = "camels";
                break;

        }
        lvNames.setItems(listOfNames);
    }

    void showMeDescription(Integer id) throws SQLException, ClassNotFoundException {
        ArrayList<String> fields = db.getDescriptionFromTable(currentTable, id);
        StringBuilder text = new StringBuilder();

        if(taDescription!=null)
            taDescription.clear();

        for(String field : fields) {
            text.append(field).append("\n");
        }
        taDescription.setText(text.toString());
    }

    Animals createNewAnimal(String type) {
        String name;
        Calendar birthday = new GregorianCalendar();
        String knownCommands;
        Animals newAnimal = null;

        if(tfName.getText().equals("") || dpDateOfBirth.getValue() == null) {
            alertError.setContentText("Необходимо заполнить обязательные поля");
            alertError.show();
        }else {
            name = tfName.getText();
            birthday.set(dpDateOfBirth.getValue().getYear(),
                    dpDateOfBirth.getValue().getMonthValue(),
                    dpDateOfBirth.getValue().getDayOfMonth());
            knownCommands = tfCommands.getText();

            switch (type) {
                case "Кошка":
                    newAnimal = new Cat(name, birthday, knownCommands);
                    tableForNewAnimal = "cats";
                    break;
                case "Собака":
                    newAnimal = new Dog(name, birthday, knownCommands);
                    tableForNewAnimal = "dogs";
                    break;
                case "Хомяк":
                    newAnimal = new Hamster(name, birthday, knownCommands);
                    tableForNewAnimal = "hamsters";
                    break;
                case "Лошадь":
                    newAnimal = new Horse(name, birthday, knownCommands);
                    tableForNewAnimal = "horses";
                    break;
                case "Осёл":
                    newAnimal = new Donkey(name, birthday, knownCommands);
                    tableForNewAnimal = "donkeys";
                    break;
                case "Верблюд":
                    newAnimal = new Camel(name, birthday, knownCommands);
                    tableForNewAnimal = "camels";
                    break;
            }
        }
        return newAnimal;
    }
}
