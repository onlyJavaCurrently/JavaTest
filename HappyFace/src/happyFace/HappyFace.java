/**
 * Painting happy faces
 */
package happyFace;

import processing.core.PApplet;

/**
 * @author KamilWo
 *
 */
public class HappyFace extends PApplet{
	
	public void setup() {
		size(500, 500);
		background(150, 150, 150);
	}
	
	public void draw() {
		fill(40, 40, 200);
		ellipse(250, 250, 220, 220);
		fill(255, 255, 255);
		ellipse(210, 210, 30, 25);
		ellipse(290, 210, 30, 25);
		fill(255, 40, 40);
		arc(250, 275, 140, 110, 0, PI);
	}
}
