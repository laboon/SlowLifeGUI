/**
 * @author Brandon S. Hang
 * @version 1.300
 * CS 1632
 * Deliverable 5
 * April 5, 2016
 * 
 * JUnit pinning test for the MainPanel.runContinuous() method.
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PinRunContinuous {
	
	private static MainPanel panel;
	
	
	/**
	 * Creates a new MainPanel object before every test
	 */
	@Before
	public void setUpMainPanel() {
		
		panel = new MainPanel(16);
	}
	
	
	/**
	 * Tests that the initial _r is 1000 before running
	 */
	@Test
	public void testRunContinuousInitialR() {
		
		int underscoreR = panel._r;
		
		assertEquals(underscoreR, 1000);
	}
	
	
	/**
	 * Tests that the panel size is 16
	 */
	@Test
	public void testRunContinuousSize() {
		
		assertEquals(panel.getSize(), 16);
	}
	
	
	/**
	 * Tests that the run continuous mode can be used
	 */
	@Test
	public void testRunContinuousActive() {
		
		Thread thread = new Thread(new Runnable() {		// Creates a Thread and Runnable object using MainPanel's runContinuous method
			public void run() {
				panel.runContinuous();
			}
		});
		
		thread.start();						// Calls runContinuous()
		
		assertTrue(thread.isAlive());			// Asserts that the Game of Life is running continuously
	}
	
	
	/**
	 * Tests that the _r value is the same before and after a continuous run
	 */
	@Test
	public void testRunContinuousRValue() {
		
		class LifeRunnable implements Runnable {		// Nested class that takes utilizes MainPanel's runContinuous and stop methods
			
			public void run() {
				panel.runContinuous();
			}
			
			public void end() {
				panel.stop();
			}
		}
		
		int finishR, startR = panel._r;
		LifeRunnable runLife = new LifeRunnable();
		Thread thread = new Thread(runLife);
		
		thread.start();						// Calls runContinuous()
		
		try {
			Thread.sleep(1000);						// Allows the Game of Life to run at least one full cycle
		}
		catch (InterruptedException ie) {
			
		}
		
		runLife.end();					// Calls stop()
		
		finishR = panel._r;					// Gets the _r value after stopping
		assertEquals(startR, finishR);
	}
}
