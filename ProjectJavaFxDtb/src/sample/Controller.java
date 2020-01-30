
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
    List countries;
    List cities;

    public Controller() {
        Database db = new Database();
        countries = db.getCountries();
    }
    public void initialize(){
        combo2.setDisable(true);
        okButton.setDisable(true);
    }

    public void populateCB1(){
        Database db = new Database();
        countries = db.getCountries();
        combo1.getItems().setAll(countries);
    }

    public String getcombobox1Value(){
        if (combo1.getValue()==null){
            combo2.setDisable(true);
        }else{
            combo2.setDisable(false);
        }
        return combo1.getValue();
    }

    public String getcombobox2Value(){
        if (combo2.getValue()==null){
            okButton.setDisable(true);
        }else{
            okButton.setDisable(false);
        }
        return combo2.getValue();
    }

    public void populateCB2(){
        Database db = new Database();
        List<City> city;
        city = db.getCities(getcombobox1Value());
        combo2.getItems().clear();
        for(City s: city){
            combo2.getItems().add(s.getName());
        }
    }

    public void getInfo(){

        String city = (String) combo2.getValue();
        System.out.println(city);

        try {
            String population = new Database().getPopulation((String) combo2.getValue());
            lblPopulation.setText("Population: " + population);
            System.out.println(population);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblCity.setText("City: "+combo2.getValue());
        lblCountry.setText("Country: "+combo1.getValue());
        System.out.println(combo1.getValue());
        System.out.println(combo2.getValue());
    }

    private String formatPopString(int population) {
        String data = String.valueOf(population);
        DecimalFormat formatter = new DecimalFormat("#,###");
        String yourFormattedString = formatter.format(population);
        return yourFormattedString;
    }
}





