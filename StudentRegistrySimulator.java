import java.util.ArrayList;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  Registry registry = new Registry();
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
			  return;


		  /**
		   * Register a new student
		   * Checks if student name contains only letters
		   * Checks if student ID contains only numbers
		   * Checks if student is already registered
		   */
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())
			  {
				 name = commandLine.next();
				 // check for all alphabetical
				 String lcase = name.toLowerCase();
				 if (!isStringOnlyAlphabet(lcase))
				 {
				   System.out.println("Invalid Characters in Name " + name);
				   continue;
				 }
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
				 // check for all numeric
				 if (!isNumeric(id))
				 {
				   System.out.println("Invalid Characters in ID " + id);
				   continue;
				 }
				 if (!registry.addNewStudent(name,id))
					 System.out.println("Student " + name + " already registered");
			  }
			 
		  }

		  /**
		   * Deletes a student from a registry
		   * Checks if student ID contains only numbers
		   */
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  if (commandLine.hasNext())
			  {
				 String id = commandLine.next();
				 // check for all numeric
				 
				 if (!isNumeric(id))
				   System.out.println("Invalid Characters in student id " + id);
				 registry.removeStudent(id);
			  }
		  }

		  /**
		   * Prints all active courses
		   */
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  registry.printActiveCourses();
		  }

		  /**
		   * Prints a class list for a given course
		   */
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printClassList(courseCode);
			  }
		  }

		  /**
		   * Prints students and their grades for a given course
		   */
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printGrades(courseCode);
			  }
		  }

		  /**
		   * Adds a student to an active course
		   * Checks if student ID contains only numbers
		   */
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.addCourse(id, courseCode);
			  }
			  
		  }

		  /**
		   * Drops a student from a course
		   * Checks if student ID contains only numbers
		   */
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.dropCourse(id, courseCode);
			  }
			  
		  }


		  /**
		   * Prints all credit courses for a given student
		   * Checks if student ID contains only numbers
		   */
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentCourses(studentId);
			  }
		  }


		  /**
		   * Prints a student's transcript
		   */
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentTranscript(studentId);
			  }
		  }

		  /**
		   * Sets the final grade for a student, given a course and grade
		   */
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();
				  if (!isNumeric(grade)) continue;
				  numGrade = Integer.parseInt(grade);
				  registry.setFinalGrade(courseCode, id, numGrade);
			  }
			  
		  }


		  /**
		   * Sorts a course's class list alphabetically by name
		   */
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.sortCourseByName(courseCode);
			  }
		  }


		  /**
		   * Sorts a course's class list by student ID
		   */
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.sortCourseById(courseCode);
			  }
		  }


		  /**
		   * Schedules a given course on a given day, at a given time, for a given duration
		   */
		  else if (command.equalsIgnoreCase("SCH"))
		  {
		  	String courseCode = null;
		  	String day = null;
		  	int start = 0;
		  	int duration = 0;
		  	if (commandLine.hasNext())
			{
				courseCode = commandLine.next();
			}
		  	if (commandLine.hasNext())
			{
				day = commandLine.next();
			}
		  	if (commandLine.hasNext())
			{
				start = Integer.parseInt(commandLine.next());
			}
		  	if (commandLine.hasNext())
			{
				duration = Integer.parseInt(commandLine.next());
			}
		  	try
			{
				registry.schedule.setDayAndTime(courseCode, day, start, duration);
			}
		  	catch (SchedulerException e)
			{
				System.out.println(e);
			}

		  }


		  /**
		   * Prints the entires schedule
		   */
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
		  		registry.schedule.printSchedule();
		  }

		  /**
		   * This method clears the schedule of a given course
		   */
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
		  		String courseCode = null;
		  		if (commandLine.hasNext())
				{
					courseCode = commandLine.next();
					registry.schedule.clearSchedule(courseCode);
				}

		  }
		  System.out.print("\n>");
	  }
  }


	/**
	 * This helper method checks if a given string contains only letters
	 * @param str A string, in this case containing a student's name
	 * @return A boolean value: true if a string only contains letters, false otherwise
	 */
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  }


	/**
	 * This helper method checks if a given string contains only numbers
	 * @param str A string, in this case containing a student's ID
	 * @return A boolean value: true if a string only contains numbers, false otherwise
	 */
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }
}