import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActiveCourse extends Course
{

	/**
	 * Creating instance variables
	 * Three new variables:
	 * lectureStart: the time a lecture starts
	 * lectureDuration: how long a lecture is
	 * lectureDay: the day a lecture takes place
	 */
   public  ArrayList<Student> students;
   private String             semester;
   private int lectureStart;
   private int lectureDuration;
   private String lectureDay;


	/**
	 * Constructor method that calls super() constructor in Course to initialize inherited variables, along with
	 * semester and enrolled students
	 * @param name A string containing the course name.
	 * @param code A string containing the course code.
	 * @param descr A string containing the course description.
	 * @param fmt A string containing how many weekly lectures and labs are taken for this course
	 * @param semester A string containing the semester the course is currently offered
	 * @param students An ArrayList of students enrolled in the course
	 * @param lectureStart an integer containing the start time of a lecture
   	 * @param lectureDuration an integer containing the duration of a lecture
	 * @param lectureDay a string containing the day of the lecture
	 */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   lectureStart = 0;
	   lectureDuration = 0;
	   lectureDay = "Mon";
   }

	/**
	 * a setter method that sets the lecture day to the parameter day
	 * @param day a string containing the lecture day
	 */
   public void setDay(String day)
   {
   		lectureDay = day;
   }


	/**
	 * an accessor method that gets the lecture day
	 * @return a string containing the lecture day
	 */
   public String getDay()
   {
   		return lectureDay;
   }


	/**
	 * a setter method that sets the lecture start time to the parameter time
	 * @param day a string containing the lecture start time
	 */
   public void setStartTime(int startTime)
	{
		lectureStart = startTime;
	}


	/**
	 * an accessor method that gets the lecture start time
	 * @return an int containing the lecture start time
	 */
	public int getStartTime()
	{
		return lectureStart;
	}


	/**
	 * a setter method that sets the lecture duration to the parameter duration
	 * @param day a string containing the lecture duration
	 */
   public void setDuration(int duration)
   {
   		lectureDuration = duration;
   }


	/**
	 * an accessor method that gets the lecture duration
	 * @return an int containing the lecture duration
	 */
   public int getDuration()
   {
   		return lectureDuration;
   }


	/**
	 * This accessor method returns the semester variable
	 * @return A string containing the semester the course is currently offered
	 */
   public String getSemester()
   {
	   return semester;
   }


	/**
	 * This method prints all the students enrolled in this class
	 */
	public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }


	/**
	 * This method prints out all enrolled students and their grades
	 */
	public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }


	/**
	 * This method overrides the getDescription() method in superclass Course by calling that superclass method, and
	 * adding the semester and number of students currently enrolled in the course
	 * @return A string containing the course description plus the semester and number of students enrolled
	 */
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }


	/**
	 * Accessor method to return the course description
	 * @return A string representing the course description
	 */
   public String getCourseDescription()
   {
	   return getDescr();
   }


	/**
	 * This method returns the grade of a student for a given course. It searches the given student's
	 * list of credit courses for the course that matches this active course, and returns the grade stored
	 * in the credit course
	 * @param studentId, A string containing this student's ID
	 * @return double A double representing the student's grade for this course
	 */
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }


	/**
	 * This method checks if a student is currently enrolled in this course
	 * @param studentId a string containing the student's id
	 * @return true if a student is enrolled, false otherwise
	 */
	public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }


	/**
	 * This method removes a student from the course
	 * @param id a string containing the student's id
	 */
	public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }


	/**
	 * This method sorts the class list by alphabetically name. Makes use of the NameComparator class below
	 */
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }


	/**
	 * This class implements the comparator interface, and compares students based on their name.
	 * Calls the compareTo() method in the student class
	 */
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }


	/**
	 * This method sorts the class by student ID. Makes use of the IdComparator class below.
	 */
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }


	/**
	 * This class implements the comparator interface, and compares students based on their student ID,
	 * in ascending order. Calls the compareTo() method in the student class
	 */
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
   }
}
