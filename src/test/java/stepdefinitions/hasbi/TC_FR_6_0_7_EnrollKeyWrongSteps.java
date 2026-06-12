package stepdefinitions.hasbi;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CourseOverviewPage;
import pages.DashboardPage;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;

public class TC_FR_6_0_7_EnrollKeyWrongSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    private DashboardPage dashboardPage = new DashboardPage(driver);
    private CourseOverviewPage courseOverviewPage = new CourseOverviewPage(driver);

    @Then("sistem menampilkan pesan error kode pendaftaran salah")
    public void sistemMenampilkanPesanErrorKodePendaftaranSalah() {
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // Cari elemen error / modal yang muncul setelah memasukkan kode salah
        List<WebElement> errorElements = driver.findElements(By.cssSelector(
            ".modal, .modal-body, .modal-content, .alert-danger, " +
            "[class*='error'], [class*='Error'], [class*='modal'], [class*='Modal']"
        ));

        boolean errorDitemukan = false;
        System.out.println("=== TC-FR-6.0.7 HASIL ===");
        for (WebElement el : errorElements) {
            try {
                if (el.isDisplayed() && !el.getText().trim().isEmpty()) {
                    System.out.println("  Pesan error: " + el.getText());
                    errorDitemukan = true;
                }
            } catch (Exception ignored) {}
        }

        // Fallback: cari teks error di seluruh halaman
        if (!errorDitemukan) {
            List<WebElement> errTeks = driver.findElements(By.xpath(
                "//*[contains(text(),'salah') or contains(text(),'Salah') or contains(text(),'tidak valid') " +
                "or contains(text(),'Pendaftaran gagal') or contains(text(),'error')]"
            ));
            for (WebElement el : errTeks) {
                if (el.isDisplayed()) {
                    System.out.println("  Pesan: " + el.getText());
                    errorDitemukan = true;
                }
            }
        }

        Assert.assertTrue(
            "Tidak ada pesan error yang muncul setelah memasukkan kode pendaftaran salah!",
            errorDitemukan
        );
        System.out.println("PASS: TC-FR-6.0.7 Pesan error kode salah berhasil terdeteksi.");
    }

    @Then("user pelajar tetap berada di halaman Course Overview")
    public void userPelajarTetapBeradaDiHalamanCourseOverview() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
            "User seharusnya tetap di halaman Course Overview! URL sekarang: " + currentUrl,
            currentUrl.contains("course-overview") || currentUrl.contains("course")
        );
        System.out.println("PASS: User masih di halaman Course Overview. URL: " + currentUrl);
    }
}
