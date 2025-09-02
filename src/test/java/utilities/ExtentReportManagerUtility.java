package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManagerUtility implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports reports;
	public ExtentTest test;

	public String repName;

	public void onStart(ITestContext testContext) {

//		SimpleDateFormat df = new SimpleDateFormat("yy.MM.dd.HH.mm.ss");
//		Date dt = new Date();
//		String currentDateTimeStamp = df.format(dt);

//		OR

		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("Open cart Automation report");
		sparkReporter.config().setReportName("open cart function testing");
		sparkReporter.config().setTheme(Theme.DARK);

		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Application", "opencart");
		reports.setSystemInfo("Module", "Admin");
		reports.setSystemInfo("Sub Module", "Customers");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("Environemnt", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("Operating system ", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("Browser ", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			reports.setSystemInfo("Groups ", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group
		test.log(Status.PASS, result.getName() + " got successfully executed");

		try {
			String imagePath = new BaseClass().captureVisiblePage(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imagePath = new BaseClass().captureVisiblePage(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {

		reports.flush();

//		String pathOfExtentReports = ".\reports\\"+repName;
		String pathOfExtentReports = System.getProperty("user.dir") + "//reports//" + repName;
		File filePath = new File(pathOfExtentReports);

		try {
			Desktop.getDesktop().browse(filePath.toURI());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

//		sent report through email
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
		 * email.setAuthenticator(new
		 * DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		 * email.setSSLOnConnect(true); email.setFrom("pavanoltraining@gmail.com");
		 * //Sender email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver email.attach(url,
		 * "extent report", "please check report..."); email.send(); // send the email }
		 * catch(Exception e) { e.printStackTrace(); }
		 */

	}

}
