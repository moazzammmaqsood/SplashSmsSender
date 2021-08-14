package splash.com.modal;

public class Reason     {

        String number;
        String status;

        @Override
        public String toString() {
                return "Reason{" +
                        "number='" + number + '\'' +
                        ", status='" + status + '\'' +
                        '}';
        }

        public Reason(String number, String status) {
                this.number = number;
                this.status = status;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
}
