package college;

public class UsersCBK {
    private boolean check;
    private boolean notif;
    private String idUser;

    public UsersCBK(String idUser) {
        this.check = false;
        this.notif = false;
        this.idUser = idUser;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isNotif() {
        return notif;
    }

    public void setNotif(boolean notif) {
        this.notif = notif;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
