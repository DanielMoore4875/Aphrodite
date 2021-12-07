package ca.kainotomia.it.aphrodite.ui.layouts;

public class Model {
    public String title;

    // THIS IS NEEDED FOR FIREBASE, DO NOT DELETE
    public Model() {
    }

    public Model(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

}
