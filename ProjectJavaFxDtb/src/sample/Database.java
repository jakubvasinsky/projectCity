package sample;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String JDBC = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://itsovy.sk:3306/world_x?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connection;


    public Connection getConnection() throws Exception {
        Class.forName(JDBC);
        connection = DriverManager.getConnection(URL, "student", "kosice2019");
        return connection;
    }
    public List getCountries() throws Exception {
        String countries = "SELECT * FROM country";

        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(countries);
        ResultSet rs = ps.executeQuery();
        String country;
        List<String> list = new ArrayList<>();
        while(rs.next()) {
            country = (rs.getString("Code") + "   " + rs.getString("Name"));
            list.add(country);

        }
        connection.close();
        return list;
    }
    public List getCity(String country) throws Exception {
        try {
            String countries = "SELECT city.name FROM country JOIN city ON country.code = city.countrycode where country.name like ? ";

            PreparedStatement statement = getConnection().prepareStatement(countries);
            statement.setString(1,country);
            ResultSet rs = statement.executeQuery();
            String city;
            List<String> list = new ArrayList();
            while (rs.next()) {
                country = (rs.getString("Name"));
                list.add(country);
            }
            connection.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}