package college;

public class UsersCBK {
    private boolean notification;
    private String idUser;

    public UsersCBK(String idUser) {
        this.notification = false;
        this.idUser = idUser;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
