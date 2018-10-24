package note;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	
	public final float defNodeSiz=20;
	
	private int[] loc;
	private ArrayList<Node> connections;
	private boolean visible;
	private Color color;
	
	public Node(int x, int y) {
		this.loc=new int[] {x,y};
		this.connections=new ArrayList<Node>();
		this.visible=true;
		this.color=new Color(150,150,150);
	}
	public void update() {
		Iterator<Node> it= connections.iterator();
		while(it.hasNext()) {
			Node check=it.next();
			boolean is=false;
			for(Node n:check.getConnections()) {
				if(n==this)is=true;
			}
			if(!is)it.remove();
		}
	}
	public void render(Graphics g,int cx,int cy) {
		if(visible) {
			g.setColor(color);
			g.fillOval( (int)(loc[0]-(defNodeSiz/2)), (int)(loc[1]-(defNodeSiz/2)), (int)defNodeSiz, (int)defNodeSiz);
			g.setColor(color.darker());
			g.drawOval( (int)(loc[0]-(defNodeSiz/2)), (int)(loc[1]-(defNodeSiz/2)), (int)defNodeSiz, (int)defNodeSiz);
		}
	}
	
	public void moveTo(int x,int y) {
		this.loc=new int[] {x,y};
	}
	public int[] getLoc() {return loc;}
	public void addConnection(Node n) {
		for(Node check:connections) {
			if(check==n)return;
		}
		connections.add(n);
		n.addConn(this);
	}
	private void addConn(Node n) {
		for(Node check:connections) {
			if(check==n)return;
		}
		connections.add(n);
	}
	public void removeConnection(Node n) {
		connections.remove(n);
		n.removeConn(this);
	}
	private void removeConn(Node n) {
		connections.remove(n);
	}
	public ArrayList<Node> getConnections(){return this.connections;}
	public boolean isVisible() {return this.visible;}
	public void setVisible(boolean to) {this.visible=to;}
	public Color getColor() {return this.color;}
	public void setColor(Color to) {this.color=to;}
}
