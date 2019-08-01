package channeldemo.videomessage;

import java.util.List;

/**
 * @author liuhai
 * @date 2019/5/21 15:31
 */
public class Message {


    /**
     * SiID : 34105639020006
     * Authenticator : FFD4A81CBCF238CD6618B1F1BE4DF735
     * Date : 2019-05-21 17:28:19
     * ReportList : [{"MsgID":"5cda29b617171","Phone":"18906577722","State":"RECEIVD","TransID":"341056390200061558430776b1U16","Amount":1842}]
     */

    private String SiID;
    private String Authenticator;
    private String Date;
    private List<ReportListBean> ReportList;

    public String getSiID() {
        return SiID;
    }

    public void setSiID(String SiID) {
        this.SiID = SiID;
    }

    public String getAuthenticator() {
        return Authenticator;
    }

    public void setAuthenticator(String Authenticator) {
        this.Authenticator = Authenticator;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public List<ReportListBean> getReportList() {
        return ReportList;
    }

    public void setReportList(List<ReportListBean> ReportList) {
        this.ReportList = ReportList;
    }

    public  class ReportListBean {
        /**
         * MsgID : 5cda29b617171
         * Phone : 18906577722
         * State : RECEIVD
         * TransID : 341056390200061558430776b1U16
         * Amount : 1842
         */

        private String MsgID;
        private String Phone;
        private String State;
        private String TransID;
        private int Amount;

        public String getMsgID() {
            return MsgID;
        }

        public void setMsgID(String MsgID) {
            this.MsgID = MsgID;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getTransID() {
            return TransID;
        }

        public void setTransID(String TransID) {
            this.TransID = TransID;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        @Override
        public String toString() {
            return "ReportListBean{" +
                    "MsgID='" + MsgID + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", State='" + State + '\'' +
                    ", TransID='" + TransID + '\'' +
                    ", Amount=" + Amount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Json{" +
                "SiID='" + SiID + '\'' +
                ", Authenticator='" + Authenticator + '\'' +
                ", Date='" + Date + '\'' +
                ", ReportList=" + ReportList.toString() +
                '}';
    }
}
