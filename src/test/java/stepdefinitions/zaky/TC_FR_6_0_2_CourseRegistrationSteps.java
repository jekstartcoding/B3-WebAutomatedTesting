package stepdefinitions.zaky;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.CourseOverviewPage;
import utils.DriverFactory;

public class TC_FR_6_0_2_CourseRegistrationSteps {
    private WebDriver driver = DriverFactory.getDriver();
    private DashboardPage dashboardPage = new DashboardPage(driver);
    private CourseOverviewPage courseOverviewPage = new CourseOverviewPage(driver);

    @Given("user berada di halaman course overview untuk {string}")
    public void userBeradaDiHalamanCourseOverviewUntuk(String courseName) {
        dashboardPage.isDashboardDisplayed();
        dashboardPage.clickCourse(courseName);
    }

    @When("user memasukkan kode pendaftaran {string}")
    public void userMemasukkanKodePendaftaran(String enrollmentKey) {
        courseOverviewPage.enterEnrollmentKey(enrollmentKey);
    }

    @When("menekan tombol Daftar")
    public void menekanTombolDaftar() {
        courseOverviewPage.clickDaftar();
    }

    @Then("muncul pop up sukses dengan pesan {string}")
    public void munculPopUpSuksesDenganPesan(String expectedMessage) {
        String actualMessage = courseOverviewPage.getSuccessPopupText();
        Assert.assertEquals("Popup success message mismatch", expectedMessage, actualMessage);
    }

    @Then("klik tombol Tutup mengarahkan ke halaman detail course.")
    public void klikTombolTutupMengarahkanKeHalamanDetailCourse() {
        courseOverviewPage.clickTutupPopup();
    }
}
