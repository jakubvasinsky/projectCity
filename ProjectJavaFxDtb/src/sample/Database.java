package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public List getCountries(){
        List<String> list = new ArrayList<>();
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT Code, Name FROM country");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List getCities(String country){
        List<City> list = new ArrayList<>();
        try{
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT city.name, json_extract(info, '$.Population') AS Info, Code, Code2 FROM country JOIN city ON country.code = city.countrycode WHERE country.name LIKE ? ");
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("city.name");
                String code2 = rs.getString("Code2");
                String code3 = rs.getString("Code");
                int population = rs.getInt("Info");
                City city = new City(name, population, code3, code2,country);
                list.add(city);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x", "student", "kosice2019");
    }

    public String getPopulation(String city) {
        try {
            String populationString = "SELECT json_extract(Info, '$.Population') AS pop FROM city JOIN country ON country.code = city.countrycode WHERE city.Name LIKE ?";

            PreparedStatement statement = getConnection().prepareStatement(populationString);
            statement.setString(1,city);
            ResultSet rs = statement.executeQuery();
            String population;
            if (rs.next()) {
                population = (rs.getString(1));
                return population;
            }
            getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}