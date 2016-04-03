/**
 * @author Brandon S. Hang
 * @version 1.100
 * CS 1632
 * Deliverable 5
 * April 5, 2016
 * 
 * JUnit pinning test for the Cell.toString() method.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class PinWrite {
	
	/**
	 * Tests that a new Cell with no parameter returns "." (dead)
	 */
	@Test
	public void testNewDefaultCell() {
		
		Cell cell = new Cell();
		String string = cell.toString();
		
		assertEquals(string, ".");
	}
	
	
	/**
	 * Tests that a new Cell with the parameter true returns "X" (alive)
	 */
	@Test
	public void testNewTrueCell() {
		
		Cell cell = new Cell(true);
		String string = cell.toString();
		
		assertEquals(string, "X");
	}
	
	
	/**
	 * Tests that a new Cell with the parameter false returns "." (dead)
	 */
	@Test
	public void testNewFalseCell() {
		
		Cell cell = new Cell();
		String string = cell.toString();
		
		assertEquals(string, ".");
	}
	
	
	/**
	 * Tests that a Cell changed from dead to alive returns "X" (alive)
	 */
	@Test
	public void testCellSetAlive() {
		
		Cell cell = new Cell(false);
		cell.setAlive(true);
		String string = cell.toString();
		
		assertEquals(string, "X");
	}
	
	
	/**
	 * Tests that a Cell changed from alive to dead returns "." (dead)
	 */
	@Test
	public void testCellSetDead() {
		
		Cell cell = new Cell(true);
		cell.setAlive(false);
		String string = cell.toString();
		
		assertEquals(string, ".");
	}
}
