package ponto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import util.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

public class GeraPonto {
	
	
	public static Util util;
	
	
	
	public static void main(String[] args) {
		System.out.println("Passar na linha de comando: properties usuário senha data_inicio(AAAA-MM-DD) data_fim(AAAA-MM-DD)");
		util = new Util();
		Properties prop = util.getProperties(args[0]);
		System.setProperty("webdriver.gecko.driver", prop.getProperty("geckodriver_path"));
		
		
		
		WebDriver driver = new FirefoxDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().setSize(new Dimension(1936, 1056));		
		System.out.println("Realizando Login");
		driver.findElement(By.name(prop.getProperty("login_element"))).sendKeys(args[1]);
		driver.findElement(By.name(prop.getProperty("password_element"))).sendKeys(args[2]);
		driver.findElement(By.cssSelector(prop.getProperty("login_btn_cssSelector"))).click();
		try {
			Thread.currentThread().sleep(Integer.parseInt(prop.getProperty("sleep_maior")));
			System.out.println("Acessando lista do ponto");
			driver.findElement(By.xpath(prop.getProperty("meu_ponto_click")))
					.click();
			Thread.currentThread().sleep(Integer.parseInt(prop.getProperty("sleep_menor")));
			System.out.println("Informando as datas");
			driver.findElement(By.xpath(prop.getProperty("input_start_date"))).sendKeys(args[3]);
			driver.findElement(By.xpath(prop.getProperty("input_end_date"))).sendKeys(args[4]);
			System.out.println("Clicando no Filtro");
			driver.findElement(
					By.xpath(prop.getProperty("filtro_click")))
					.click();
			Thread.currentThread().sleep(Integer.parseInt(prop.getProperty("sleep_menor")));
			
			List<WebElement> rows = driver.findElements(By.xpath(
					prop.getProperty("linhas_filtradas")));
					 
			System.out.println("Total de Registros:" + rows.size());
			System.out.println("Capturando Dados");
			for (int i = 1; i < rows.size() + 1; i++) {
				WebElement diaElement = driver.findElement(By.xpath(
						prop.getProperty("elemento_dia_pt1")
								+ i + prop.getProperty("elemento_dia_pt2")));
				
				driver.findElement(By.xpath(prop.getProperty("ver_dia_btn_pt1") + i + prop.getProperty("ver_dia_btn_pt2"))).click();

				Thread.currentThread().sleep(Integer.parseInt(prop.getProperty("sleep_menor")));

				String dia = diaElement.getText();
				String horas = "";
				List<WebElement> rowsInternal = driver
						.findElements(By.xpath(prop.getProperty("horas_dia")));
				for (int t = 1; t <= rowsInternal.size(); t++) {
					// Thread.currentThread().sleep(1000);

					try {
						WebElement horaElement = driver.findElement(
								By.xpath(prop.getProperty("elemento_hora_pt1") + t + prop.getProperty("elemento_hora_pt2")));
						horas = horas + horaElement.getText() + ";";

					} catch (Exception e) {
						horas = ";Sem lançamento";
					}

				}
				System.out.println(dia.substring(dia.length()-6) + ";" + horas.replace("", ""));
				driver.findElement(By.xpath(prop.getProperty("sair_dia_click"))).click();

			}

			// WebElement e =
			// driver.findElement(By.xpath("//tr[]//td[14]//div[1]//button[2]"));
			// e.equals(driver.switchTo().activeElement());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Terminando o Programa");
		driver.close();
	}
}
