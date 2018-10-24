package note;

public class Textbox {

	private int[] loc,siz;
	private String text;
	private boolean visible, active;
	
	public Textbox(int x,int y,int sx, int sy) {
		this.loc=new int[] {x,y};
		this.siz=new int[] {sx,sy};
		this.text="";
		this.visible=true;
		this.active=false;
	}
	
	public int[] getLoc() {return this.loc;}
	public void setLoc(int x, int y) {this.loc=new int[] {x,y};}
	public int[] getSiz() {return this.siz;}
	public void setSiz(int sx, int sy) {this.siz=new int[] {sx,sy};}
	public String getTest() {return this.text;}
	public void setText() {}
	
}
