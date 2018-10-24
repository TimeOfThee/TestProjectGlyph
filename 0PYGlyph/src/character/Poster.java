package character;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Poster {
	
	private ArrayList<Letter> letters;
	private ArrayList<Letter> memory;
	private int life,opas,rate=4;
	private int[] loc;
	private Color fontClor;
	private float size;
	
	public Poster(float size,Color fontClor,int x,int y) {
		this.letters=new ArrayList<Letter>();
		this.memory=new ArrayList<Letter>();
		this.life=0;
		this.opas=100;
		this.size=size;
		this.fontClor=fontClor;
		this.loc=new int[] {x,y};
	}
	public void update() {
		fontClor=new Color(fontClor.getRed(),fontClor.getGreen(),fontClor.getBlue(),opas);
		float xs=loc[0]-(letters.size()*(size/2))+(size/2);
		for(int a=0;a<letters.size();a++) {
			letters.get(a).setColor(fontClor);
			letters.get(a).setLoc(new float[] {xs+(size*a),(int)(loc[1]+(size/2))});
		}
		
		if(life>0) {
			life-=rate;
			if(life<=0) {
				copy();
				clear();
				life=0;
			}
		}
		opas=life;
		if(opas>255)opas=255;
		
		/*if(opas>0) {
			opas-=rate;
			if(opas<0) {
				opas=0;
				for(int a=letters.size()-1;a>-1;a--) {
					letters.remove(a);
				}
			}
		}*/
	}
	public void render(Graphics g) {
		float xs=loc[0]-(letters.size()*(size/2));
		g.setColor(new Color(50,200,100,opas));
		g.drawRect((int)xs-1, loc[1]-1, (int)(letters.size()*size)+2, (int)size+2);
		
		for(Letter l:letters) {
			g.setColor(fontClor);
			l.render(g);
		}
	}
	public void enter(Letter image) {
		Letter l=new Letter(image);
		l.setColor(fontClor);
		l.setAngle(270);
		l.setSize(size/2);
		letters.add(l);
		wake();
	}
	public void backspace() {
		if(letters.size()>0) {
			letters.remove(letters.get(letters.size()-1));
			wake();
		}
	}
	public void wake() {
		life=420;
	}
	
	public void setLife(int to) {
		life=to;
	}
	
	public ArrayList<Letter> getLetters(){return this.letters;}
	public ArrayList<Letter> getMemory(){return this.memory;}
	public void clear() {
		for(int a=letters.size()-1;a>-1;a--) {
			letters.remove(a);
		}
	}
	public void copy() {
		if(letters.size()<=0)return;
		memory=new ArrayList<Letter>();
		for(Letter l: letters) {
			memory.add(new Letter(l));
		}
	}
}
