package classes;

import weatherclasses.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

public class WeatherData
{
    private City city;
    private Direction direction;
    private Humidity humidity;
    private Pressure pressure;
    private Temperature temperature;
    private Wind wind;
    private Speed speed;
    private Date date = new Date();

    private void setAttribute(String currentTagName, Element rootElement)
    {
        NodeList currentList = rootElement.getElementsByTagName(currentTagName);
        Node currentNode = currentList.item(0);
        Element currentElement = (Element) currentNode;

        switch (currentTagName)
        {
            case "city":
                city = new City(currentElement.getAttribute("id"), currentElement.getAttribute("name"));
                break;
            case "coord":
                Coord coord = new Coord(currentElement.getAttribute("lon"), currentElement.getAttribute("lat"));
                city.setCoordinates(coord);
                break;
            case "country":
                city.setCountry(currentElement.getTextContent());
                break;
            case "temperature":
                temperature = new Temperature(currentElement.getAttribute("value"), currentElement.getAttribute("min"),
                        currentElement.getAttribute("max"), currentElement.getAttribute("unit"));
                break;
            case "humidity":
                humidity = new Humidity(currentElement.getAttribute("value"), currentElement.getAttribute("unit"));
                break;
            case "pressure":
                pressure = new Pressure(currentElement.getAttribute("value"), currentElement.getAttribute("unit"));
                break;
            case "wind":
                wind = new Wind();
                break;
            case "speed":
                Speed speed = new Speed(currentElement.getAttribute("value"), currentElement.getAttribute("name"));
                wind.setSpeed(speed);
                break;
            case "direction":
                Direction direction = new Direction(currentElement.getAttribute("value"),
                        currentElement.getAttribute("code"), currentElement.getAttribute("name"));
                wind.setDirection(direction);
                break;
        }
    }

    public void setAllWeatherDataByTagNames(Document doc)
    {
        NodeList list = doc.getElementsByTagName("current");
        Node rootNode = list.item(0);
        Element rootElement = (Element) rootNode;

        setAttribute("city", rootElement);
        setAttribute("coord", rootElement);
        setAttribute("country", rootElement);
        setAttribute("temperature", rootElement);
        setAttribute("humidity", rootElement);
        setAttribute("pressure", rootElement);
        setAttribute("wind", rootElement);
        setAttribute("speed", rootElement);
        setAttribute("direction", rootElement);
        setAttribute("precipitation", rootElement);
    }


    public City getCity() {
        return city;
    }

    public Direction getDirection() {
        return direction;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public Speed getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return String.format(
                "Дата " + date + "%n"+
                        "Город =" + city +  "%n"+
                        "Температура="  + temperature +  "%n" +
                        "Влажность =" + humidity + " %n" +
                        "Давление =" + pressure +  "%n" +
                        "Ветер=" + wind +  "%n"+
                "%n"+"%n"+"- - - - - - - - - - - - - - - - - - - - -  - - - -- - "+"%n"+"%n"
        );
    }
}
