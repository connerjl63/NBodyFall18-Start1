

public class Body {
	
	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	private double mass;
	private String fileName;
	
	public Body(double myXPos, double myYPos, double myXVel, double myYVel, double myMass, String myFileName) {
		xPos = myXPos;
		yPos = myYPos;
		xVel = myXVel;
		yVel = myYVel;
		mass = myMass;
		fileName = myFileName;
	}
	
	public Body (Body b) {
		this(b.getX(), b.getY(), b.getXVel(), b.getYVel(), b.getMass(), b.getName());
	}
	
	public double getX() {
		return xPos;
	}
	public double getY() {
		return yPos;
	}
	public double getXVel() {
		return xVel;
	}
	public double getYVel() {
		return yVel;
	}
	public double getMass() {
		return mass;
	}
	public String getName() {
		return fileName;
	}
	
	public double calcDistance(Body x) {
		return Math.sqrt((this.getX()-x.getX())*(this.getX()-x.getX())+(this.getY()-x.getY())*(this.getY()-x.getY()));
	}
	public double calcForceExertedBy(Body x) {
		return (6.67e-11)*((this.getMass()*x.getMass())/((calcDistance(x)*calcDistance(x))));
	}
	public double calcForceExertedByX(Body x)
	{
		return calcForceExertedBy(x)*((x.getX()-this.getX())/calcDistance(x));
	}

	public double calcForceExertedByY(Body x)
	{
		return calcForceExertedBy(x)*((x.getY()-this.getY())/calcDistance(x));

	}

	public double calcNetForceExertedByX(Body[] x)
	{
		double netForceX=0;
		for(Body b : x)
			{
			if( ! b.equals(this))
				{
				netForceX=netForceX+calcForceExertedByX(b);
				}
			}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] x)
	{
		double netForceY=0;
		for(Body b : x)
			{
			if( ! b.equals(this))
				{
				netForceY=netForceY+calcForceExertedByY(b);
				}
			}
		return netForceY;
	}

	public void update(double deltaT, double xforce, double yforce)
	{
		double accelX=xforce/this.getMass();
		double accelY=yforce/this.getMass();
		double nvx=xVel+deltaT*accelX;
		double nvy=yVel+deltaT*accelY;
		double nx=xPos+deltaT*nvx;
		double ny=yPos+deltaT*nvy;
		xPos=nx;
		yPos=ny;
		xVel=nvx;
		yVel=nvy;
	}

	public void draw()
	{
		StdDraw.picture(xPos, yPos, "images/"+fileName);
	}
}
