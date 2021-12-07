package ca.kainotomia.it.aphrodite.ui.home;

public class HomeModel {
    public String title;

    // THIS IS NEEDED FOR FIREBASE, DO NOT DELETE
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
