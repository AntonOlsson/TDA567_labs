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
 * Test cases for method workingEmployees
 */
public class Task1_2
{

	WorkSchedule ws;
	final static int SIZE = 4;
	
	@Before
	public void setUp() throws Exception {
		// Schedule = [ {}, {Nisse, Aasa}, {Kalle}, {}], size = SIZE, requiredNumber = 3 for every hour
		ws = new WorkSchedule(SIZE);
		ws.setRequiredNumber(3, 0, SIZE-1);
		ws.readSchedule(1).workingEmployees = new String[]{"Nisse", "Aasa"};
		ws.readSchedule(2).workingEmployees = new String[]{"Kalle"};
	}
	
	
	/*
	 * Partition 1
	 * starttime <= endtime
	 */
	@Test
	public void workingEmployees_Part1_test_empty_return() {
		final String[] result = ws.workingEmployees(0,0);
		assertEquals(0, result.length);
	}
	@Test
	public void workingEmployees_Part1_test_non_empty_return() {
		final String[] result = ws.workingEmployees(0, SIZE-1);
		assertEquals(
				toSet(new String[] {"Nisse", "Aasa", "Kalle"}),
				toSet(result)
			);
	}
	
	/*
	 * Part2
	 * starttime > endtime
	 */
	@Test
	public void workingEmployees_Test_m_part2() {
		final String[] result = ws.workingEmployees(2, 1);
		assertEquals(0, result.length);
	}
	
	
	Set<String> toSet(String[] arr) {
		return new HashSet<String>(Arrays.asList(arr));
	}

	
	String[] getWorkingHours(int s, int e) {
		HashSet<String> emps = new HashSet<>();
		for (int i = s; i <= e; i++) {
			emps.addAll(
					Arrays.asList(ws.readSchedule(i).workingEmployees)
					);
		}
		return emps.toArray(new String[0]);
	}
}
