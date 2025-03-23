package lab.servletsjspjstl;

public class BorderBean {
    private boolean isBorderVisible;

    public boolean isBorderVisible() {
        return isBorderVisible;
    }

    public void setBorderVisible(boolean borderVisible) {
        isBorderVisible = borderVisible;
    }

    public void setBorderVisibleFromString(String borderVisible) {
        isBorderVisible = Boolean.parseBoolean(borderVisible);
    }
}
