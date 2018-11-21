package model.Shapes;

import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;
import model.ShapeProperties.ShapeTypes;
import static model.ShapeProperties.ShapeTypes.RECTANGLE;
import java.util.Map;

public class Rectangle extends AbstractShape
{
    private static final ShapeTypes shapeType = RECTANGLE;
    private ShapeDimensions dimensions;

    public Rectangle(ShapeDimensions dimensions, Map<SettingNames, Object> settings)
    {
        initializeSettings(settings);
        this.dimensions = dimensions;
    }

    public ShapeDimensions getDimensions()
    {
        return dimensions;
    }

    public static ShapeTypes getShapeType()
    {
        return shapeType;
    }

    @Override
    public String toString()
    {
        return "Rectangle{" +
                "dimensions=" + dimensions +
                super.toString() +
                '}';
    }
}