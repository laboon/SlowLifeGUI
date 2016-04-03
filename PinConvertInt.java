/**
 * @author Brandon S. Hang
 * @version 1.500
 * CS 1632
 * Deliverable 5
 * April 5, 2016
 * 
 * JUnit pinning test for the MainPanel.convertToInt() method.  Uses
 * reflection per Chapter 25 to test the private method.
 */

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class PinConvertInt {
	
	private static Method convertToInt;			// Reflected private method
	private static MainPanel panel;
	
	
	/**
	 * Uses reflection to set up and test the private MainPanel.convertToInt() method
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpPrivateMethods() throws Exception {
		
		try {
			convertToInt = MainPanel.class.getDeclaredMethod("convertToInt", int.class);
			convertToInt.setAccessible(true);
		}
		catch (NoSuchMethodException nsme) {
			System.exit(1);
		}
	}
	
	
	/**
	 * Creates a new MainPanel object before every test
	 */
	@Before
	public void setUpMainPanel() {
		
		panel = new MainPanel(16);
	}
	
	
	/**
	 * Tests a common use integer
	 */
	@Test
	public void testConvertIntBaseCase() {
		
		Object returnObj;
		int returnInt;
		
		try {
			returnObj = convertToInt.invoke(panel, 888);
			returnInt = ((Integer)returnObj).intValue();
			
			assertEquals(returnInt, 888);
		}
		catch (IllegalAccessException | InvocationTargetException iex) {
			fail();
		}
	}
	
	
	/**
	 * Tests the edge case of the maximum int value
	 */
	@Test
	public void testConvertIntMaxEdgeCaseInt() {
		
		Object returnObj;
		int returnInt;
		
		try {
			returnObj = convertToInt.invoke(panel, Integer.MAX_VALUE);
			returnInt = ((Integer)returnObj).intValue();
			
			assertEquals(returnInt, Integer.MAX_VALUE);
		}
		catch (IllegalAccessException | InvocationTargetException iex) {
			fail();
		}
	}
	
	
	/**
	 * Tests the edge case zero
	 */
	@Test
	public void testConvertIntZeroEdgeCaseInt() {
		
		Object returnObj;
		int returnInt;
		
		try {
			returnObj = convertToInt.invoke(panel, 0);
			returnInt = ((Integer)returnObj).intValue();
			
			assertEquals(returnInt, 0);
		}
		catch (IllegalAccessException | InvocationTargetException iex) {
			fail();
		}
	}
	
	
	/**
	 * Tests the corner case of a negative integer
	 */
	@Test
	public void testConvertIntCornerCase() {
		
		try {
			convertToInt.invoke(panel, -5);
		}
		catch (InvocationTargetException iex) {			// Should throw a NumberFormatException
			Throwable cause = iex.getCause();
			assertThat(cause, instanceOf(NumberFormatException.class));
		}
		catch (IllegalAccessException iae) {
			fail();
		}
	}
}
