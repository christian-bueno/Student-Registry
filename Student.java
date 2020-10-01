import java.util.ArrayList;

public class Student implements Comparable<Student>
{
/**
 * Creating instance variables
 */

  private String name;
  private String id;
  private ArrayList<CreditCourse> courses;


/**
 * Constructor method for student class to initialize instance variables
 * @param name A string containing this Student's name
 * @param id A string containing this student's ID
 */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses = new ArrayList<CreditCourse>();
  }


/**
 * Accessor method to return student's ID
 * @return A string containing this student's ID
 */
  public String getId()
  {
	  return id;
  }


/**
 * Accessor method to return student's name
 * @return A string containing this student's Name
 */
  public String getName()
  {
	  return name;
  }

/**
 * Helper method that checks if a student has already taken a course
 * @param courseCode A string containing the course code
 * @return True if the student has taken a course, false otherwise
 */
  public boolean takenCourse(String courseCode)
  {
    for (int j = 0; j < courses.size(); j++)
    {
      if (courses.get(j).getCode().equalsIgnoreCase(courseCode))
        return true;
	}
    return false;
  }

/**
 * Adds a credit course to the student's ArrayList of courses
 * @param courseName A string containing the course name.
 * @param courseCode A string containing the course code.
 * @param descr A string containing the course description.
 * @param format A string containing how many weekly lectures and labs are taken for this course
 * @param sem A string containing the semester the course is currently offered
 * @param grade A double containing the student's grade for this course
 */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse cc = new CreditCourse(courseName,courseCode,descr,format,sem, grade);
	  cc.setActive();
	  courses.add(cc);
  }

/**
 * An accessor method that returns a student's grade, given a course code
 * @param courseCode A string containing a course code.
 * @return a double containing the students grade for a given course
 */
  public double getGrade(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	  {
		if (courses.get(i).getCode().equals(courseCode))
		{
			return courses.get(i).grade; 
		}
	  }
	  return 0;
  }


/**
 * A setter method that sets a student grade for a given course
 * @param courseCode a string containing a courses code
 * @param grade a double representing the student's grade for the course
 */
    public void setGrade(String courseCode, double grade)
  {
    for (int k = 0; k < courses.size(); k++)
    {
	   if (courses.get(k).getCode().equalsIgnoreCase(courseCode))
	   {
		  courses.get(k).grade = grade;
		  courses.get(k).setInactive();
	   }
    }
  }

/**
 * Prints a student's transcript, including the course code, course name, and course grade
 */
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++)
	  {
		  if (!courses.get(i).active)
			  System.out.println(courses.get(i).displayGrade());
	  }
  }


/**
 * Prints all active courses the student is enrolled in
 */
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 }
  }

/**
 * Prints all completed courses, that is, courses that are no longer active
 */
  public void printCompletedCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (!courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 }
  }


/**
 * Removes an active course from a student's list of courses
 * @param courseCode A string containing the course's course code.
 */
  public void removeActiveCourse(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) 
		 {
            courses.remove(i);
            return;
		 }
	 }
  }

/**
 * Overrides the String object's default toString() method
 * @return A string representation of a student's ID and name
 */
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }


/**
 * Checks if two student objects have the same name and ID
 * @param other A student object
 * @return A boolean value: true if the two objects are the same, false otherwise
 */
  public int compareTo(Student other)
  {
	  return this.name.compareTo(other.name);
  }


/**
 * comapres two student names alphabetically
 * @param other A student object
 * @return a negative value if this.student comes before student other,
 * positive value if this.student comes after student other,
 * zero if the names are equal
 */
  public boolean equals(Object other)
  {
	  Student s = (Student) other;
	  return this.name.equals(s.name) && this.id.equals(s.id);
  }
  
}
