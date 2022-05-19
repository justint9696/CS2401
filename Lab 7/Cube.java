public class Cube {
	public double length, width, height, volume;

	public Cube() {}

	public Cube(double l, double w, double h) {
		length = l;
		width = w;
		height = h;
	}
	
	public void setLength(double l) {
		length = l;
	}
	
	public void setWidth(double w) {
		width = w;
	}
	
	public void setHeight(double h) {
		height = h;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getVolume() {
		volume = (length * width * height);
		return volume;
	}
}
