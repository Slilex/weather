package weatherclasses;

public class City
{
    private String id;
    private String name;
    private String country;
    private Coord coord;

    public City(String ID, String name)
    {
        setID(ID);
        setName(name);
    }

    public String getID()
    {
        return id;
    }

    public void setID(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String countryCode)
    {
        this.country = countryCode;
    }

    public Coord getCoordinates()
    {
        return coord;
    }

    public void setCoordinates(Coord coordinates)
    {
        this.coord = coordinates;
    }

    @Override
    public String toString()
    {
        return "ID=" + getID() + ", Город =" + getName() + ", код страны =" + getCountry() + ", координаты="
                + getCoordinates() ;
    }
}
