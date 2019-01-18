package classes;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import exceptions.*;
import org.w3c.dom.Document;

public class OpenWeatherMap implements IWeatherDataService
{
    private WeatherData weatherData = new WeatherData();
    private static volatile OpenWeatherMap weatherMap;

    private OpenWeatherMap()
    {
    }

    public static OpenWeatherMap getInstance()
    {
        if (weatherMap == null)
        {
            weatherMap = new OpenWeatherMap();
        }

        return weatherMap;
    }

    @Override
    public WeatherData getWeatherData(Location location) throws WeatherDataServiceException
    {
        String city = location.getCity();
        String country = location.getCountry();

        try
        {
            URL url;
            String appID = "5fa7571c03889c663963c41593c4124d";
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units="
                    + location.getUnit() + "&mode=xml&APPID="+appID );

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            InputStream in = con.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);

            weatherData.setAllWeatherDataByTagNames(doc);
        }
        catch (Exception e)
        {
            throw new WeatherDataServiceException();

        }

        return weatherData;
    }
}
