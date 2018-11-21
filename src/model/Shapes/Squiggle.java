package model.Shapes;

import model.ShapeProperties.SettingNames;
import model.ShapeProperties.ShapeDimensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Squiggle extends AbstractShape
{
    private double[] xCoordinates, yCoordinates;

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

    public double[] getxCoordinates()
    {
        return xCoordinates;
    }

    public double[] getyCoordinates()
    {
        return yCoordinates;
    }

    @Override
    public ShapeDimensions getDimensions()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "Squiggle{" +
                "xCoordinates=" + Arrays.toString(xCoordinates) +
                ", yCoordinates=" + Arrays.toString(yCoordinates) +
                super.toString() +
                '}';
    }
}
