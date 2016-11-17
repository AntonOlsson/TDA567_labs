1. Black-box Testing

a)

public boolean addWorkingPeriod(String employee, int starttime, int endtime) { ... }

Input space:
    WorkSchedule.size >= 1
    employee != null
    "starttime" in { MIN_INT ... MAX_INT } 
    "endtime" in { MIN_INT ... MAX_INT } 


This partition is for when the input times is "wrong".


starttime < 0
Partition #1a: 
    test_starttime_lt_zero:
        input: size = 4, starttime = -5, employee = "nisse", endtime = 1
        expected: return False

endtime >= size
Partition #1b:
    test_endtime_ge_size:
        input: size = 4, starttime = 1, employee = "nisse", endtime = 10
        expected: return False

starttime > endtime
Partition #1c:
    test_starttime_gt_endtime:
        input: size = 4, starttime = 2, employee = "nisse", endtime = 1
        expected: return False

This partition is for when the input hours interval is not ok because some hour in it is already fully employed.
Partition #2:
    test_first_hour_fully_employed:
        input: starttime = 1, employee = "nisse", endtime = 2, size = 4, schedule = [ {Åsa}, {Kalle, Åsa, Niise}, {} ], requiredNumber = [3,3,3,3]
        expected: return False
    
    test_last_hour_fully_employed:
        input: starttime = 1, employee = "nisse", endtime = 2, size = 4, schedule = [ {Åsa}, {}, {Kalle, Åsa} ], requiredNumber = [3,3,3,3]
        expected: return False
        

This partition is for when the input is changing the Schedule.
Partition #3:
    test_part3:
        input: starttime = 1, employee = "nisse", endtime = 2, size = 4, schedule = [ {Åsa}, {Kalle, Åsa}, {}, {} ], requiredNumber = [3,3,3,3]
        expected: return True; schedule = [ {Åsa}, {Kalle, Åsa, Nisse}, {Nisse} ]

This partition is for when the employee is already on an hour.
Partition #4:
    test_part4:
        input: starttime = 0, employee = "nisse", endtime = 2, size = 4, schedule = [ {Åsa, Nisse}, {Kalle, Åsa}, {}, {} ], requiredNumber = [3,3,3,3]
        expected: return False; schedule unchanged.          

Border cases:

test_starttime_lt_zero_border_min:
    input: size = 4, starttime = -1, employee = "nisse", endtime = 5
    expected: return False
test_starttime_lt_zero_border_max:
    input: size = 4, starttime = MIN_INT, employee = "nisse", endtime = 5
    expected: return False
    
test_endtime_ge_size_border_min:
    input: size = 4, starttime = 3, employee = "nisse", endtime = 4
    expected: return False
test_endtime_ge_size_border_max:
    input: size = 4, starttime = 2, employee = "nisse", endtime = MAX_INT
    expected: return False
    
test_starttime_gt_endtime_border:
    input: size = 4, starttime = 4, employee = "nisse", endtime = 3
    expected: return False
test_starttime_gt_endtime_border_xd:
    input: size = 4, starttime = 1, employee = "nisse", endtime = 1
    expected: return True, schedule[1] = "nisse"
    
test_starttime_border_min:
    input: size = 4, starttime = 0, employee = "nisse", endtime = 5
    expected: return True
test_starttime_border_max:
    input: size = 4, starttime = 3, employee = "nisse", endtime = 3
    expected: return True


Bugs found:

starttime > endtime
Partition #1c:
    test_starttime_gt_endtime >>> BUG! result is true (should be false), schedule unchanged


b)

public String[] workingEmployees(int starttime, int endtime) { ... }

Input space:
    WorkSchedule.size >= 1
    starttime >= 0
    endtime in { MIN_INT ... WorkSchedule.size-1 } 

Partition 1: Start time < endtime:
    workingEmployees_Part1_test_empty_return:
        input: starttime = 0, endtime = 0, schedule = [ [], [Nisse, Åsa], [Kalle], [] ], size = 4, requiredNumber = [3,3,3,3]
        expected: []; schedule unchanged.
        
    workingEmployees_Part1_test_non_empty_return:
        input: starttime = 0, endtime = 3, schedule = [ [], [Nisse, Åsa], [Kalle], [] ], size = 4, requiredNumber = [3,3,3,3]
        expected: ["Nisse", "Åsa", "Kalle"]


Partition 2: Start time > endtime:
    workingEmployees_Test_m_part2:
        input: starttime = 0, endtime = 0, schedule = [ [], [Nisse, Åsa], [Kalle], [] ], size = 4, requiredNumber = [3,3,3,3]
        expected: []; schedule unchanged.

Border cases:


    
test_starttime_eq_endtime_border:
    input: starttime = 1, endtime = 1, size = 4
    expected: return ["Nisse", "Åsa"]













