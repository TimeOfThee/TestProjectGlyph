package character;

import java.awt.Color;
import java.awt.Graphics;

import main.Calculator;

public class Letter {

	private Ray[] points;
	private double angle;
	private float size;
	private float[] loc;
	private String id;
	private Color clor;
	
	public Letter(String id,float x, float y, double angle, float size,Color clor) {
		this.id=id;
		this.loc=new float[] {x,y};
		this.angle=angle;
		this.size=size;
		this.clor=clor;
		
		this.points=setPoints(id);
	}
	public Letter(Letter l) {
		this.id=l.getID();
		this.loc=l.getLoc().clone();
		this.angle=l.getAngle();
		this.size=l.getSize();
		this.clor=l.getColor();
		
		this.points=setPoints(id);
	}
	public void render(Graphics g){
		g.setColor(clor);
		//g.drawOval((int)(loc[0]-size), (int)(loc[1]-size), (int)(size*2), (int)(size*2));
		for(int a=0;a<points.length-1;a++) {
			if(points[a].isLink()) {
				g.drawLine( (int)(loc[0]+Calculator.findCosSin(angle+points[a].getAngle(), points[a].getDistance())[0]), 
								(int)(loc[1]+Calculator.findCosSin(angle+points[a].getAngle(), points[a].getDistance())[1]), 
								(int)(loc[0]+Calculator.findCosSin(angle+points[a+1].getAngle(), points[a+1].getDistance())[0]),
								(int)(loc[1]+Calculator.findCosSin(angle+points[a+1].getAngle(), points[a+1].getDistance())[1]) );
			}
		}
		if(points[points.length-1].isLink()) {
			g.drawLine( (int)(loc[0]+Calculator.findCosSin(angle+points[points.length-1].getAngle(), points[points.length-1].getDistance())[0]), 
					(int)(loc[1]+Calculator.findCosSin(angle+points[points.length-1].getAngle(), points[points.length-1].getDistance())[1]), 
					(int)(loc[0]+Calculator.findCosSin(angle+points[0].getAngle(), points[0].getDistance())[0]),
					(int)(loc[1]+Calculator.findCosSin(angle+points[0].getAngle(), points[0].getDistance())[1]) );
		}
	}
	
	public Ray[] setPoints(String let) {
		Ray[] ret;
		
		switch(let) {
		case "a":
			ret=new Ray[5];
			ret[0]=new Ray(-140,size,true);
			ret[1]=new Ray(0,size,true);
			ret[2]=new Ray(140,size,false);
			ret[3]=new Ray(-95,size*0.38f,true);
			ret[4]=new Ray(95,size*0.38f,false);
			break;
		case "b":
			ret=new Ray[9];
			ret[0]=new Ray(-150,size,true);
			ret[1]=new Ray(-30,size,true);
			ret[2]=new Ray(20,size*0.93f,true);
			ret[3]=new Ray(35,size*0.8f,true);
			ret[4]=new Ray(90,size*0.45f,true);
			ret[5]=new Ray(-90,size*0.45f,true);
			ret[6]=new Ray(90,size*0.45f,true);
			ret[7]=new Ray(145,size*0.8f,true);
			ret[8]=new Ray(160,size*0.93f,true);
			break;
		case "c":
			ret=new Ray[6];
			ret[0]=new Ray(30,size,true);
			ret[1]=new Ray(-20,size*0.93f,true);
			ret[2]=new Ray(-35,size*0.8f,true);
			ret[3]=new Ray(-145,size*0.8f,true);
			ret[4]=new Ray(-160,size*0.93f,true);
			ret[5]=new Ray(150,size,false);
			break;
		case "d":
			ret=new Ray[6];
			ret[0]=new Ray(-150,size,true);
			ret[1]=new Ray(-30,size,true);
			ret[2]=new Ray(20,size*0.93f,true);
			ret[3]=new Ray(35,size*0.8f,true);
			ret[4]=new Ray(145,size*0.8f,true);
			ret[5]=new Ray(160,size*0.93f,true);
			break;
		case "e":
			ret=new Ray[6];
			ret[0]=new Ray(150,size,true);
			ret[1]=new Ray(-150,size,true);
			ret[2]=new Ray(-30,size,true);
			ret[3]=new Ray(30,size,false);
			ret[4]=new Ray(-90,size*0.5f,true);
			ret[5]=new Ray(90,size*0.3f,false);
			break;
		case "f":
			ret=new Ray[5];
			ret[0]=new Ray(-150,size,true);
			ret[1]=new Ray(-30,size,true);
			ret[2]=new Ray(30,size,false);
			ret[3]=new Ray(-90,size*0.45f,true);
			ret[4]=new Ray(90,size*0.3f,false);
			break;
		case "g":
			ret=new Ray[8];
			ret[0]=new Ray(30,size,true);
			ret[1]=new Ray(-20,size*0.93f,true);
			ret[2]=new Ray(-35,size*0.8f,true);
			ret[3]=new Ray(-145,size*0.8f,true);
			ret[4]=new Ray(-160,size*0.93f,true);
			ret[5]=new Ray(150,size,true);
			ret[6]=new Ray(90,size*0.5f,true);
			ret[7]=new Ray(-90,0,false);
			break;
		case "h":
			ret=new Ray[6];
			ret[0]=new Ray(-30,size,true);
			ret[1]=new Ray(-150,size,false);
			ret[2]=new Ray(30,size,true);
			ret[3]=new Ray(150,size,false);
			ret[4]=new Ray(-90,size*0.5f,true);
			ret[5]=new Ray(90,size*0.5f,false);
			break;
		case "i":
			ret=new Ray[6];
			ret[0]=new Ray(-30,size,true);
			ret[1]=new Ray(30,size,false);
			ret[2]=new Ray(-150,size,true);
			ret[3]=new Ray(150,size,false);
			ret[4]=new Ray(0,size*0.87f,true);
			ret[5]=new Ray(180,size*0.87f,false);
			break;
		case "j":
			ret=new Ray[6];
			ret[0]=new Ray(-30,size,true);
			ret[1]=new Ray(30,size,false);
			ret[2]=new Ray(15,size*0.9f,true);
			ret[3]=new Ray(160,size*0.7f,true);
			ret[4]=new Ray(180,size*0.87f,true);
			ret[5]=new Ray(-150,size,false);
			break;
		case "k":
			ret=new Ray[5];
			ret[0]=new Ray(-160,size,true);
			ret[1]=new Ray(-20,size,false);
			ret[2]=new Ray(30,size,true);
			ret[3]=new Ray(-90,size*0.35f,true);
			ret[4]=new Ray(150,size,false);
			break;
		case "l":
			ret=new Ray[3];
			ret[0]=new Ray(-20,size,true);
			ret[1]=new Ray(-160,size,true);
			ret[2]=new Ray(160,size,false);
			break;
		case "m":
			ret=new Ray[5];
			ret[0]=new Ray(-150,size,true);
			ret[1]=new Ray(-30,size,true);
			ret[2]=new Ray(180,size*0,true);
			ret[3]=new Ray(30,size,true);
			ret[4]=new Ray(150,size,false);
			break;
		case "n":
			ret=new Ray[4];
			ret[0]=new Ray(-150,size,true);
			ret[1]=new Ray(-30,size,true);
			ret[2]=new Ray(150,size,true);
			ret[3]=new Ray(30,size,false);
			break;
		case "o":
			ret=new Ray[8];
			ret[0]=new Ray(-20,size*0.93f,true);
			ret[1]=new Ray(-35,size*0.8f,true);
			ret[2]=new Ray(-145,size*0.8f,true);
			ret[3]=new Ray(-160,size*0.93f,true);
			ret[4]=new Ray(160,size*0.93f,true);
			ret[5]=new Ray(145,size*0.8f,true);
			ret[6]=new Ray(35,size*0.8f,true);
			ret[7]=new Ray(20,size*0.93f,true);
			break;
		case "p":
			ret=new Ray[6];
			ret[0]=new Ray(-160,size,true);
			ret[1]=new Ray(-20,size,true);
			ret[2]=new Ray(20,size,true);
			ret[3]=new Ray(35,size*0.9f,true);
			ret[4]=new Ray(78,size*0.52f,true);
			ret[5]=new Ray(-70,size*0.35f,false);
			break;
		case "q":
			ret=new Ray[11];
			ret[0]=new Ray(-20,size*0.93f,true);
			ret[1]=new Ray(-35,size*0.8f,true);
			ret[2]=new Ray(-145,size*0.8f,true);
			ret[3]=new Ray(-160,size*0.93f,true);
			ret[4]=new Ray(170,size*0.88f,true);
			ret[5]=new Ray(130,size*0.6f,true);
			ret[6]=new Ray(35,size*0.8f,true);
			ret[7]=new Ray(20,size*0.93f,true);
			ret[8]=new Ray(-20,size*0.93f,false);
			ret[9]=new Ray(160,size,true);
			ret[10]=new Ray(160,size*0.5f,false);
			break;
		case "r":
			ret=new Ray[7];
			ret[0]=new Ray(-160,size,true);
			ret[1]=new Ray(-20,size,true);
			ret[2]=new Ray(20,size,true);
			ret[3]=new Ray(35,size*0.9f,true);
			ret[4]=new Ray(78,size*0.52f,true);
			ret[5]=new Ray(-70,size*0.35f,true);
			ret[6]=new Ray(150,size,false);
			break;
		case "s":
			ret=new Ray[8];
			ret[0]=new Ray(20,size,true);
			ret[1]=new Ray(-10,size*0.95f,true);
			ret[2]=new Ray(-30,size*0.8f,true);
			ret[3]=new Ray(-90,size*0.4f,true);
			ret[4]=new Ray(90,size*0.4f,true);
			ret[5]=new Ray(150,size*0.8f,true);
			ret[6]=new Ray(170,size*0.95f,true);
			ret[7]=new Ray(-160,size,false);
			break;
		case "t":
			ret=new Ray[4];
			ret[0]=new Ray(-40,size,true);
			ret[1]=new Ray(40,size,false);
			ret[2]=new Ray(0,size*0.78f,true);
			ret[3]=new Ray(180,size,false);
			break;
		case "u":
			ret=new Ray[6];
			ret[0]=new Ray(-27,size,true);
			ret[1]=new Ray(-145,size*0.8f,true);
			ret[2]=new Ray(-160,size*0.93f,true);
			ret[3]=new Ray(160,size*0.93f,true);
			ret[4]=new Ray(145,size*0.8f,true);
			ret[5]=new Ray(27,size,false);
			break;
		case "v":
			ret=new Ray[3];
			ret[0]=new Ray(-40,size,true);
			ret[1]=new Ray(180,size,true);
			ret[2]=new Ray(40,size,false);
			break;
		case "w":
			ret=new Ray[5];
			ret[0]=new Ray(-40,size,true);
			ret[1]=new Ray(-160,size,true);
			ret[2]=new Ray(0,size*0.5f,true);
			ret[3]=new Ray(160,size,true);
			ret[4]=new Ray(40,size,false);
			break;
		case "x":
			ret=new Ray[4];
			ret[0]=new Ray(-30,size,true);
			ret[1]=new Ray(150,size,false);
			ret[2]=new Ray(30,size,true);
			ret[3]=new Ray(-150,size,false);
			break;
		case "y":
			ret=new Ray[5];
			ret[0]=new Ray(-40,size,true);
			ret[1]=new Ray(180,size*0,true);
			ret[2]=new Ray(40,size,false);
			ret[3]=new Ray(180,size*0,true);
			ret[4]=new Ray(180,size,false);
			break;
		case "z":
			ret=new Ray[6];
			ret[0]=new Ray(-30,size,true);
			ret[1]=new Ray(30,size,true);
			ret[2]=new Ray(-150,size,true);
			ret[3]=new Ray(150,size,false);
			ret[4]=new Ray(-90,size*0.3f,true);
			ret[5]=new Ray(90,size*0.3f,false);
			break;
		/*case "z":
			break;*/
		default:
				ret=new Ray[2];
				ret[0]=new Ray(-90,size/2,true);
				ret[1]=new Ray(90,size/2,true);
		}
		
		return ret;
	}
	
	public void setAngle(double to) {this.angle=to;}
	public void addAngle(double by) {
		this.angle+=by;
		if(angle>=360) angle-=360;
		else if(angle<0)angle+=360;
	}
	public double getAngle() {return this.angle;}
	public void setLoc(float[] to) {this.loc=to;}
	public float[] getLoc() {return this.loc;}
	public Color getColor() {return this.clor;}
	public void setColor(Color to) {this.clor=to;}
	public void setOpas(int to) {
		this.clor=new Color( this.clor.getRed(),this.clor.getGreen(),this.clor.getBlue(),to );
	}
	public void setSize(float to) {
		this.size=to;
		this.points=setPoints(getID());
	}
	public String getID() {return this.id;}
	public float getSize() {return this.size;}
}
