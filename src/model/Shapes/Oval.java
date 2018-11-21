package model.Shapes;

import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;
import model.ShapeProperties.ShapeTypes;
import static model.ShapeProperties.ShapeTypes.OVAL;
import java.util.Map;

public class Oval extends AbstractShape
{
    private static final ShapeTypes shapeType = OVAL;
    private ShapeDimensions dimensions;

    public Oval(ShapeDimensions dimensions, Map<SettingNames, Object> settings)
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
        return "Oval{" +
                "dimensions=" + dimensions +
                super.toString() +
                '}';
    }
}
