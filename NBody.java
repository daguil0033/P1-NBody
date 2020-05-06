	

/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
		s.nextInt();
		double rad = s.nextDouble();

		s.close();

		return rad;
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));

			int nb = 0;
			nb = s.nextInt();
			s.nextDouble();
			CelestialBody[] bodiesArr = new CelestialBody[nb];
			for(int k=0; k < nb; k++) {
				double xp;
				double yp;
				double xv;
				double yv;
				double mass;
				String filename;
				xp = s.nextDouble();
				yp = s.nextDouble();
				xv = s.nextDouble();
				yv = s.nextDouble();
				mass = s.nextDouble();
				filename = s.next();
				CelestialBody cb = new CelestialBody(xp, yp, xv, yv, mass, filename);
				bodiesArr[k] = cb;

			}

			s.close();

			return bodiesArr;
	}

	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 39447000.0;
		double dt = 25000.0;

		String fname= "./data/kaleidoscope.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
		//StdAudio.play("images/2001.wav");
	
		// run simulation until time up

		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			double[] Xforces = new double[bodies.length];
			double[] Yforces = new double[bodies.length];
			
			// TODO: loop over all bodies, calculate netForcesX and Y
			for(int k=0; k < bodies.length; k++) {
				double XForce = bodies[k].calcNetForceExertedByX(bodies);
				double YForce = bodies[k].calcNetForceExertedByY(bodies);
				Xforces[k] = XForce;
				Yforces[k] = YForce;

			}
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			for(int k=0; k < bodies.length; k++) {
				bodies[k].update(dt, Xforces[k], Yforces[k]);
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for(int k=0; k < bodies.length; k++) {
				bodies[k].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
