package com.example.testukrclock;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public CheckBox check1;
    public CheckBox check2;
    public CheckBox check3;
    public Label questionText;
    public Button nextQuestion;
    @FXML
    private Label welcomeText;

    List<Question> questionList;
    private int counterQuestion;
    private int counterCorrectAnswers;

    public void initialize(){
        counterQuestion = 0;
        counterCorrectAnswers = 0;
       questionList =  new ArrayList<>();
        Question question0 = new Question("13:25",
                "Тринадцята година двадцять п'ять хвилин",
                "Двадцять п'ята хвилина по тринадцятій",
                "Двадцять п'ята хвилина на чотирнадцяту",
                "111");

        Question question1 = new Question("13:05",
                "Тринадцята година п'ять хвилин",
                "П'ята хвилина по тринадцятій",
                "П'ята хвилина на тринадцяту",
                "110");

        questionList.add(question0);
        questionList.add(question1);
    }

    @FXML
    protected void onHelloButtonClick() {
        String check = translate(check1.isSelected()) + "\n"
                + check2.isSelected() + "\n"
                + check3.isSelected();
        welcomeText.setText(check);
        check1.setText("aaaaaaaa");
    }

    private String translate(boolean selected) {
        return (selected) ? "Обрано" : "Не обрано";
        //Тернарный оператор
    }

    public void onNextQuestion(ActionEvent actionEvent) {
        check1.setVisible(true);
        check2.setVisible(true);
        check3.setVisible(true);
        checkAnswer();
        if(counterQuestion < questionList.size()){

            questionText.setText((counterQuestion + 1) + ") " + questionList.get(counterQuestion).getQuestionText());
            check1.setText(questionList.get(counterQuestion).getAnswer1());
            check2.setText(questionList.get(counterQuestion).getAnswer2());
            check3.setText(questionList.get(counterQuestion).getAnswer3());
            nextQuestion.setText("Наступне питання");
            counterQuestion++;
        }else {
            showResults();
        }
    }

    private void checkAnswer() {
        if (counterQuestion<1) return;
        String answerUser = "";
        if(check1.isSelected()) answerUser+="1";
        else answerUser+="0";
        if(check2.isSelected()) answerUser+="1";
        else answerUser+="0";
        if(check3.isSelected()) answerUser+="1";
        else answerUser+="0";
        if (answerUser.equals(questionList.get(counterQuestion-1).getCorrectAnswer())){
            counterCorrectAnswers++;
        }
    }

    private void showResults() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Тест з української мови");
        alert.setHeaderText("Тест завершено");
        alert.setContentText("Результати: "+counterCorrectAnswers +" / " + questionList.size());
        alert.showAndWait();
    }
}