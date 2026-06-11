package stepdefinitions.deus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.CourseOverviewPage;
import utils.DriverFactory;

public class TC_FR_6_0_8_EnrollKeyEmptySteps {
    private WebDriver driver = DriverFactory.getDriver();
    private DashboardPage dashboardPage = new DashboardPage(driver);
    private CourseOverviewPage courseOverviewPage = new CourseOverviewPage(driver);

    private String courseOverviewUrl;

    @Given("user pelajar berada di halaman course overview untuk course {string}")
    public void userPelajarBeradaDiHalamanCourseOverviewUntukCourse(String courseName) {
        dashboardPage.isDashboardDisplayed();
        dashboardPage.clickCourse(courseName);
        courseOverviewUrl = driver.getCurrentUrl();
    }

    @When("user pelajar tidak mengisi kode pendaftaran dan menekan tombol Daftar")
    public void userPelajarTidakMengisiKodePendaftaranDanMenekanTombolDaftar() {
        // Tidak memanggil enterEnrollmentKey() - field dibiarkan kosong sesuai test case
        courseOverviewPage.clickDaftar();
    }

    @Then("sistem menampilkan pesan error {string}")
    public void sistemMenampilkanPesanError(String expectedErrorMessage) {
        String actualErrorMessage = courseOverviewPage.getErrorPopupText();
        Assert.assertEquals(
            "Pesan error tidak sesuai dengan yang diharapkan",
            expectedErrorMessage,
            actualErrorMessage
        );
    }

    @Then("user tetap berada di halaman Course Overview")
    public void userTetapBeradaDiHalamanCourseOverview() {
        Assert.assertEquals(
            "URL berubah - user seharusnya tetap di halaman Course Overview",
            courseOverviewUrl,
            driver.getCurrentUrl()
        );
    }
}
