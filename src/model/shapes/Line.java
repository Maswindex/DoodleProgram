/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds the line object
 */
package model.shapes;

import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeDimensions;
import model.shapeproperties.ShapeTypes;
import static model.shapeproperties.ShapeTypes.LINE;
import java.util.Map;

/**
 * defines line shape object
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class Line extends AbstractShape
{
    private static final ShapeTypes shapeType = LINE;
    private ShapeDimensions dimensions;

    /**
     * Line constructor
     *
     * @param dimensions shape dimensions
     * @param settings settings from toolbar
     */
    public Line(ShapeDimensions dimensions, Map<SettingNames, Object> settings)
    {
        initializeSettings(settings);
        this.dimensions = dimensions;
    }

    @Override
    public ShapeDimensions getDimensions()
    {
        return dimensions;
    }

    /**
     * @return shapeType
     */
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
