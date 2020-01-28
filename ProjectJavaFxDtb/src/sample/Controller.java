package sample;


import javafx.event.Event;
import javafx.scene.control.ComboBox;

import java.util.List;

public class Controller {

    List countries;
    List cities;
    String country;

    public ComboBox cmbCountry;
    public ComboBox cmbCity;


    public Controller() {
        Database database = new Database();
        try {
            countries = database.getCountries();
            cities = database.getCity(country);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clickcmbCountry(Event event) {
        cmbCountry.getItems().setAll(countries);
    }

    public void clickcmbCity(Event event) {
        country = (String) cmbCountry.getValue();
        System.out.println(country);

        try {
            cities = new Database().getCity(country);
            cmbCity.getItems().setAll(cities);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}