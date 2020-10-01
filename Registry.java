public class Registry
{

	/**
	 * creates instance variables students (which stores all the students currently enrolled),
	 * and courses (which stores all courses currently offered), and a new scheduler object
	 */
   private Map<String, Student>      students;
   private TreeMap<String, ActiveCourse> courses;
   public Scheduler schedule;
   
   public Registry()
   {
	   /**
		* creates new students and courses treemap
		* creates new file object from students.txt file
		* calls addStudentFromFile to create new student objects and populate the students treemap
		* initializes new schedule object from the courses treemap
		*/

	   students = new TreeMap<String, Student>();
		courses = new TreeMap<String, ActiveCourse>();
		File file = new File("students.txt");
		addStudentsFromFile(file);
		this.schedule = new Scheduler(courses);


	   ArrayList<Student> list = new ArrayList<Student>();


	   /**
		* Creating new courses
		*/
	   // CPS209
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   // CPS511
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	   // CPS643
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   // CPS706
	   courseName = "Computer Networks";
	   courseCode = "CPS706";
	   descr = "Learn about Computer Networking";
	   format = "3Lec 1Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   // CPS616
	   courseName = "Algorithms";
	   courseCode = "CPS616";
	   descr = "Learn about Algorithms";
	   format = "3Lec 1Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
   }


	/**
	 * Helper method that reads a file containing students, line by line, and creates new student objects
	 * @param file a file of student names and their ids
	 */
	public void addStudentsFromFile(File file)
   {
		try
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
			{
				String studentName = scanner.next();
				String studentId = scanner.next();
				Student newStudent = new Student(studentName, studentId);
				students.put(studentId, newStudent);
//				scanner.nextLine();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("This file does not exist.");
			e.printStackTrace();
		}
   }


	/**
	 * This method creates a new student object and registers them. It checks if they are already registered,
	 * if not, it adds the new student object to the students ArrayList
	 * @param name A string containing the student's name.
	 * @param id A string containing the student's ID.
	 * @return A boolean value: true if student was able to be registered/already registered, false otherwise
	 */
   public boolean addNewStudent(String name, String id)
   {
	   if (findStudent(id) != null) return false;
	   	   
	   students.put(id, new Student(name, id));
//	   Collections.sort(students);
	   return true;
   }


	/**
	 * This method removes a student from the registry. It checks if the student is already
	 * registered, and then removes them from the students ArrayList.
	 * @param studentId A string containing the student's ID.
	 * @return A boolean value: true if the student was able to be removed, false otherwise
	 */
   public boolean removeStudent(String studentId)
   {
     if (students.containsKey(studentId))
	   {
	      students.remove(studentId);
		  return true;
	   }
	 return false;
   }


	/**
	 * This method prints all students currently registered.
	 */
   public void printAllStudents()
   {
	   for (String key : students.keySet())
	   {
		   System.out.println(students.get(key));
	   }
   }


	/**
	 * This method finds a student in the students treemap, given a student ID
	 * @param id a string containing a student's id
	 * @return a student object
	 */
	private Student findStudent(String id)
   {
	   Student student = students.get(id);
	   if (students.containsValue(student))
	   {
	   		return student;
	   }
	   return null;
   }


	/**
	 * This method finds a course in the courses treemap, given a course code
	 * @param code a string containing the courses code
	 * @return a course object
	 */
	private ActiveCourse findCourse(String code)
   {
   		ActiveCourse ac = courses.get(code);
   		if (courses.containsValue(ac))
	   {
	   		return ac;
	   }
   		return null;
   }


	/**
	 * This method adds a student to a course's class roster, with an initial grade of zero.
	 * @param studentId a student's ID
	 * @param courseCode A string containing the course's course code.
	 */
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
			   
	   ac.students.add(s);
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
   }



	/**
	 * This method drops a student from a course.
	 * @param studentId A string containing the student's ID.
	 * @param courseCode A string containing the course's course code.
	 */
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId);
	   s.removeActiveCourse(courseCode);
   }


	/**
	 * This method prints all active courses, i.e., all courses in the courses ArrayList.
	 */
   public void printActiveCourses()
   {
      for (String key : courses.keySet())
	  {
	  		ActiveCourse ac = courses.get(key);
	  		System.out.println(ac.getDescription());
	  }
   }


	/**
	 * This method prints all the students currently enrolled in a given course.
	 * @param courseCode A string containing the course code.
	 */
   public void printClassList(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printClassList();
   }


	/**
	 * This method sorts a course's class list alphabetically by student name.
	 * @param courseCode A string containing the course code.
	 */
   public void sortCourseByName(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortByName();
   }


	/**
	 * This method sorts a course's class list numerically by student ID.
	 * @param courseCode A string containing the course code.
	 */
   public void sortCourseById(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.sortById();	   
   }


	/**
	 * This method prints the grades of each student for a given course.
	 * @param courseCode A string containing the course code.
	 */
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = findCourse(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }


	/**
	 * This method prints all active courses of a given student.
	 * @param studentId A string containing of a student's ID
	 */
   public void printStudentCourses(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }


	/**
	 * This method prints all grades of completed courses for a given student
	 * @param studentId A string containing of a student's ID
	 */
   public void printStudentTranscript(String studentId)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   
	   s.printTranscript();
   }


	/**
	 * This method sets a final grade for a student, given a course, student ID, and grade.
	 * @param courseCode A string containing the course code.
	 * @param studentId A string containing of a student's ID
	 * @param grade A double containing the student's grade
	 */
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = findStudent(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
   }
}
