/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.dto;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Imalka Gunawardana
 */
public class PaperDTO extends SuperDTO {

    private String url;
    private int tid;
    private String time;
    private String teacherName;
    private String semester;
    private String subject;
    private String questionPaper;
    private int questionsCount;
    private List<String> questionNo;
    private HashMap<String, String> questionWithNo;
    private HashMap<String, Integer> answersCountWithNo;
    private HashMap<String, List<String>> answersWithNo;

    public PaperDTO() {
    }

    public PaperDTO(String url, String teacherName, String semester, String subject, int questionsCount, List<String> questionNo, HashMap<String, String> questionWithNo, HashMap<String, Integer> answersCountWithNo, HashMap<String, List<String>> answersWithNo) {
        this.url = url;
        this.teacherName = teacherName;
        this.semester = semester;
        this.subject = subject;
        this.questionsCount = questionsCount;
        this.questionNo = questionNo;
        this.questionWithNo = questionWithNo;
        this.answersCountWithNo = answersCountWithNo;
        this.answersWithNo = answersWithNo;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * @param teacherName the teacherName to set
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the questionsCount
     */
    public int getQuestionsCount() {
        return questionsCount;
    }

    /**
     * @param questionsCount the questionsCount to set
     */
    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    /**
     * @return the questionNo
     */
    public List<String> getQuestionNo() {
        return questionNo;
    }

    /**
     * @param questionNo the questionNo to set
     */
    public void setQuestionNo(List<String> questionNo) {
        this.questionNo = questionNo;
    }

    /**
     * @return the questionWithNo
     */
    public HashMap<String, String> getQuestionWithNo() {
        return questionWithNo;
    }

    /**
     * @param questionWithNo the questionWithNo to set
     */
    public void setQuestionWithNo(HashMap<String, String> questionWithNo) {
        this.questionWithNo = questionWithNo;
    }

    /**
     * @return the answersCountWithNo
     */
    public HashMap<String, Integer> getAnswersCountWithNo() {
        return answersCountWithNo;
    }

    /**
     * @param answersCountWithNo the answersCountWithNo to set
     */
    public void setAnswersCountWithNo(HashMap<String, Integer> answersCountWithNo) {
        this.answersCountWithNo = answersCountWithNo;
    }

    /**
     * @return the answersWithNo
     */
    public HashMap<String, List<String>> getAnswersWithNo() {
        return answersWithNo;
    }

    /**
     * @param answersWithNo the answersWithNo to set
     */
    public void setAnswersWithNo(HashMap<String, List<String>> answersWithNo) {
        this.answersWithNo = answersWithNo;
    }

    /**
     * @return the questionPaper
     */
    public String getQuestionPaper() {
        return questionPaper;
    }

    /**
     * @param questionPaper the questionPaper to set
     */
    public void setQuestionPaper(String questionPaper) {
        this.questionPaper = questionPaper;
    }

    /**
     * @return the tid
     */
    public int getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(int tid) {
        this.tid = tid;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
}
