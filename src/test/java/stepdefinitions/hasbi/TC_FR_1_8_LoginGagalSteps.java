package stepdefinitions.hasbi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;

public class TC_FR_1_8_LoginGagalSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    private LoginPage loginPage = new LoginPage(driver);

    @Given("user berada di halaman login dengan URL {string}")
    public void userBeradaDiHalamanLoginDenganURL(String url) {
        driver.get(url);
        // Tunggu sampai form login muncul (React butuh waktu render)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
    }

    @When("memasukkan email pelajar {string} dan password tidak valid {string}")
    public void memasukkanEmailPelajarDanPasswordTidakValid(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @Then("sistem menampilkan pesan error bahwa kredensial tidak valid")
    public void sistemMenampilkanPesanErrorBahwaKredensialTidakValid() {
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // Verifikasi: user TIDAK berpindah ke halaman dashboard
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(
            "Login seharusnya GAGAL tapi user malah masuk ke dashboard! URL: " + currentUrl,
            currentUrl.contains("dashboard")
        );

        // Cari pesan error di halaman (apapun bentuknya dari React)
        List<WebElement> errorMessages = driver.findElements(By.xpath(
            "//*[contains(text(),'salah') or contains(text(),'Salah') or contains(text(),'gagal') " +
            "or contains(text(),'Gagal') or contains(text(),'tidak') or contains(text(),'Kesalahan') " +
            "or contains(text(),'invalid') or contains(text(),'ditemukan')]"
        ));

        boolean pesanErrorMuncul = errorMessages.stream().anyMatch(WebElement::isDisplayed);
        System.out.println("=== TC-FR-1.8 HASIL ===");
        for (WebElement el : errorMessages) {
            if (el.isDisplayed() && !el.getText().trim().isEmpty()) {
                System.out.println("  Pesan error: " + el.getText());
            }
        }

        Assert.assertTrue(
            "Tidak ada pesan error yang muncul setelah login gagal!",
            pesanErrorMuncul
        );
    }

    @Then("user tetap berada di halaman login")
    public void userTetapBeradaDiHalamanLogin() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(
            "User seharusnya tetap di halaman login, tapi URL sekarang: " + currentUrl,
            currentUrl.contains("dashboard")
        );
        System.out.println("PASS: User masih di halaman login. URL: " + currentUrl);
    }
}
