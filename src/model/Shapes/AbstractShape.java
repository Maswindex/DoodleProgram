package model.Shapes;

import javafx.scene.paint.Color;
import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;
import model.ShapeProperties.ShapeTypes;

import java.util.Map;

import static model.ShapeProperties.SettingNames.*;

public abstract class AbstractShape
{
    private Color fillColor, strokeColor;
    private double strokeWidth;
    private boolean filled;
    private ShapeTypes type;
    private ShapeDimensions dimensions;

    public abstract ShapeDimensions getDimensions();

    public boolean isFilled()
    {
        return filled;
    }

    public Color getFillColor()
    {
        return fillColor;
    }

    public Color getStrokeColor()
    {
        return strokeColor;
    }

    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    public ShapeTypes getType()
    {
        return type;
    }

    protected void initializeSettings(Map<SettingNames, Object> settings)
    {
        fillColor = (Color)settings.get(FILLCOLOR);
        strokeColor = (Color)settings.get(STROKECOLOR);
        strokeWidth = (double)settings.get(STROKEWIDTH);
        filled = (boolean)settings.get(FILLED);
        type = (ShapeTypes)settings.get(SHAPE);
    }

    @Override
    public String toString()
    {
        return "AbstractShape{" +
                "fillColor=" + fillColor +
                ", strokeColor=" + strokeColor +
                ", strokeWidth=" + strokeWidth +
                ", filled=" + filled +
                '}';
    }
}
