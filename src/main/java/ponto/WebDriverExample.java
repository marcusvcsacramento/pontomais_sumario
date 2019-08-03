package ponto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

public class WebDriverExample {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "D:\\Developer\\Tools\\Java\\geckodriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		driver.get("https://app.pontomaisweb.com.br/");
		driver.manage().window().setSize(new Dimension(1936, 1056));
		driver.findElement(By.name("password")).sendKeys("senha");
		driver.findElement(By.name("login")).sendKeys("usuario");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		try {
			Thread.currentThread().sleep(10000);
			driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/nav[1]/div[1]/ul[1]/li[5]/a[2]"))
					.click();
			Thread.currentThread().sleep(5000);
			driver.findElement(By.xpath("//input[@name='start_date']")).sendKeys("2019-07-01");
			driver.findElement(By.xpath("//input[@name='end_date']")).sendKeys("2019-07-30");
			driver.findElement(
					By.xpath("//div[@id=\'content-wrapper\']/div[2]/div/ng-view/div[2]/div/div/div/div[3]/button/i"))
					.click();
			Thread.currentThread().sleep(5000);

			List<WebElement> rows = driver.findElements(By.xpath(
					"/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/ng-view[1]/div[2]/div[2]/table[1]/tbody/tr"));
			System.out.println(rows.size());
			for (int i = 1; i < rows.size() + 1; i++) {
				WebElement diaElement = driver.findElement(By.xpath(
						"/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/ng-view[1]/div[2]/div[2]/table[1]/tbody[1]/tr["
								+ i + "]/td[1]/div[1]/div[2]"));
				driver.findElement(By.xpath("//tr[" + i + "]/td[14]/div/button[2]/i[1]")).click();

				Thread.currentThread().sleep(5000);

				String dia = diaElement.getText();
				String horas = "";
				List<WebElement> rowsInternal = driver
						.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div/div[1]/div[1]"));
				for (int t = 1; t <= rowsInternal.size(); t++) {
					// Thread.currentThread().sleep(1000);

					try {
						WebElement horaElement = driver.findElement(
								By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/div[" + t + "]/div[1]/div[1]"));
						horas = horas + horaElement.getText() + ";";

					} catch (Exception e) {
						horas = ";Sem lançamento";
					}

				}
				System.out.println(dia.substring(dia.length()-6) + ";" + horas);
				driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[4]/button[1]")).click();

			}

			// WebElement e =
			// driver.findElement(By.xpath("//tr[]//td[14]//div[1]//button[2]"));
			// e.equals(driver.switchTo().activeElement());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
