

/**
 * Celestial Body class for NBody
 * @author Daniel Aguilar
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	public double getX() {
		// TODO: complete method
		return myXPos;
	}
	public double getY() {
		// TODO: complete method
		return myYPos;
	}
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}
	
	public double getMass() {
		// TODO: complete method
		return myMass;
	}
	public String getName() {
		// TODO: complete method
		return "" + myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double dx;
		double dy;
		double r;
		double dist;
		dx = myXPos - b.getX();
		dy = myYPos - b.getY();
		r = (dx * dx) + (dy * dy);
		dist = Math.sqrt(r);

		return dist;
	}

	public double calcForceExertedBy(CelestialBody b) {
		// TODO: complete method
		double F;
		double g;
		double x;
		g = 6.67 * 1e-11;
		x = (myMass * b.getMass()) / (calcDistance(b) * calcDistance(b));
		F = g * x;
		return F;
	}

	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		double F;
		double dx;
		double r;
		F = calcForceExertedBy(b);
		dx = b.getX() - myXPos;
		r = calcDistance(b);

		return ((dx/r) * F);
	}
	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		double F;
		double dy;
		double r;
		F = calcForceExertedBy(b);
		dy = b.getY() - myYPos;
		r = calcDistance(b);

		return ((dy/r) * F);
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double count;
		count = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				count += calcForceExertedByX(b);
			}
		}
		return count;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double count;
		count = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				count += calcForceExertedByY(b);
			}
		}
		return count;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax;
		double ay;
		ax = xforce / myMass;
		ay = yforce / myMass;
		double nvx;
		double nvy;
		nvx = myXVel + deltaT  * ax;
		nvy = myYVel + deltaT * ay;
		double nx;
		double ny;
		nx = myXPos +  deltaT * nvx;
		ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos =  ny;
		myXVel = nvx;
		myYVel = nvy;

	}

	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
