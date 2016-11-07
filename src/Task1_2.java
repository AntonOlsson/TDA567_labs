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
 */
public class Task1_2
{

	WorkSchedule ws;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Schedule = [ {}, {Nisse, Åsa}, {Kalle}, {}, {} ... ], size = 20
		ws = new WorkSchedule(20);
		ws.addWorkingPeriod("Nisse", 1, 1);
		ws.addWorkingPeriod("Åsa", 1, 1);
		ws.addWorkingPeriod("Kalle", 2, 2);
	}
	
	
	/* PARTITION 1 */
	
	/**
	 * Part1
	 * starttime <= endtime
	 */
	@Test
	public void workingEmployees_Part1_test_empty_return() {
		final String[] result = ws.workingEmployees(16, 18);
		assertEquals(0, result.length);
	}
	/**
	 * Part1
	 * starttime <= endtime
	 */
	@Test
	public void workingEmployees_Part1_test_non_empty_return() {
		final String[] result = ws.workingEmployees(1, 3);
		assertEquals(
				toSet(new String[] {"Nisse", "Åsa", "Kalle"}),
				toSet(result)
			);
	}
	
	/* PARTITION 1 BORDER CASES */
	
	
	
	/* PARTITION 2 */
	
	/**
	 * Part2
	 * starttime > endtime
	 */
	@Test
	public void workingEmployees_Test_m_part2() {
		final String[] result = ws.workingEmployees(18, 16);
		assertEquals(0, result.length);
	}
	
	/* PARTITION 2 BORDER CASES */
	
	/**
	 * Part2
	 * starttime > endtime
	 */
	
	@Test
	public void workingEmployees_Starttime_eq_endtime_plus_1() {
		final String[] result = ws.workingEmployees(3, 2);
		assertEquals(0, result.length);
	}
	
	
	Set<String> toSet(String[] arr) {
		return new HashSet<String>(Arrays.asList(arr));
	}

}
