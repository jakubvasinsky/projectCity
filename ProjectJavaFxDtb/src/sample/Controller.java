
package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.List;

public class Controller {
    public ComboBox<String> combo1;
    public ComboBox<String> combo2;
    public Button okButton;
    public Label population;
    public Label lblCity;
    public Label lblCountry;
    public Label lblPopulation;
    public Label lblTemp;
    public Label lblHum;
    public Label lblDis;
    List countries;
    List<City> cities;

    public Controller() {
        Database db = new Database();
        countries = db.getCountries();
    }

    public void initialize() {
        combo2.setDisable(true);
        okButton.setDisable(true);
    }

    public void populateCB1() {
        Database db = new Database();
        countries = db.getCountries();
        combo1.getItems().setAll(countries);
    }

    public String getcombobox1Value() {
        if (combo1.getValue() == null) {
            combo2.setDisable(true);
        } else {
            combo2.setDisable(false);
        }
        return combo1.getValue();
    }

    public String getcombobox2Value() {
        if (combo2.getValue() == null) {
            okButton.setDisable(true);
        } else {
            okButton.setDisable(false);
        }
        return combo2.getValue();
    }

    public void populateCB2() {
        Database db = new Database();
        List<City> cities;
        cities = db.getCities(getcombobox1Value());
        combo2.getItems().clear();
        for (City c : cities) {
            combo2.getItems().add(c.getName());
        }
    }

    public void getInfo() {

        String cityString = combo2.getValue();
        System.out.println(cityString);
        City city = null;
        for (City c : cities){
            if (c.getName().equals(cityString)){
                city = c;
                break;
            }
        }
        if (city == null)
            return;

        population.setText("Population: "+formatPopString(city.getPopulation()));
        lblDis.setText("District: "+city.getThreeCode());

        Weather weather = new WebWeather().getData(city.getName(), city.getTwoCode());
        lblCity.setText("Name: "+String.valueOf(weather.getName()));
        lblCountry.setText("Country: "+String.valueOf(weather.getCountry()));
        lblTemp.setText("Temperature: "+ String.valueOf(weather.getTemp())+"°C");
        lblHum.setText("Humidity: "+formatPopString(weather.getHumidity())+"%");



    }
    private String formatPopString(int population){
        String data = String.valueOf(population);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(population);

    }
}





