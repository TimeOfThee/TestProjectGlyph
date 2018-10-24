package character;

public class Ray {

	private double angle;
	private float distance;
	private boolean link;
	
	public Ray(double angle,float distance,boolean link) {
		this.angle=angle;
		this.distance=distance;
		this.link=link;
	}
	public void setAngle(double to) {this.angle=to;}
	public void setDistance(float to) {this.distance=to;}
	public double getAngle() {return this.angle;}
	public float getDistance() {return distance;}
	public void setLink(boolean to) {this.link=to;}
	public boolean isLink() {return link;}
	
}
