package sample;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebWeather {

    public Weather getData(String city, String code2) {
      Weather weather = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "," + code2 + "&units=metric&&APPID=2ca495a00aa9654ffbc70b57017a9490");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output = br.readLine();
                System.out.println(output);
                System.out.println(getTemperatureFromJSON(output));
                System.out.println(getHumidityFromJSON(output));


                JSONObject jsonObject = new JSONObject(output);
                String name = jsonObject.getString("name");
                String country = jsonObject.getJSONObject("sys").getString("country");
                double temp = jsonObject.getJSONObject("main").getDouble("temp");
                int humidity = jsonObject.getJSONObject("main").getInt("humidity");
                double lon = jsonObject.getJSONObject("coord").getDouble("lon");
                double lat = jsonObject.getJSONObject("coord").getDouble("lat");
                weather = new Weather(name, country, temp, humidity, lon, lat);

                long sunrise = jsonObject.getJSONObject("sys").getLong("sunrise");
                long sunset = jsonObject.getJSONObject("sys").getLong("sunset");
                long visibility = jsonObject.getLong("visibility");
                return weather;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    public String getTemperatureFromJSON(String output) {
        String[] parts = output.split(":");
        String[] one= parts[11].split(",");
        return one[0];
    }

    public String getHumidityFromJSON(String output) {
        String[] parts = output.split(":");
        String[] one= parts[16].split(",");
        return one[0].substring(0,2);

    }
}