package pages;

public abstract class BasePage {
    int timeout = 3000;

    public abstract BasePage isPageOpened();
}
