package weatherclasses;

public class Pressure
{
    private String value;
    private String unit;

    public Pressure(String value, String unit)
    {
        this.setValue(value);
        this.unit = "мм.рт.ст.";
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        double d = Double.parseDouble(value);
        this.value = String.valueOf( (int)(d/1.3332));
    }

    public String getUnit()
    {
        return unit;
    }

    @Override
    public String toString()
    {
        return value + " " + unit;
    }
}
