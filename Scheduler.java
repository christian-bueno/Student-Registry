import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.*;

public class Scheduler 
{

	/**
	 * Creates instance variables:
	 * schedule: a treemap of active courses and their course codes
	 * validDays: an arraylist of the days of the week
	 * validTimes: an arraylist of the valid hours to have class
	 * arrayDay: a treemap that helps with printing the schedule (mapping days/hours with the indices)
	 * arrayHour: a treemap that helps with printing the schedule
	 */
	TreeMap<String,ActiveCourse> schedule;
	ArrayList<String> validDays = new ArrayList<String>(Arrays.asList("Mon", "Tue", "Wed", "Thur", "Fri"));
	ArrayList<String> validTimes = new ArrayList<String>(Arrays.asList("800", "900", "1000", "1100",
			"1200", "1300", "1400", "1500", "1600"));
	TreeMap<Integer, String> arrayDay = new TreeMap<Integer, String>();
	TreeMap<Integer, Integer> arrayHour = new TreeMap<Integer, Integer>();

	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{

		/**
		 * initializes the schedule to the courses arguement
		 * populates the arrayDay and arrayHour treemaps with valid days/hours and the associated index
		 */
		schedule = courses;
		arrayDay.put(1, "Mon");
		arrayDay.put(2, "Tue");
		arrayDay.put(3, "Wed");
		arrayDay.put(4, "Thur");
		arrayDay.put(5, "Fri");
		arrayHour.put(800, 1);
		arrayHour.put(900, 2);
		arrayHour.put(1000, 3);
		arrayHour.put(1100, 4);
		arrayHour.put(1200, 5);
		arrayHour.put(1300, 6);
		arrayHour.put(1400, 7);
		arrayHour.put(1500, 8);
		arrayHour.put(1600, 9);

	}


	/**
	 * This method sets an active courses' day, start, and duration variables, while extensively checking for
	 * errors.
	 * @param courseCode
	 * @param day a string containing the day of the lecture
	 * @param startTime an in containing the start time of a lecture
	 * @param duration an int containing the duration of a lecture
	 * @throws SchedulerException an exception if there is a scheduling conflict
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws SchedulerException
	{
		// see assignment doc
		ActiveCourse ac = schedule.get(courseCode.toUpperCase());
		if (ac == null)
		{
			throw new SchedulerException ("This course cannot be found");
		}
		else if (!isValidDay(day))
		{
			throw new SchedulerException ("This is not a valid day");
		}
		else if (startTime < 800 || startTime + duration*100 > 1700)
		{
			throw new SchedulerException("This is not a valid start time");
		}
		else if (duration < 1 || duration > 3)
		{
			throw new SchedulerException("This is not a valid duration");
		}
		else if (isOverlapping(day, startTime, duration))
		{
			throw new SchedulerException("This time overlaps with another course");
		}
		else
		{
			ac.setDay(day);
			ac.setStartTime(startTime);
			ac.setDuration(duration);
		}
	}


	/**
	 * a helper method that checks if a day is a valid day to have class
	 * @param day a string containing the day to have class
	 * @return true if the day is valid, false otherwise
	 */
	public boolean isValidDay(String day)
	{
		for (String validDay : validDays)
		{
			if (validDay.equalsIgnoreCase(day))
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * a helper method that checks if there is an overlap between class times
	 * @param day a string containing the day to have lecture
	 * @param startTime an int that contains the lecture's start time
	 * @param duration an int that contains the lecture's duration
	 * @return
	 */
	public boolean isOverlapping(String day, int startTime, int duration)
	{
		Set<String> keys = schedule.keySet();
		for (String key : keys)
		{
			ActiveCourse ac = schedule.get(key);
			int otherStartTime = ac.getStartTime();
			int otherDuration = ac.getDuration();
			int endTime = startTime + duration*100;
			int otherEndTime = otherStartTime + otherDuration*100;
			if (ac.getDay().equalsIgnoreCase(day))
			{
				if (courseCollisionCheck(startTime, endTime, otherStartTime, otherEndTime))
				{
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * a helper method that is checks if the start/end times of a class overlap
	 * @param startA start time for class A
	 * @param endA end time for class A
	 * @param startB start time for class B
	 * @param endB end time for class B
	 * @return
	 */
	public boolean courseCollisionCheck (int startA, int endA, int startB, int endB)
	{
		return(startA <= endB && startB <= endA);
	}


	/**
	 * This method clears the schedule of a given course
	 * @param courseCode a string containing the courses code
	 */
	public void clearSchedule(String courseCode)
	{
		ActiveCourse ac = schedule.get(courseCode);
		ac.setDay("");
		ac.setStartTime(0);
		ac.setDuration(0);
	}


	/**
	 * This method prints the entire schedule as a 2d Array
	 */
	public void printSchedule()
	{
		String[][] agenda = new String[10][6];
		agenda[0][0] = "   ";
		int i = 0;
		for (String day : validDays)
		{
			agenda[0][i+1] = day + "    ";
			i++;
		}
		i = 0;
		for (String time : validTimes)
		{
			agenda[i+1][0] = time + " ";
			i++;
		}
		Set<String> keys = schedule.keySet();
		for (String key : keys)
		{
			ActiveCourse ac = schedule.get(key);
			String code = ac.getCode();
			String day = ac.getDay();
			int start = ac.getStartTime();
			int duration = ac.getDuration();
			for (int k = arrayHour.get(start); k < (arrayHour.get(start)+duration); k++)
			{
				for (int j = 1; j <= 5; j++)
				{
					if (arrayDay.get(j).equalsIgnoreCase(day))
					{
						agenda[k][j] = code + "   ";
					}
				}
			}

		}
		for (String[] hour : agenda)
		{
			String classBlock = Arrays.toString(hour)
			.replace("]", "")
			.replace(",", "")
			.replace("[", "");
			System.out.println(classBlock);
		}
	}
	
}

