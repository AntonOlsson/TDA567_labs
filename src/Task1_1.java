/**
 * 
 */


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * @author antolss
 *
 * Test cases for method addWorkingEmployees
 *
 */
public class Task1_1
{

	WorkSchedule ws;
	 final static int SIZE = 4;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ws = new WorkSchedule(SIZE);
		ws.setRequiredNumber(3, 0, SIZE-1);
	}
	
	/*
	 * Partition 1a
	 * starttime <= endtime
	 */
	@Test
	public void test_starttime_lt_zero() {
		boolean result = ws.addWorkingPeriod("Nisse", -5, 1);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	
	/*
	 * Partition 1b
	 * endtime >= size
	 */
	@Test
	public void test_endtime_ge_size() {
		boolean result = ws.addWorkingPeriod("Nisse", 1, 10);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	
	/*
	 * Partition 1c
	 * starttime > endtime
	 */
	@Test
	public void test_starttime_gt_endtime() {
		boolean result = ws.addWorkingPeriod("Nisse", 2, 0);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);  // BUG! result is true, schedule unchanged
	}
	
	/*
	 * Partition 2
	 * This partition is for when the input hours interval 
	 * is not ok because some hour in it is already fully employed.
	 */
	@Test
	public void test_first_hour_fully_employed() {
		ws.readSchedule(0).workingEmployees = new String[]{"Aasa"};
		ws.readSchedule(1).workingEmployees = new String[]{"Kalle", "Aasa", "Niise"};
		
		boolean result = ws.addWorkingPeriod("Nisse", 1,2);
		
		assertScheduleEquals(new String[][] {
			{"Aasa"},
			{"Kalle", "Aasa", "Niise"},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	@Test
	public void test_last_hour_fully_employed() {
		ws.readSchedule(0).workingEmployees = new String[]{"Aasa"};
		// notice the 2 \/
		ws.readSchedule(2).workingEmployees = new String[]{"Kalle", "Aasa", "Niise"};
		
		boolean result = ws.addWorkingPeriod("Nisse", 1,2);
		
		assertScheduleEquals(new String[][] {
			{"Aasa"},
			{},
			{"Kalle", "Aasa", "Niise"},
			{},
		});
		
		assertEquals(false, result);
	}
	
	
	/*
	 * Partition 3
	 * This partition is for when the schedule is ok.
	 */
	@Test
	public void test_part3() {
		ws.readSchedule(0).workingEmployees = new String[]{"Aasa"};
		ws.readSchedule(1).workingEmployees = new String[]{"Kalle", "Aasa"};
		
		boolean result = ws.addWorkingPeriod("Nisse", 1,2);
		
		assertScheduleEquals(new String[][] {
			{"Aasa"},
			{"Kalle", "Aasa", "Nisse"},
			{"Nisse"},
			{},
		});
		
		assertEquals(true, result);
	}
	
	/*
	 * Partition 4
	 * This partition is for when the employee is already on an hour.
	 */
	@Test
	public void test_part4() {
		ws.readSchedule(0).workingEmployees = new String[]{"Aasa", "Nisse"};
		ws.readSchedule(1).workingEmployees = new String[]{"Kalle", "Aasa"};
		
		boolean result = ws.addWorkingPeriod("Nisse", 0,2);
		
		assertScheduleEquals(new String[][] {
			{"Aasa", "Nisse"},
			{"Kalle", "Aasa"},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	
	/*
	 * Border cases
	 */
	@Test
	public void test_starttime_lt_zero_border_max() {
		boolean result = ws.addWorkingPeriod("Nisse", -1, 1);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	@Test
	public void test_starttime_lt_zero_border_min() {
		boolean result = ws.addWorkingPeriod("Nisse", Integer.MIN_VALUE, 1);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	
	@Test
	public void test_endtime_ge_size_border_min() {
		boolean result = ws.addWorkingPeriod("Nisse", 3, 4);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	@Test
	public void test_endtime_ge_size_border_max() {
		boolean result = ws.addWorkingPeriod("Nisse", 2, Integer.MAX_VALUE);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	
	@Test
	public void test_starttime_gt_endtime_border() {
		boolean result = ws.addWorkingPeriod("Nisse", 2, 1);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{},
		});
		
		assertEquals(false, result);
	}
	@Test
	public void test_starttime_gt_endtime_border_xd() {
		boolean result = ws.addWorkingPeriod("Nisse", 2, 2);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{"Nisse"},
			{},
		});
		
		assertEquals(true, result);
	}
	
	@Test
	public void test_starttime_border_min() {
		boolean result = ws.addWorkingPeriod("Nisse", 0, 1);
		
		assertScheduleEquals(new String[][] {
			{"Nisse"},
			{"Nisse"},
			{},
			{},
		});
		
		assertEquals(true, result);
	}
	@Test
	public void test_starttime_border_max() {
		boolean result = ws.addWorkingPeriod("Nisse", SIZE-1, SIZE-1);
		
		assertScheduleEquals(new String[][] {
			{},
			{},
			{},
			{"Nisse"},
		});
		
		assertEquals(true, result);
	}
	
	
	private void assertScheduleEquals(String[][] schedule) {
		for (int i = 0; i < SIZE; i++ ) {
			assertEquals(schedule[i], ws.readSchedule(i).workingEmployees);
		}
	}
}
