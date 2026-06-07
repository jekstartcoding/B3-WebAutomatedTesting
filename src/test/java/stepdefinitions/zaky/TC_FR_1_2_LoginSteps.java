package stepdefinitions.zaky;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.DashboardPage;
import utils.DriverFactory;

public class TC_FR_1_2_LoginSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("user pelajar berada di halaman Login")
    public void userPelajarBeradaDiHalamanLogin() {
        loginPage.navigateToLoginPage();
    }

    @When("memasukkan email pelajar {string} dan password valid {string}")
    public void memasukkanEmailPelajarDanPasswordValid(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("menekan tombol Masuk")
    public void menekanTombolMasuk() {
        loginPage.clickLogin();
    }

    @Then("sistem berhasil autentikasi dan diarahkan ke Dashboard Pelajar")
    public void sistemBerhasilAutentikasiDanDiarahkanKeDashboardPelajar() {
        Assert.assertTrue("Dashboard is not displayed", dashboardPage.isDashboardDisplayed());
        Assert.assertTrue("URL does not contain dashboard", driver.getCurrentUrl().contains("dashboard"));
    }

    @Then("header menampilkan menu Beranda, Kursus Saya, Riwayat Kuis, dan Nama Akun")
    public void headerMenampilkanMenuBerandaKursusSayaRiwayatKuisDanNamaAkun() {
        Assert.assertTrue("Header menus are not displayed correctly", dashboardPage.isHeaderMenusDisplayed());
    }
}
