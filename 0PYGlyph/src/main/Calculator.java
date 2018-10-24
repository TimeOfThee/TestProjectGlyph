package main;
public class Calculator {
	
	public static double findHypotenuse(float xs,float ys,float xe,float ye) {
		return Math.sqrt( (xe-xs)*(xe-xs)+(ye-ys)*(ye-ys) );
	}
	public static double findAngle(float xs,float ys,float xe,float ye) {
		double ang;
		if(xe-xs!=0) {
			ang= Math.toDegrees( Math.atan((ye-ys)/(xe-xs)) );
			if(xe<xs) {ang+=180;}
		}
		else {
			if(ye-ys<0)ang=270;else ang=90;
		}
		return ang;
	}
	public static double findDif(double angle,double angt) {
		double dif1=0,dif2=0;
		
		dif1=angt-angle;
		dif2=dif1-(360*(dif1/Math.abs(dif1)));
		
		if(dif1>360) dif1-=360;
		else if(dif1<-360)dif1+=360;
		
		if(dif2>360) dif2-=360;
		else if(dif2<-360)dif2+=360;
		
		if(Math.abs(dif2)<Math.abs(dif1)) {
			return dif2;
		}else {
			return dif1;
		}
	}
	/**
	 * 
	 * @param ang le to move by
	 * @param dis tance to move
	 * @return a point moved DIS away at an angle ANG along the [x axis,y axis]
	 */
	public static double[] findCosSin(double ang,double dis) {
		double cos=Math.cos( Math.toRadians(ang) )*dis;
		double sin=Math.sin( Math.toRadians(ang) )*dis;
		return new double[] {cos,sin};
	}
	public static boolean intersects(float[] l1,float[] l2) {
		
		if((l1[0] > l2[0] && 
			l1[0] > l2[2] && 
			l1[2] > l2[0] && 
			l1[2] > l2[2])
				||
		   (l1[0] < l2[0] && 
			l1[0] < l2[2] && 
			l1[2] < l2[0] && 
			l1[2] < l2[2])) {
			return false;
		}
		if((l1[1] > l2[1] && 
			l1[1] > l2[3] && 
			l1[3] > l2[1] && 
			l1[3] > l2[3])
				||
		   (l1[1] < l2[1] && 
			l1[1] < l2[3] && 
			l1[3] < l2[1] && 
			l1[3] < l2[3])) {
			return false;	
		}
		double sl1=(findCosSin(findAngle(l1[0],l1[1],l1[2],l1[3]),1)[1])
				/(findCosSin(findAngle(l1[0],l1[1],l1[2],l1[3]),1)[0])
			,sl2=(findCosSin(findAngle(l2[0],l2[1],l2[2],l2[3]),1)[1])
				/(findCosSin(findAngle(l2[0],l2[1],l2[2],l2[3]),1)[0]);
		
		double ix=( (l2[1] -sl2*l2[0]) - (l1[1] -sl1*l1[0]) ) / ( sl1-sl2 );
		double iy=sl1*ix+l1[1]-(sl1*l1[0]);
		
		if(isBetween(ix,l2[0],l2[2]) && isBetween(iy,l2[1],l2[3]) && isBetween(ix,l1[0],l1[2]) && isBetween(iy,l1[1],l1[3])) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean isBetween(double look,double start,double end) {
		if(start<=end) {
			if(look>=start-0.1 && look<=end+0.1)return true;
			return false;
		}
		else {
			if(look>=end-0.1 && look<=start+0.1)return true;
			return false;
		}
	}
}
