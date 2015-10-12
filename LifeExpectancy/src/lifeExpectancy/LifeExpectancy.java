/**
 * Applet shows Life Expectancy from World Bank
 * Lib dependencies:
 *    tested on jdk1.8.0_60, jre1.8.0_60
 *    core.jar  (processing.core)
 *    gluegen-rt-natives-windows-amd64.jar
 *    gluegen-rt.jar
 *    jogl-all-natives-windows-amd64.jar
 *    jogl-all.jar
 *    json4processing.jar
 *    log4j-1.2.15.jar
 *    unfolding.0.9.7-uscd.jar
 */
package lifeExpectancy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
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
    
    Map<String, Float> lifeExpMap;
    
    List<Feature> countries;
    List<Marker> countryMarkers;

    /**
     * Loading Life Expectancy from CSV file
     * @param fileName - name of the CSV file
     * @return Map<String, Float> lifeExpMap
     */
    private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
        lifeExpMap = new HashMap<String, Float>();
        
        String[] rows = loadStrings(fileName);
        
        for(String row: rows) {
            String[] columns = row.split(",");
            System.out.println(columns[5]);
            if(columns.length > 0 && columns[5].matches("[0-9]+(.[0-9]*)?")) {
                System.out.println(columns[4]);
                float value = Float.parseFloat(columns[5]);
                lifeExpMap.put(columns[4], value);            
            }
        }
        
        return lifeExpMap;
    }
    
    /**
     * Changing colours of countries depending on Life Expectancy
     */
    private void shadeCountries() {
        
        for(Marker marker: countryMarkers) {
            String countryId = marker.getId();
            
            if(lifeExpMap.containsKey(countryId)) {
                float lifeExp = lifeExpMap.get(countryId);
                int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
                marker.setColor(color(255 - colorLevel, 100, colorLevel));
            } else {
                marker.setColor(color(150, 150, 150));
            }
        }
    }
    
    /* (non-Javadoc)
     * @see processing.core.PApplet#setup()
     */
    public void setup() {
        size(1200, 800, OPENGL);
        map = new UnfoldingMap(this, 50, 50, 1100, 700, 
                new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        
        lifeExpByCountry = loadLifeExpectancyFromCSV
                ("../data/LifeExpectancyWorldBank.csv");
        
        countries = GeoJSONReader.loadData(this, "../data/countries.geo.json");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        System.out.println(countryMarkers);
        map.addMarkers(countryMarkers);
        shadeCountries();
    }

    /* (non-Javadoc)
     * @see processing.core.PApplet#draw()
     */
    public void draw() {
        map.draw();
    }
}
