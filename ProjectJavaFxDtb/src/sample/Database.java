package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Database {
    public List getCountries() {
        try {

                Connection con = getConnection();
                PreparedStatement pstm = con.prepareStatement("SELECT Name FROM country");
                ResultSet rs = pstm.executeQuery();
                String country;
                List <String> list= new ArrayList<>();
                while (rs.next()){
                    country=rs.getString("name");
                    // System.out.println(country);
                    list.add(country);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://itsovy.sk:3306/world_x?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "kosice2019");
        return con;
    }
}