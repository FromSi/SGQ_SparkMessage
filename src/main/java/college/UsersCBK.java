package college;

public class UsersCBK {
    private boolean notification;
    private int idUser;

    public UsersCBK(int idUser) {
        this.notification = false;
        this.idUser = idUser;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
