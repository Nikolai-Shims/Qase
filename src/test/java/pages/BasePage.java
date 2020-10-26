package pages;

public abstract class BasePage {

    public static int timeout = 3000;

    public abstract BasePage isPageOpened();
}
