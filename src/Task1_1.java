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
public class Task1_1
{

	WorkSchedule ws;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Schedule = [ {}, {Nisse, �sa}, {Kalle}, {}, {} ... ], size = 20
		ws = new WorkSchedule(20);
		ws.addWorkingPeriod("Nisse", 1, 1);
		ws.addWorkingPeriod("�sa", 1, 1);
		ws.addWorkingPeriod("Kalle", 2, 2);
	}
	
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
				toSet(new String[] {"Nisse", "�sa", "Kalle"}),
				toSet(result)
			);
	}
	
	/**
	 * Part2
	 * starttime > endtime
	 */
	@Test
	public void workingEmployees_Test_m_part2() {
		final String[] result = ws.workingEmployees(18, 16);
		assertEquals(0, result.length);
	}
	
	
	Set<String> toSet(String[] arr) {
		return new HashSet<String>(Arrays.asList(arr));
	}

}