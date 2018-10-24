package main;

import character.Rune;
import character.RuneDisplay;

public class Handler {

	private Plan plan;
	
	public Handler(Plan plan) {
		this.plan=plan;
	}
	public KeyManager getKM() {return plan.getKM();}
	public MouseManager getMM() {return plan.getMM();}
	public Plan getPlan() {return plan;}
	
	public RuneDisplay[] getGlyphs() {return plan.getGlyphs();}
}
