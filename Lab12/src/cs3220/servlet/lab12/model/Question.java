package cs3220.servlet.lab12.model;

public class Question {

	String description,answerA,answerB,answerC;
	int correctAnswer;
	public Question(String description, String answerA, String answerB, String answerC, int correctAnswer) {
		super();
		this.description = description;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.correctAnswer = correctAnswer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
