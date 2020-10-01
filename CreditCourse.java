import java.util.ArrayList;

public class CreditCourse extends Course 
{

	/**
	 * Creating instance variables
	 */
	private String  semester;
	public  double  grade;
	public  boolean active;
	
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		/**
		 * Default constructor method, that calls the super() constructor that initializes inherited
		 * variables from superclass Course
		 */
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade    = grade;
		active = false;
	}


	/**
	 * This setter method method sets the active variable to true
	 */
	public void setActive()
	{
		active = true;
	}


	/**
	 * This setter method sets the active variable to false
	 */
	public void setInactive()
	{
		active = false;
	}


	/**
	 * This method returns the superclass's getDescription() method along with the semester and grade
	 * @return A string representing the course code, course name, course description, course format,
	 * semester, and the student's grade for this course
	 */
	public String displayGrade()
	{
		return getCode() + " " + getName() + " " + semester + " Grade " + convertNumericGrade(grade); 
	}
	
}
