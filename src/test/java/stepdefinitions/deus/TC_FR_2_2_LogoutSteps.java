package stepdefinitions.deus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.DashboardPage;
import utils.DriverFactory;

public class TC_FR_2_2_LogoutSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private DashboardPage dashboardPage = new DashboardPage(driver);

    @When("pelajar mengklik nama akun pada header")
    public void pelajarMengklikNamaAkunPadaHeader() {
        dashboardPage.clickAccountDropdown();
    }

    @When("pelajar mengklik tombol Keluar pada dropdown")
    public void pelajarMengklikTombolKeluarPadaDropdown() {
        dashboardPage.clickLogoutButton();
    }

    @Then("sistem mengarahkan ke halaman login setelah logout")
    public void sistemMengarahkanKeHalamanLoginSetelahLogout() {
        Assert.assertTrue(
            "Seharusnya tidak lagi di halaman dashboard setelah logout. URL aktual: " + driver.getCurrentUrl(),
            dashboardPage.isRedirectedToLoginPage()
        );
    }

    @Then("session pelajar telah dihapus setelah logout")
    public void sessionPelajarTelahDihapusSetelahLogout() {
        Assert.assertFalse(
            "Session masih aktif - cookie 'MoodleSession' seharusnya tidak ada",
            driver.manage().getCookieNamed("MoodleSession") != null
        );
    }

    @Then("akses langsung ke dashboard diredirect ke halaman login")
    public void aksesLangsungKeDashboardDiredirectKeHalamanLogin() {
        dashboardPage.navigateToDashboardDirectly();
        Assert.assertTrue(
            "Setelah akses langsung ke dashboard tanpa session, seharusnya diredirect ke halaman login",
            dashboardPage.isSessionCleared()
        );
    }
}
