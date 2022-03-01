/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class IssueBookBean {
    
    private String cellNo;
    private String studentId;
    private String studentName;
    private int studentMobile;
    private Date issuedDate;
    private String returnStatus;
    
    public IssueBookBean(){
    
    }
    
    public IssueBookBean(String cellNo, String studentId, String studentName, int studentMobile) {
        this.cellNo = cellNo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMobile = studentMobile;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(int studentMobile) {
        this.studentMobile = studentMobile;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}
