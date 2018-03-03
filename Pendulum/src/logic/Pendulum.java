package logic;

public class Pendulum {
	public double mult_m_by=100;
	
	public double g = 9.8*mult_m_by;
	
	public double a1; //position
	public double a2;
	
	public double a11; //velocity
	public double a21;
	
	public double a12; //acceleration
	public double a22;
	
	public double l1;
	public double l2;
	
	public double m1;
	public double m2;
	
	public double d1;
	public double d2;
	
	public int mode;
	
	
	public Pendulum(int mode){
		this.mode=mode;
		
		a1=Math.PI/2;
		a2=Math.PI;
		
		a11=0;
		a21=0;
		
		a12=0;
		a22=0;
		
		l1=1*mult_m_by;
		l2=1*mult_m_by;
		
		m1=1;
		m2=1;
		
		d1=20*m1;
		d2=20*m2;
		
		System.out.println("Mode: "+mode);
	}
	
	//lol look at the size of that shit XD
	//believe it or not they are the same equations

	public void update(double t) {
		
		
		/*if(mode==1) {
			a12=(((Math.cos(a1)*Math.cos(a2)*Math.sin(a2)-Math.sin(a1)*Math.pow(Math.cos(a2),2))*g+
					(l2*Math.cos(a1)*Math.pow(Math.sin(a2),3)-l2*Math.sin(a1)*Math.cos(a2)*Math.pow(Math.sin(a2),2)+l2*Math.cos(a1)*Math.pow(Math.cos(a2),2)*Math.sin(a2)-l2*Math.sin(a1)*Math.pow(Math.cos(a2),3))*Math.pow(a21,2)+l1*Math.cos(a1)*Math.sin(a1)*
					Math.pow(a11,2)*Math.pow(Math.sin(a2),2)+(l1*Math.pow(Math.cos(a1),2)*Math.pow(a11,2)-l1*Math.pow(Math.sin(a1),2)*Math.pow(a11,2))*Math.cos(a2)*Math.sin(a2)-l1*Math.cos(a1)*Math.sin(a1)*Math.pow(a11,2)*Math.pow(Math.cos(a2),2))*m2+
					(-Math.sin(a1)*Math.pow(Math.sin(a2),2)-Math.sin(a1)*Math.pow(Math.cos(a2),2))*g*m1)/(
							(l1*Math.pow(Math.cos(a1),2)*Math.pow(Math.sin(a2),2)-2*l1*Math.cos(a1)*Math.sin(a1)*Math.cos(a2)*Math.sin(a2)+l1*Math.pow(Math.sin(a1),2)*Math.pow(Math.cos(a2),2))*m2+
							((l1*Math.pow(Math.sin(a1),2)+l1*Math.pow(Math.cos(a1),2))*Math.pow(Math.sin(a2),2)+(l1*Math.pow(Math.sin(a1),2)+l1*Math.pow(Math.cos(a1),2))*Math.pow(Math.cos(a2),2))*m1);

			a22=-(((Math.pow(Math.cos(a1),2)*Math.sin(a2)-Math.cos(a1)*Math.sin(a1)*Math.cos(a2))*g+
					(l2*Math.cos(a1)*Math.sin(a1)*Math.pow(Math.sin(a2),2)+(l2*Math.pow(Math.cos(a1),2)-l2*Math.pow(Math.sin(a1),2))*Math.cos(a2)*Math.sin(a2)-l2*Math.cos(a1)*Math.sin(a1)*Math.pow(Math.cos(a2),2))*Math.pow(a21,2)+
					(l1*Math.cos(a1)*Math.pow(Math.sin(a1),2)*Math.pow(a11,2)+l1*Math.pow(Math.cos(a1),3)*Math.pow(a11,2))*Math.sin(a2)+(-l1*Math.pow(Math.sin(a1),3)*Math.pow(a11,2)-l1*Math.pow(Math.cos(a1),2)*Math.sin(a1)*Math.pow(a11,2))*Math.cos(a2))*m2+(
							(Math.pow(Math.cos(a1),2)*Math.sin(a2)-Math.cos(a1)*Math.sin(a1)*Math.cos(a2))*g+(l1*Math.cos(a1)*Math.pow(Math.sin(a1),2)*Math.pow(a11,2)+l1*Math.pow(Math.cos(a1),3)*Math.pow(a11,2))*Math.sin(a2)+
							(-l1*Math.pow(Math.sin(a1),3)*Math.pow(a11,2)-l1*Math.pow(Math.cos(a1),2)*Math.sin(a1)*Math.pow(a11,2))*Math.cos(a2))*m1)/(
									(l2*Math.pow(Math.cos(a1),2)*Math.pow(Math.sin(a2),2)-2*l2*Math.cos(a1)*Math.sin(a1)*Math.cos(a2)*Math.sin(a2)+l2*Math.pow(Math.sin(a1),2)*Math.pow(Math.cos(a2),2))*m2+
									((l2*Math.pow(Math.sin(a1),2)+l2*Math.pow(Math.cos(a1),2))*Math.pow(Math.sin(a2),2)+(l2*Math.pow(Math.sin(a1),2)+l2*Math.pow(Math.cos(a1),2))*Math.pow(Math.cos(a2),2))*m1);
		}
		else {*/
			a12=(-g*(2*m1+m2)*Math.sin(a1)-m2*g*Math.sin(a1-2*a2)-2*Math.sin(a1-a2)*m2*(a21*a21*l2+a11*a11*l1*Math.cos(a1-a2)))/(l1*(2*m1+m2-m2*Math.cos(2*a1-2*a2)));
			a22=(2*Math.sin(a1-a2)*(a11*a11*l1*(m1+m2)+g*(m1+m2)*Math.cos(a1)+a21*a21*l2*m2*Math.cos(a1-a2)))/(l2*(2*m1+m2-m2*Math.cos(2*a1-2*a2)));
	/*	}*/
		
		if(mode==1) {
			a1+=a11*t+0.5*a12*t*t;
			a2+=a21*t+0.5*a22*t*t;
		}
		
		a11+=a12*t;
		a21+=a22*t;
		
		if(mode==2) {
			a1+=a11*t;
			a2+=a21*t;
		}
		
		if(mode==3) {
			double ep = (-l2*Math.cos(a2)-l1*Math.cos(a1)+l2+l1)*g*m2+(l1-l1*Math.cos(a1))*g*m1;
			double ec = 0.5*(Math.pow((l2*Math.sin(a2)*a21+l1*Math.sin(a1)*a11),2)+Math.pow((l2*Math.cos(a2)*a21+l1*Math.cos(a1)*a11),2))*m2+0.5*
					(Math.pow(l1,2)*Math.pow(Math.sin(a1),2)*Math.pow(a11,2)+Math.pow(l1,2)*Math.pow(Math.cos(a1),2)*Math.pow(a11,2))*m1;

			System.out.println("em: "+(ep+ec));
		}
	}
	
}
