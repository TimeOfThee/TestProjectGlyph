package character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Calculator;
import main.Handler;
import main.KeyManager;
import main.MouseManager;

public class Rune {

	private Letter[] letters;
	private int select=0,flood=4;
	private double angle=0,target=0;
	private float[] loc;
	private float size;
	private double shift;
	private Color fontClor;
	private Letter[] trail;
	
	private Poster poster;
	private boolean replay;
	
	private Handler ha;
	private KeyManager km;
	//private static ArrayList<KeyEvent> last=new ArrayList<KeyEvent>();
	private ArrayList<String> last;
	
	private float spd=0,mspd=1,acc=3;
	
	int tar=0,delay=0;
	boolean tog=true;
	
	int trailL=5;
	
	public Rune(Handler ha,float x, float y,float size,Color fontClor) {
		this.ha=ha;
		this.km=ha.getKM();
		this.angle=0;
		this.loc=new float[] {x,y};
		this.size=size;
		this.fontClor=fontClor;
		
		float size2=size/30;
		this.letters=new Letter[26];
		this.letters[0] =new Letter("a",0,0,0,size2,fontClor);
		this.letters[1] =new Letter("b",0,0,0,size2,fontClor);
		this.letters[2] =new Letter("c",0,0,0,size2,fontClor);
		this.letters[3] =new Letter("d",0,0,0,size2,fontClor);
		this.letters[4] =new Letter("e",0,0,0,size2,fontClor);
		this.letters[5] =new Letter("f",0,0,0,size2,fontClor);
		this.letters[6] =new Letter("g",0,0,0,size2,fontClor);
		this.letters[7] =new Letter("h",0,0,0,size2,fontClor);
		this.letters[8] =new Letter("i",0,0,0,size2,fontClor);
		this.letters[9] =new Letter("j",0,0,0,size2,fontClor);
		this.letters[10] =new Letter("k",0,0,0,size2,fontClor);
		this.letters[11] =new Letter("l",0,0,0,size2,fontClor);
		this.letters[12] =new Letter("m",0,0,0,size2,fontClor);
		this.letters[13] =new Letter("n",0,0,0,size2,fontClor);
		this.letters[14] =new Letter("o",0,0,0,size2,fontClor);
		this.letters[15] =new Letter("p",0,0,0,size2,fontClor);
		this.letters[16] =new Letter("q",0,0,0,size2,fontClor);
		this.letters[17] =new Letter("r",0,0,0,size2,fontClor);
		this.letters[18] =new Letter("s",0,0,0,size2,fontClor);
		this.letters[19] =new Letter("t",0,0,0,size2,fontClor);
		this.letters[20] =new Letter("u",0,0,0,size2,fontClor);
		this.letters[21] =new Letter("v",0,0,0,size2,fontClor);
		this.letters[22] =new Letter("w",0,0,0,size2,fontClor);
		this.letters[23] =new Letter("x",0,0,0,size2,fontClor);
		this.letters[24] =new Letter("y",0,0,0,size2,fontClor);
		this.letters[25] =new Letter("z",0,0,0,size2,fontClor);
		
		this.shift=letters.length-0.001;

		this.poster=new Poster(50, fontClor,(int)loc[0],(int)(loc[1]-(size/2)-50-10));
		this.last=new ArrayList<String>();
		this.replay=false;
		newTrail(this.letters[0]);
	}public Rune(Handler ha,float x, float y,float size,Letter[] letters) {
		this.ha=ha;
		this.angle=0;
		this.loc=new float[] {x,y};
		this.size=size;
		this.fontClor=fontClor;
		
		float size2=size/30;
		this.letters=letters;
		
		this.shift=letters.length-0.001;
		
		this.poster=new Poster(100, fontClor,(int)loc[0],(int)(loc[1]-(size/2)-(50)));
		this.replay=false;
		newTrail(this.letters[0]);
	}
	public void update() {

		flood=4;
		 
		if(last.size()>=7) {
			flood=2;
		}else if(last.size()>=4) {
			flood=3;
		}
		
		if(last.size()>0) {
			if(delay==0) {
				turnTo(last.get(0));
				if(atTarget(true)) {
					last.remove(0);
					delay=0;
				}
			}
			else delay--;
		}
		else { 
			delay=0;
			if(replay) {
				replay();
			}
		}
		
		rotate( Calculator.findDif(angle, target)/flood );
		
		rotate(spd);
		
		setSelect();
		if(trail[0].getID().equals(letters[select].getID())) {
			trail();
		}else {
			newTrail(this.letters[select]);
		}
		
		double ang=270;
		for(int a=0;a<letters.length;a++) {
			
			letters[a].setLoc( new float[] {(int)(loc[0]+Calculator.findCosSin(ang+angle, size*0.45)[0]), (int)(loc[1]+Calculator.findCosSin(ang+angle, size*0.45)[1])} );
			letters[a].setAngle(ang+angle);
			
			int red=letters[a].getColor().getRed(),green=letters[a].getColor().getGreen(),blue=letters[a].getColor().getBlue();
			if(red>fontClor.getRed()) {
				red-=10;
			}
			if(green>fontClor.getGreen()) {
				green-=10;
			}
			if(blue>fontClor.getBlue()) {
				blue-=10;
			}
			letters[a].setColor(new Color(red,green,blue));
			
			ang+=360/shift;
		}
		letters[select].setColor(letters[select].getColor().brighter());
		
		poster.update();
	}
	public void render(Graphics g) {
		
		double ang=270;
		for(int a=0;a<letters.length;a++) {

			letters[a].render(g);
			
			g.setColor(fontClor.darker());
			g.drawLine((int)loc[0], (int)loc[1], (int)(loc[0]+Calculator.findCosSin(ang+angle+((360/shift)/2), size/2)[0]), (int)(loc[1]+Calculator.findCosSin(ang+angle+((360/shift)/2), size/2)[1]));
			
			ang+=360/shift;
		}
		
		g.setColor(fontClor);
		g.drawLine((int)loc[0], (int)loc[1], (int)(loc[0]+Calculator.findCosSin( -(((360/shift)/2)+90), size/2)[0] ), (int)(loc[1]+Calculator.findCosSin( -(((360/shift)/2)+90), size/2)[1] ));
		g.drawLine((int)loc[0], (int)loc[1], (int)(loc[0]+Calculator.findCosSin( ((360/shift)/2)-90, size/2)[0] ), (int)(loc[1]+Calculator.findCosSin( ((360/shift)/2)-90, size/2)[1] ));
		
		g.setColor(new Color(0,225,225,100));
		g.fillArc((int)(loc[0]-(size/2)), (int)(loc[1]-(size/2)), (int)size, (int)size, -(int)((360/letters.length+1)/2)+90, (int)(360/letters.length+1));
		g.fillOval((int)(loc[0]-(size*0.4)), (int)(loc[1]-(size*0.4)), (int)(size*0.8), (int)(size*0.8));
		
		g.setColor(new Color(0,80,230));
		g.fillOval((int)(loc[0]-(size*0.2)), (int)(loc[1]-(size*0.2)), (int)(size*0.4), (int)(size*0.4));
		
		g.setColor(Color.blue);
		g.drawOval((int)(loc[0]-(size/2)), (int)(loc[1]-(size/2)), (int)size, (int)size);
		g.drawOval((int)(loc[0]-(size*0.4)), (int)(loc[1]-(size*0.4)), (int)(size*0.8), (int)(size*0.8));
		g.drawOval((int)(loc[0]-(size*0.2)), (int)(loc[1]-(size*0.2)), (int)(size*0.4), (int)(size*0.4));
		
		//g.setColor(Color.red);
		//g.drawLine((int)loc[0], (int)loc[1], (int)(loc[0]+Calculator.findCosSin(target-90, 300)[0]), (int)(loc[1]+Calculator.findCosSin(target-90, 300)[1]));
		poster.render(g);
		
		for(Letter l:trail) {
			l.render(g);
		}
	}
	public void rotate(double by) {
		this.angle+=by;
		if(angle>360)angle-=360;
		else if(angle<0)angle+=360;
	}
	public Letter[] getLetters() {return this.letters;}
	public void turnTo(int to) {
		if(to<0 || to>letters.length-1)return;
		
		target=-((360/shift)*to);

		if(target>360)target-=360;
		else if(target<0)target+=360;
	}
	public void turnTo(KeyEvent e) {
		if(e.getKeyCode()<65 || e.getKeyCode()>90)return;
		
		for(int a=0;a<letters.length;a++) {
			if( letters[a].getID().toUpperCase() .equals( KeyEvent.getKeyText(e.getKeyCode()) ) ) {
				turnTo(a);
			}
		}
	}
	public void turnTo(String to) {
		if(to.length()>1)return;
		for(int a=0;a<letters.length;a++) {
			if(letters[a].getID().equals(to)) {
				turnTo(a);
			}
		}
	}
	public void setLastInput(KeyEvent e) {
		last.add(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase());
	}
	private boolean atTarget(boolean send) {
		if(Math.abs((int)Calculator.findDif(angle, target)/5) <1) {
			if(send && last.size()>0) {
				if(last.get(0).equals("backspace"))poster.backspace();
				else if(last.get(0).equals("enter") || last.get(0).equals("shift"));
				else poster.enter(new Letter(last.get(0),0,0,0,0f,fontClor));
			}
			return true;
		}
		return false;
	}
	private void setSelect() {
		select=(letters.length-1-(int)( (letters.length+1)*(target*100/360)/100 ))+1;
		if(select>=letters.length)select-=letters.length;
		//System.out.println(select);
	}
	public Poster getPoster() {return this.poster;}
	public void newTrail(Letter root) {
		this.trail=new Letter[trailL];
		for(int a=0;a<trailL;a++) {
			this.trail[a]=new Letter(root);
			int opas=root.getColor().getAlpha()-(a*20);
			if(opas<0)opas=0;
			this.trail[a].setColor(new Color(this.trail[a].getColor().getRed(),this.trail[a].getColor().getGreen(),this.trail[a].getColor().getBlue(),opas));
		}
	}
	public void trailClorFix() {
		for(int a=0;a<trailL;a++) {
			trail[a].setColor(letters[select].getColor());
			int opas=letters[select].getColor().getAlpha();
			opas-=(a*80);
			if(opas<0)opas=0;
			trail[a].setOpas(opas);
		}
	}
	public void trail() {
		trailClorFix();
		Letter[] t=this.trail;
		for(int a=t.length-1;a>0;a--) {
			trail[a].setLoc(trail[a-1].getLoc().clone());
			trail[a].setAngle(trail[a-1].getAngle());
		}
		trail[0].setLoc(letters[select].getLoc().clone());
		trail[0].setAngle(letters[select].getAngle());
	}
	public void pushReplay() {
		replay=true;
	}
	private void replay() {
		poster.copy();
		poster.clear();
		for(Letter l:poster.getMemory()) {
			last.add(l.getID());
		}
		replay=false;
	}
}
