package UPC.noveldownloader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
	public static void WriteToFile(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void WriteToFileAppend(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(conent + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ChromeOptions options=new ChromeOptions();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入书的标题。");
		String name = in.nextLine();
		System.out.println(name);
		System.out.println("请输入书的作者。");
		String author = in.nextLine();
		System.out.println("请输入书的首章url。");
		String url = in.nextLine();
		System.out.println("请输入出错后重试的次数。");
		Integer tryTimes = in.nextInt();
		in.nextLine();
		System.out.println("请输入标题xpath。");
		String xpathTitle = in.nextLine();
		System.out.println("请输入内容xpath。");
		String xpathContent = in.nextLine();
		System.out.println("请输入下一页xpath。");
		String xpathNext = in.nextLine();
		System.out.println("请输入文件名（包括路径）。");
		String fileName = in.nextLine();
		System.out.println("请输入超时时间（秒）：");
		Integer timeout=in.nextInt();
		String markdown = "% " + name + "\r\n\r\n";
		markdown += "% " + author + "\r\n\r\n";
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);
		Integer triedTimes = 0;
		WriteToFile(fileName, markdown);
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		String oldUrl="";
		while (true) {
			markdown="";
			if (triedTimes >= tryTimes) {
				break;
			}
			WebElement eleTitle=null;
			WebElement eleContent=null; 
			WebElement eleNext=null;
			try {
				 eleTitle= driver.findElement(By.xpath(xpathTitle));
				 eleContent= driver.findElement(By.xpath(xpathContent));
				 eleNext= driver.findElement(By.xpath(xpathNext));
			}catch(Exception e){
				e.printStackTrace();
			}
			if (eleTitle == null || eleContent == null) {
				System.out.println("未找到内容，正在重试……");
				triedTimes++;
				driver.navigate().refresh();
				continue;
			} else {
				markdown += "# " + eleTitle.getText() + "\r\n\r\n";
				markdown += eleContent.getText() + "\r\n\r\n";
				System.out.println("Downloading>" + eleTitle.getText());
				WriteToFileAppend(fileName, markdown);
			}
			if (eleNext == null) {
				triedTimes++;
				System.out.println("未找到下一页，正在重试……");
				driver.navigate().refresh();
				continue;
			} else {
				try {
					((JavascriptExecutor)driver).executeScript("arguments[0].click()", eleNext);
				}catch(Exception e) {
					e.printStackTrace();
					triedTimes++;
					driver.navigate().refresh();
					continue;
				}
				if(driver.getCurrentUrl().equals(oldUrl)){
					break;
				}else{
					oldUrl=driver.getCurrentUrl();
				}
			}
			triedTimes = 0;
		}
		System.out.println("OK!");
		driver.close();
		in.close();
	}
}
