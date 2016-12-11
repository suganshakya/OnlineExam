package np.com.onlineExam.controller;

import java.util.List;
import org.apache.log4j.Logger;
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
import np.com.onlineExam.dao.StudentDAO;
import np.com.onlineExam.dao.TeacherDAO;

@Controller
public class AddQuestionController {
    private static Logger log = Logger.getLogger(AddQuestionController.class);

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private ExamDAO examDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired 
	private ScoreDAO scoreDAO;

	@Autowired
	private TeacherDAO teacherDAO;
	private Exam exam;
	private long examId;

	private Question question;

	private long questionId;
	private String teacherName;
	private String teacherPassword;

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homePage() {
		return "homepage";
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.POST)
	public ModelAndView redirect(@RequestParam("name") String name, @RequestParam("password") String password) {
		boolean success = false;
		ModelAndView model = null;
		teacherName = name;
		teacherPassword = password;

		// get list from teacher table

		System.out.println(teacherDAO.getAllTeachers().size());


		if (teacherDAO.getAllTeachers().size() == 0) {
			if (name.contentEquals("admin") && password.contentEquals("admin")) {
				success = true;
			} else {
				success = false;
			}
		} else {
			success = teacherDAO.validate(name, password);
		}
		if (success) {
			model = new ModelAndView("kcList");
			model.addObject("examList", examDAO.getAllExam());
		} else {
			model = new ModelAndView("homepage");
			model.addObject("msg", "Invalid Username or Password");
		}

		return model;
	}

	@RequestMapping(value = "/redirectKC", method = RequestMethod.POST)
	public ModelAndView redirectKC(@RequestParam("btnHidden") String name, @RequestParam("checkStatus") String status) {
		String tempType = name.split("_")[0];
		String tempID = name.split("_")[1];
		System.out.println(tempType);
		System.out.println(tempID);
		ModelAndView model = new ModelAndView();
		examId = Long.parseLong(tempID);
		if (tempType.contentEquals("Update")) {
			model = new ModelAndView("questList");
			model.addObject("question", questionDAO.getAllQuestion(examId));
			model.addObject("teacherName", teacherName);
			model.addObject("teacherPassword", teacherPassword);
		} else if (tempType.contentEquals("Check")) {

			for (Exam exam : examDAO.getAllExam()) {
				exam.setStatus(false);
				examDAO.update(exam);
			}
			exam = examDAO.getExam(examId);
			if (status.contentEquals("true")) {
				exam.setStatus(true);
			} else if (status.contentEquals("false")) {
				exam.setStatus(false);
			}
			examDAO.update(exam);

			model = new ModelAndView("kcList");
			model.addObject("examList", examDAO.getAllExam());
		} else if (tempType.contentEquals("Delete")) {
			examDAO.remove(examId);
			model = new ModelAndView("kcList");
			model.addObject("examList", examDAO.getAllExam());
		}

		return model;

	}

	@RequestMapping(value = "/redirectQuest", method = RequestMethod.POST)
	public ModelAndView redirectQuest() {
		ModelAndView model = new ModelAndView("upload_question");
		model.addObject("teacherName", teacherName);
		model.addObject("teacherPassword", teacherPassword);
		return model;
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String loadQuest(@RequestParam("qText") String text, @RequestParam("qnChoice") String type,
			@RequestParam("options") String options, @RequestParam("answer") String correctAnswer) {
		String questType;
		if (type.contentEquals("1")) {
			questType = "Single Choice";
		} else if (type.contentEquals("2")) {
			questType = "Multiple Choice";
		} else if (type.contentEquals("3")) {
			questType = "True/False";
		} else if (type.contentEquals("4")) {
			questType = "Correct Order";
		} else {
			questType = "";
		}

		question = new Question();
		exam = examDAO.getExam(examId);
		question.setExam(exam);
		question.setText(text);
		question.setType(questType);
		question.setCorrectAnswer(correctAnswer.toUpperCase());
		questionDAO.persist(question);

		String[] answerText = options.split("____");
		Answer[] answer = new Answer[answerText.length];
		for (int i = 0; i < answer.length; ++i) {
			answer[i] = new Answer();
			answer[i].setText(answerText[i].toUpperCase());
			answer[i].setQuestion(question);
			answerDAO.persist(answer[i]);
		}

		return "redirect:redirectToQuestionList";
	}

	@RequestMapping(value = "/redirectToQuestionList")
	public ModelAndView finalPost() {
		ModelAndView model = new ModelAndView("questList");
		model.addObject("question", questionDAO.getAllQuestion(examId));
		model.addObject("teacherName", teacherName);
		model.addObject("teacherPassword", teacherPassword);
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteQuestion(@RequestParam("btnHidden") String questID) {
		questionId = Long.parseLong(questID);
		answerDAO.remove(questionId);
		questionDAO.remove(questionId);
		ModelAndView model = new ModelAndView("questList");
		model.addObject("teacherName", teacherName);
		model.addObject("teacherPassword", teacherPassword);
		model.addObject("question", questionDAO.getAllQuestion(examId));
		return model;
	}

	@RequestMapping(value = "/createExam", method = RequestMethod.POST)
	public ModelAndView createExam(@RequestParam("Subject") String subject) {
		System.out.println(subject);
		Exam exam = new Exam();
		exam.setTitle(subject);
		examDAO.persist(exam);
		ModelAndView model = new ModelAndView("kcList");
		model.addObject("examList", examDAO.getAllExam());
		return model;
	}

	@RequestMapping(value = "/displayExamResults")
	public ModelAndView displayExamResults(@RequestParam("getId") String examID) {
		// create model of display_question.jsp
		ModelAndView model = new ModelAndView("display_exam_results");

		// input is the examID
		long examId = Long.parseLong(examID);
		List<Score> scoreList = scoreDAO.getAllScoreByExam(examId);
		exam = examDAO.getExam(examId);
		model.addObject("teacherName", teacherName);
		model.addObject("teacherPassword", teacherPassword);
		model.addObject("Title", exam.getTitle());
		model.addObject("ExamID", examId);
		model.addObject("score_list", scoreList);

		return model;
	}

}
