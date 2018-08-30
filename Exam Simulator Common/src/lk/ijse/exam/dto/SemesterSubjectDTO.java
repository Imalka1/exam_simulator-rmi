/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.exam.dto;

/**
 *
 * @author Imalka Gunawardana
 */
public class SemesterSubjectDTO extends SuperDTO {

    private int tid;
    private String subjectName;
    private String semesterName;
    private String teacherName;

    public SemesterSubjectDTO() {
    }

    public SemesterSubjectDTO(int tid, String subjectName, String semesterName, String teacherName) {
        this.tid = tid;
        this.subjectName = subjectName;
        this.semesterName = semesterName;
        this.teacherName = teacherName;
    }

    /**
     * @return the semesterName
     */
    public String getSemesterName() {
        return semesterName;
    }

    /**
     * @param semesterName the semesterName to set
     */
    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
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
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
