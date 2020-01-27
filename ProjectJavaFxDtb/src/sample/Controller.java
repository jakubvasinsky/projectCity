package sample;


import javafx.event.Event;
import javafx.scene.control.ComboBox;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    public ComboBox combo1;
    List<String> countries;
    public Controller() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        countries = database.getCountries();

    }

    public void showcountries(Event event) {
        Database database = new Database();
        combo1.getItems().setAll(database.getCountries());
    }
    }
