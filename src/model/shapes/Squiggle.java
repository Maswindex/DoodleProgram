/*
 *      author: Mason Hernandez
 *      author: Zachary Rosenlund
 *     version: 1.0
 *     created: 11/15/18
 * last edited: 11/29/18
 *
 * This file holds the squiggle shape object
 */

package model.shapes;

import model.Point;
import model.shapeproperties.SettingNames;
import model.shapeproperties.ShapeDimensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * defines the squiggle shape object
 *
 * @author Zachary Rosenlund
 * @author Mason Hernandez
 * @version 1.0
 */
public class Squiggle extends AbstractShape
{
    private double[] xCoordinates, yCoordinates;

    /**
     * Squiggle constructor
     *
     * @param xCoordinates graphicscontext point
     * @param yCoordinates graphicscontext point
     * @param settings settings from toolbar
     */
    public Squiggle(ArrayList<Double> xCoordinates, ArrayList<Double> yCoordinates,
                    Map<SettingNames, Object> settings)
    {
        this.xCoordinates = new double[xCoordinates.size()];
        this.yCoordinates = new double[yCoordinates.size()];
        for (int i = 0; i < this.xCoordinates.length ; i++)
        {
            this.xCoordinates[i] = xCoordinates.get(i);
            this.yCoordinates[i] = yCoordinates.get(i);
        }
        initializeSettings(settings);
    }

    /**
     * @return xCoordinates
     */
    public double[] getxCoordinates()
    {
        return xCoordinates;
    }

    /**
     * @return yCoordinates
     */
    public double[] getyCoordinates()
    {
        return yCoordinates;
    }

    @Override
    public ShapeDimensions getDimensions()
    {
        return new ShapeDimensions(
                new Point(xCoordinates[0], yCoordinates[0]),
                new Point(xCoordinates[xCoordinates.length-1],
                          yCoordinates[yCoordinates.length-1]
                ));
    }

    @Override
    public String toString()
    {
        return "Squiggle{" +
                "xCoordinates=" + Arrays.toString(xCoordinates) +
                ", yCoordinates=" + Arrays.toString(yCoordinates) + ", " +
                super.toString() +
                '}';
    }
}
