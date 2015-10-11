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
		ellipse(50, 100, 75, 75);
	}
}
