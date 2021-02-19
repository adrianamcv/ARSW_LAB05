package edu.eci.arsw.blueprints.filter.impl;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filter.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;

@Component("redundancyFilter")
public class RedundancyBlueFilter implements BluePrintFilter {
    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<String> rPoints = new ArrayList<>();
        Blueprint bluePrint = null;

        List<Point> points = blueprint.getPoints();
        Point[] pointsN = new Point[points.size()];

        int count = 0 ;
        Point[] newPoints = new Point[count];

        for ( Point p : points){
            if (!(rPoints.contains(p.getX()+" "+p.getY()))){
                rPoints.add(p.getX()+ " "+p.getY());
                pointsN[count] = new Point(p.getX(),p.getY());
                count++;
            }
        }

        for (int i = 0 ; i < count ; i++){
            newPoints[i] = pointsN[i];
        }

        bluePrint = new Blueprint(blueprint.getAuthor(),blueprint.getName(),newPoints);
        return null;
    }

}
