package edu.eci.arsw.blueprints.filter.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filter.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("subsamplingFilter")
public class SubSamplingBlueFilter implements BluePrintFilter {
    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        Point[] newPoints = new Point[points.size()/2];

        int count = 0;
        for (int i = 1 ; i< points.size(); i+=2){
            newPoints[count]= points.get(i);
            count++;
        }

        return new Blueprint(blueprint.getAuthor(),blueprint.getName(),newPoints);
    }

}
