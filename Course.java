public class Course implements Comparable<Course>
{

/**
 * Creating instance variables
 * code: course code
 * name: course name
 * description: course decription
 * format: course format
 */
   private String code;
   private String name;
   private String description;
   private String format;


/**
 * Default constructor method that takes no parameters, and initializes
 * all instance variables to empty strings
 */
   public Course()
   {
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
   }


/**
 * Second constructor method that takes parameters to initialize insance variables
 * @param name A string containing the course name.
 * @param code A string containing the course code.
 * @param descr A string containing the course description.
 * @param fmt A string containing how many weekly lectures and labs are taken for this course
 */
   public Course(String name, String code, String descr, String fmt)
   {
	 this.code        = code;
	 this.name        = name;
	 this.description = descr;
	 this.format      = fmt;
   }


/**
 * Accessor method that returns a course code
 * @return A string representing the course code.
 */
   public String getCode()
   {
	   return code;
   }


/**
 * Accessor method that returns a course name
 * @return A string representing the course name
 */
   public String getName()
   {
	   return name;
   }


/**
 * Accessor method that returns course format
 * i.e., how many lectures and labs each course has per week
 * @return A string representing the course format
 */
   public String getFormat()
   {
	   return format;
   }


/**
 * This method is used to concatenate basic course information
 * @return A string representing the course code, course name, course descrioption, and course format
 */
   public String getDescription()
   {
	   return code +" - " + name + "\n" + description + "\n" + format;
   }


/**
 * Accessor method to return the course description
 * @return A string representing the course description
 */
   public String getDescr()
   {
	   return description;
   }


/**
 * This method concatenates course code and course name
 * @return A string representing the course code and course name
 */
   public String getInfo()
   {
	   return code +" - " + name;
   }


/**
 * Checks if two course objects have the same course code
 * @param other a course object
 * @return A boolean value: true if the two objects are the same, false otherwise
 */
   public int compareTo(Course other)
   {
	   return this.code.compareTo(other.code);
   }


/**
 * This method converts the score parameter from a double to its letter grade equivalent
 * @param score A double containing the student's grade for the course
 * @return A string representing letter grade equivalent of the score
 */
   public static String convertNumericGrade(double score)
   {
 	  if (score >= 90)
 	    return "A+";
 	  else if (score >= 85)
 		return "A";
 	  else if (score >= 80)
 		return "A-";
 	  else if (score >= 77)
 		return "B+";
 	  else if (score >= 73)
 		return "B";
 	  else if (score >= 70)
 	    return "B-";
 	  else if (score >= 67)
 	    return "C+";
 	 else if (score >= 63)
  	    return "C";
 	else if (score >= 60)
 	    return "C-";
 	else if (score >= 57)
 	    return "D+";
 	else if (score >= 53)
 	    return "D";
 	else if (score >= 50)
 	    return "D-";
 	  else return "F";
   }
   
   
}
