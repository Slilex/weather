package classes;

import exceptions.*;

public interface IWeatherDataService
{
    WeatherData getWeatherData(Location location) throws WeatherDataServiceException;
}
