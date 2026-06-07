package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h3.greeting-title")
    private WebElement greetingTitle;

    @FindBy(xpath = "//a[contains(.,'student') or contains(.,'zaky') or contains(.,'aliyashfi') or contains(.,'jeki') or contains(.,'Ahmad Joni') or contains(@class,'dropdown-toggle')]")
    private WebElement accountDropdown;

    @FindBy(xpath = "//button[text()='Keluar']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[contains(text(),'Kursus Saya')]")
    private WebElement kursusSayaLink;

    @FindBy(xpath = "//a[contains(text(),'Beranda')]")
    private WebElement berandaLink;

    @FindBy(xpath = "//a[contains(text(),'Riwayat Kuis')]")
    private WebElement riwayatKuisLink;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(greetingTitle));
            waitForLoading();
            return greetingTitle.getText().contains("Hai,");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHeaderMenusDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(berandaLink));
            wait.until(ExpectedConditions.visibilityOf(kursusSayaLink));
            wait.until(ExpectedConditions.visibilityOf(riwayatKuisLink));
            wait.until(ExpectedConditions.visibilityOf(accountDropdown));
            return berandaLink.isDisplayed() &&
                   kursusSayaLink.isDisplayed() &&
                   riwayatKuisLink.isDisplayed() &&
                   accountDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAccountDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));
        accountDropdown.click();
        // Allow a small delay for dropdown transition
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        waitForLoading();
    }

    public void navigateToKursusSaya() {
        wait.until(ExpectedConditions.elementToBeClickable(kursusSayaLink));
        kursusSayaLink.click();
        waitForLoading();
    }

    public void navigateToBeranda() {
        wait.until(ExpectedConditions.elementToBeClickable(berandaLink));
        berandaLink.click();
        waitForLoading();
    }

    public void clickCourse(String courseName) {
        WebElement courseCard = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h6[text()='" + courseName + "']/ancestor::div[contains(@class,'card')]")
        ));
        courseCard.click();
        waitForLoading();
    }

    private void waitForLoading() {
        try {
            Thread.sleep(1500);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Loading')]")));
            Thread.sleep(500);
        } catch (Exception e) {
            // Ignore
        }
    }
}
