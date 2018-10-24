package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import character.Letter;
import character.Poster;
import character.Rune;
import character.RuneDisplay;

public class Plan {
	
	private KeyManager kM;
	private MouseManager mM;
	private Handler ha;
	
	//put variables here
	RuneDisplay[] glyphs;
	boolean[] hide;
	Letter test=new Letter("a",400,450, 0, 10, Color.black);
	
	public Plan(KeyManager km,MouseManager mm) {
		this.ha=new Handler(this);
		this.kM=km;
		this.kM.setHandler(ha);
		this.mM=mm;
		glyphs=new RuneDisplay[1];
		hide=new boolean[1];
		glyphs[0]=new RuneDisplay(ha,600,450,300,new Color(200,200,200));
	}
	
	public void update() {
		//update variables here
		for(RuneDisplay r:glyphs) {
			r.update();
		}
		
		test.setAngle(glyphs[0].getLetters()[0].getAngle());
	}
	public void render(Graphics g) {
		//draw here
		for(int a=0;a<glyphs.length;a++) {
			if(!hide[a])glyphs[a].render(g);
		}
	}
	public KeyManager getKM() {return this.kM;}
	public MouseManager getMM() {return this.mM;}
	
	public RuneDisplay[] getGlyphs() {return glyphs;}
}
