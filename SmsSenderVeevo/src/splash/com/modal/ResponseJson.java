package splash.com.modal;

public class ResponseJson {

    String status;
    Reason reason;

    public ResponseJson(String status, Reason reason) {
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseJson{" +
                "status='" + status + '\'' +
                ", reason=" + reason +
                '}';
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }
}
