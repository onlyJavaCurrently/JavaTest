/**
 * 
 */
package lifeExpectancy;

import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/**
 * @author KamilWo
 *
 */
public class LifeExpectancy extends PApplet {
	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry; 

    public void setup() {
    	size(800, 600, OPENGL);
    	map = new UnfoldingMap(this, 50, 50, 700, 500, 
    			new Google.GoogleMapProvider());
    	MapUtils.createDefaultEventDispatcher(this, map);
    	
    	lifeExpByCountry = loadLifeExpectancyFromCSV
    			("data/LifeExpectancyWorldBank.csv");
    }

    public void draw() {
		map.draw();
    }
}
