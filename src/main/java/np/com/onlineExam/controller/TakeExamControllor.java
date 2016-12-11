package np.com.onlineExam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import np.com.onlineExam.dao.Answer;
import np.com.onlineExam.dao.AnswerDAO;
import np.com.onlineExam.dao.Exam;
import np.com.onlineExam.dao.ExamDAO;
import np.com.onlineExam.dao.Question;
import np.com.onlineExam.dao.QuestionDAO;
import np.com.onlineExam.dao.Score;
import np.com.onlineExam.dao.ScoreDAO;
import np.com.onlineExam.dao.Student;
import np.com.onlineExam.dao.StudentDAO;
import np.com.onlineExam.dao.Teacher;
import np.com.onlineExam.dao.TeacherDAO;

@Controller
public class TakeExamControllor {

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private ExamDAO examDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private ScoreDAO scoreDAO;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private TeacherDAO teacherDAO;

	private long examId;
	private Exam e;

	private long studentId;
	private String firstName;
	private String lastName;

	private List<Question> questionList;
	private List<Answer> tempList = null;
	private List<String> answerList[];
	private List<Long> questionIdList = null;
	private List<String> questionTextList = null;
	private List<String> questionTypeList = null;
	private List<String> correctAnswerList = null;
	private List<Student> studentList = null;
	private Student student = null;
	
	/*
	 * Redirect to Display Question Page
	 * 
	 */
	@RequestMapping(value = "/startExam", method = RequestMethod.POST)
	public ModelAndView takeExam() {

		// create model of display_question.jsp
		ModelAndView model = new ModelAndView("display_question");
		// set first and last name to display in target page as a parameter
		model.addObject("firstName", firstName);
		model.addObject("lastName", lastName);

		System.out.println(firstName + lastName);

		// get all questions, and send them
		questionList = new ArrayList<>();
		questionList = questionDAO.getAllQuestion(examId);

		questionIdList = new ArrayList<>();
		questionTextList = new ArrayList<>();
		questionTypeList = new ArrayList<>();
		correctAnswerList = new ArrayList<>();

		for (Question q : questionList) {
			questionIdList.add(q.getId());

			questionTextList.add(q.getText());
			questionTypeList.add(q.getType());
			correctAnswerList.add(q.getCorrectAnswer());
		}

		for (int i = 0; i < questionTypeList.size(); i++) {

		}

		model.addObject("question", questionTextList);

		model.addObject("opType", questionTypeList);

		answerList = new ArrayList[questionList.size()];
		// get all possible options and question type
		for (int i = 0; i < questionList.size(); i++) {
			tempList = new ArrayList<>();
			tempList = answerDAO.getAllAnswer(questionIdList.get(i));

			answerList[i] = new ArrayList<>();
			for (Answer a : tempList) {
				answerList[i].add(a.getText());

			}
		}

		model.addObject("options", answerList);
		return model;
	}

	/*
	 * Submit Test Result for Checking
	 */
	@RequestMapping(value = "/submitTestResult", method = RequestMethod.POST)
	public ModelAndView submitMarks(@RequestParam("studentAnswer") String givenAnswer) {

		// count score
		int scoreCount = 0;
		ModelAndView model = null;
		List<String> wrongAns = new ArrayList<>();

		Score s = null;
		s = scoreDAO.getStudentByeIdAndSid(studentId, examId);
		System.out.println("Score Result: " + s);

		if (s == null) {

			String[] answers = givenAnswer.split("____");
			System.out.println("length of the answer: " + answers.length);
			for (int i = 0; i < correctAnswerList.size(); i++) {
				if (answers[i].equals(correctAnswerList.get(i))) {
					// increase score if answer is matched
					scoreCount++;
				} else {
					wrongAns.add((i + 1) + ". " + questionTextList.get(i) + " Your Answer: (" + answers[i] + ")");
				}
			}
			s = new Score();
			s.setExam(e);
			Student student = studentDAO.getStudent(studentId);
			s.setStudent(student);
			// add Score in Database
			s.setValue(scoreCount);
			scoreDAO.persist(s);

			model = new ModelAndView("score_display");
			model.addObject("score", scoreCount);
			model.addObject("totalQn", correctAnswerList.size());
			model.addObject("wrongAnswer", wrongAns);
		} else {
			model = new ModelAndView("error_page");
		}
		return model;
	}


	@RequestMapping(value = "/registerTeacher", method = RequestMethod.POST)
	public ModelAndView registerTeacher(@RequestParam("userName") String username,
			@RequestParam("password") String password) {
		boolean success = false;
		success = teacherDAO.isExist(username);
		System.out.println(success);
		System.out.println(username + password);

		// create model of student_register.jsp
		ModelAndView model = new ModelAndView("teacher_register");
		// get Student List to check passed name already in there or not
		if (success) {
			model.addObject("msg", "Username Already Exists");
		} else {
			System.out.println("I am going to die");
			Teacher teacher = new Teacher(username, password);
			teacherDAO.persist(teacher);
			model.addObject("msg", "You are successfully Registered.");
		}
		return model;
	}

	/*
	 * Register Student First Name and Last Name in database
	 * 
	 * @Param firstName
	 * 
	 * @Param lastName
	 */

	@RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
	public ModelAndView registerStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		boolean success = false;
		// create model of student_register.jsp
		ModelAndView model = new ModelAndView("student_register");

		// get Student List to check passed name already in there or not
		studentList = new ArrayList<>();
		studentList = studentDAO.getAllStudent();
		System.out.println("size of the student: " + studentList.size());
		if (studentList.size() != 0) {
			for (int i = 0; i < studentList.size(); i++) {
				if (studentList.get(i).getFirstName().equals(firstName)
						&& studentList.get(i).getLastName().equals(lastName)) {
					success = false;
					break;
				} else {
					success = true;
				}
			}
			// pass success message to the page if registration is successful
			if (success) {
				model.addObject("success", "You are successfully Registered in our system.");
				Student student = new Student(firstName, lastName);
				studentDAO.persist(student);
			} else {
				// pass error message to the page if registration is failed
				model.addObject("errmsg", firstName + " " + lastName + " already exists in our database.");
			}
		} else {
			// Add student to the database if database is empty
			Student student = new Student(firstName, lastName);
			studentDAO.persist(student);
			model.addObject("success", "You are successfully Registered in our system.");
		}
		return model;
	}

	/*
	 * Check firstName and Last Name is registered or not in database.
	 * 
	 * @Param firstName
	 * 
	 * @Param lastName
	 */
	@RequestMapping(value = "/goStudent", method = RequestMethod.POST)
	public ModelAndView goStudent(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		ModelAndView model = null;

		// get student if First Name and Last Name match.
		studentList = new ArrayList<>();
		studentList = studentDAO.getStudentId(firstName, lastName);
		System.out.println(studentList.size());

		// check condition and return to corresponding page
		if (studentList.size() != 0) {
			// pass studentId
			this.studentId = studentList.get(0).getId();
			this.firstName = studentList.get(0).getFirstName();
			this.lastName = studentList.get(0).getLastName();

			// create model for show_student_marks.jsp
			model = new ModelAndView("show_student_marks");

			// check any tests available or not
			e = examDAO.getPublishedExam();

			if (e != null) {
				this.examId = e.getId();
				model.addObject("status", true);

				model.addObject("title", e.getTitle());
			} else if (e == null) {
				model = new ModelAndView("homepage");
				model.addObject("errmsg", " No exams available");
				return model;
			}

			// get list of score of particular student
			List<Score> scoreList = scoreDAO.getAllScoreByStudent(studentId);

			// add Object to a model to display in jsp
			model.addObject("scorelist", scoreList);
			model.addObject("firstName", firstName);
			model.addObject("lastName", lastName);
		} else {
			// return to homepage with error message if condition is false
			model = new ModelAndView("homepage");
			model.addObject("errmsg", firstName + " " + lastName + " not found. Please register first.");
		}
		return model;
	}

	// Student will see the marks in this all exams till now
	@RequestMapping(value = "/showStudentMarks", method = RequestMethod.POST)
	public ModelAndView showStudentMarks(@RequestParam("stdID") String stdID) {
		// create model of display_question.jsp
		ModelAndView model = new ModelAndView("show_student_marks");
		// set first and last name to display in target page as a parameter
		// input is the Student id
		long studentId = Long.parseLong(stdID);
		List<Score> scoreList = scoreDAO.getAllScoreByStudent(studentId);
		String studentName = scoreList.get(1).getStudent().getFirstName() + " "
				+ scoreList.get(1).getStudent().getLastName();
		model.addObject("student_name", studentName);
		model.addObject("score_list", scoreList);
		return model;
	}

	// TakeController

	@RequestMapping(value = "/displayAllExamResults")
	public ModelAndView displayAllExamResults() {
		// create model of display_question.jsp
		ModelAndView model = new ModelAndView("display_exam_results");

		List<Exam> examList = examDAO.getAllExam();
		studentList = studentDAO.getAllStudent();
		List<Score> scoreList = scoreDAO.getAllScore();
		// e = examDAO.getExam(examId);
		model.addObject("Title", e.getTitle());
		model.addObject("examList", examList);
		model.addObject("studentList", studentList);
		
		model.addObject("score_list", scoreList);

		return model;
	}

}
