package model.Shapes;

import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;
import model.ShapeProperties.ShapeTypes;
import static model.ShapeProperties.ShapeTypes.LINE;
import java.util.Map;

public class Line extends AbstractShape
{
    private static final ShapeTypes shapeType = LINE;
    private ShapeDimensions dimensions;

    public Line(ShapeDimensions dimensions, Map<SettingNames, Object> settings)
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
        return "Line{" +
                "dimensions=" + dimensions +
                super.toString() +
                '}';
    }
}
