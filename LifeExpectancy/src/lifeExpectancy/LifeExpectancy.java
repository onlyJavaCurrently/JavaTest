/**
 * 
 */
package lifeExpectancy;

import java.util.HashMap;
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

	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		
		String[] rows = loadStrings(fileName);
		
		return lifeExpMap;
	}
	
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
