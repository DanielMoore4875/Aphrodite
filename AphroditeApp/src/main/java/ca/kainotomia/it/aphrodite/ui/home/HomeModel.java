package ca.kainotomia.it.aphrodite.ui.home;

public class HomeModel {
    public String title;

    public HomeModel() {
    }

    public HomeModel(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
