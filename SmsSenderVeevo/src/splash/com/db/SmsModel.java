package splash.com.db;

import java.util.Date;

public class SmsModel {
    int id;
    int userid;
    String phoneno;
    String msg;
    String status;
    Date senttime;
    String response;

    public SmsModel() {
    }


    public SmsModel(int id, int userid, String phoneno, String msg, String status, Date senttime, String response) {
        this.id = id;
        this.userid = userid;
        this.phoneno = phoneno;
        this.msg = msg;
        this.status = status;
        this.senttime = senttime;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "SmsModel{" +
                "id=" + id +
                ", userid=" + userid +
                ", phoneno='" + phoneno + '\'' +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", senttime=" + senttime +
                ", response='" + response + '\'' +
                '}';
    }
}
