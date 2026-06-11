package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CourseOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='enrollmentKey' or @name='enrollmentKey' or @placeholder='Masukkan Kode Pendaftaran' or @type='password' or contains(@placeholder, 'Pendaftaran')]")
    private WebElement enrollmentKeyInput;

    @FindBy(xpath = "//button[text()='Daftar' or contains(text(),'Daftar') or @type='submit']")
    private WebElement daftarButton;

    @FindBy(xpath = "//*[contains(text(), 'Pendaftaran berhasil. Anda sekarang dapat mengakses materi')]")
    private WebElement successPopupText;

    @FindBy(xpath = "//button[text()='Tutup' or contains(text(),'Tutup')]")
    private WebElement tutupPopupButton;

    @FindBy(xpath = "//*[contains(text(),'Silakan masukkan kode pendaftaran') or contains(@class,'alert') and contains(text(),'kode')]")
    private WebElement errorPopupText;

    public CourseOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void enterEnrollmentKey(String enrollmentKey) {
        wait.until(ExpectedConditions.visibilityOf(enrollmentKeyInput));
        enrollmentKeyInput.clear();
        enrollmentKeyInput.sendKeys(enrollmentKey);
    }

    public void clickDaftar() {
        wait.until(ExpectedConditions.elementToBeClickable(daftarButton));
        daftarButton.click();
    }

    public String getSuccessPopupText() {
        wait.until(ExpectedConditions.visibilityOf(successPopupText));
        return successPopupText.getText().trim();
    }

    public void clickTutupPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(tutupPopupButton));
        tutupPopupButton.click();
    }

    public String getErrorPopupText() {
        wait.until(ExpectedConditions.visibilityOf(errorPopupText));
        return errorPopupText.getText().trim();
    }

    public boolean isOnCourseOverviewPage(String expectedUrlFragment) {
        try {
            wait.until(driver -> driver.getCurrentUrl().contains(expectedUrlFragment));
            return driver.getCurrentUrl().contains(expectedUrlFragment);
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToCourseOverview(String courseUrl) {
        driver.get(courseUrl);
        wait.until(ExpectedConditions.visibilityOf(enrollmentKeyInput));
    }
}
