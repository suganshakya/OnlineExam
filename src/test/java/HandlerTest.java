//
//
//import java.util.List;
//
////import np.com.accent.multiplechoice.DataJPAHandler;
//import org.junit.Test;
//
//import np.com.onlineExam.dao.*;
//public class HandlerTest {
///*
//	//public static void main(String[] args) {
//	@Test	
//	public void testHandler(){
//		DataJPAHandler dHandler = new DataJPAHandler();
//		List<Question> list = dHandler.getAllQuestion();
//		
//		if(list.isEmpty()){
//			System.out.println("It is empty.");
//		}
//		int count = 0;
//		for (Question q : list) {
//			System.out.printf("\nQuestion %d : %s", ++count, q.getqText());
//			List<Answer> aList = dHandler.getAnswers(q.getQid());
//			count = 0;
//			for (Answer a : aList) {
//				System.out.printf("\nAnswer %d : %s", ++count, a.getText());
//			}
//		}
//		System.out.println("\n\nBye");	
//		
//		System.out.println("Printing answers to Question2:");		
//		
//		//KnowledgeCheck k = new KnowledgeCheck();
//		//dHandler.addQuestion(k);		
//		dHandler.commit();		
//		dHandler.close();
//	}
//*/
//	@Test
//	public void showExam(){
//		DataJPAHandler dHandler = new DataJPAHandler();
//		//Show all question and answer from KC=3
//		List<Question> list = dHandler.getAllQuestion(3);
//		
//		if(list.isEmpty()){
//			System.out.println("It is empty.");
//		}
//		int count = 0;
//		for (Question q : list) {
//			System.out.printf("\nQuestion %d : %s", ++count, q.getqText());
//			List<Answer> aList = dHandler.getAnswers(q.getQid());
//			count = 0;
//			for (Answer a : aList) {
//				System.out.printf("\nAnswer %d : %s", ++count, a.getText());
//			}
//		}
//		System.out.println("\n\nBye");	
//		
//		//KnowledgeCheck k = new KnowledgeCheck();
//		//dHandler.addQuestion(k);		
//		dHandler.commit();		
//		dHandler.close();
//
//	}
//}
