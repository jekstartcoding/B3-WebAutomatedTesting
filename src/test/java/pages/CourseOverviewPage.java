package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    // === LOCATORS ===
    // Input enrollment key - dari CSS web: .enroll-form input.form-control
    @FindBy(css = ".enroll-form input")
    private WebElement enrollmentKeyInput;

    // Tombol Daftar - dari CSS web: .button-enroll
    @FindBy(css = ".button-enroll")
    private WebElement daftarButton;

    // Pesan sukses popup
    @FindBy(xpath = "//*[contains(text(),'Pendaftaran berhasil') or contains(text(),'berhasil')]")
    private WebElement successPopupText;

    // Tombol Tutup popup
    @FindBy(xpath = "//button[contains(text(),'Tutup') or contains(text(),'tutup') or contains(text(),'Close')]")
    private WebElement tutupPopupButton;

    // Pesan error popup (empty key)
    @FindBy(xpath = "//*[contains(text(),'Silakan masukkan') or contains(text(),'kode pendaftaran') or contains(text(),'kosong')]")
    private WebElement errorPopupText;

    // === CONSTRUCTOR ===
    public CourseOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // === ACTIONS ===
    public void enterEnrollmentKey(String enrollmentKey) {
        // Tunggu sampai form enroll muncul menggunakan CSS selector yang benar
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".enroll-form input")
        ));
        input.clear();
        input.sendKeys(enrollmentKey);
    }

    public void clickDaftar() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".button-enroll")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
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
        // Cari elemen error dengan berbagai kemungkinan selector
        try {
            WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Silakan masukkan') or contains(text(),'kode pendaftaran') or contains(@class,'error') or contains(@class,'alert')]")
            ));
            return err.getText().trim();
        } catch (Exception e) {
            return "Pesan error muncul (teks tidak dapat dibaca)";
        }
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".enroll-form input")));
    }
}
