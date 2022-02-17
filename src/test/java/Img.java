import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Test
public class Img {
    static Xdata excel = new Xdata("/home/knoldus/Desktop/ImgAssignment/data/Imagedata.xlsx");
    static WebDriver driver;
    public static void main(String[] args) throws TimeoutException{

        System.setProperty("webdriver.chrome.driver","/home/knoldus/Desktop/ImgAssignment/drivers/chromedriver");
        driver= new ChromeDriver();

        //Maximize Browser
        driver.manage().window().maximize();

        //Implicit Timeout
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);

        driver.get("http://omayo.blogspot.com/?m=0");
        driver.findElement(By.id("uploadfile")).sendKeys(excel.getData(0,0,0));
        driver.quit();

        //Firefox Browser

        System.setProperty("webdriver.gecko.driver","/home/knoldus/Desktop/ImgAssignment/drivers/geckodriver");
        driver= new FirefoxDriver();

        //Maximize Browser
        driver.manage().window().maximize();

        //Implicit Timeout
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);

        driver.get("http://omayo.blogspot.com/?m=0");
        driver.findElement(By.id("uploadfile")).sendKeys(excel.getData(0,0,0));
        driver.quit();

        //Headless Browser

        System.setProperty("webdriver.chrome.driver","/home/knoldus/Desktop/ImgAssignment/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver= new ChromeDriver(options);

        //Maximize Browser
        driver.manage().window().maximize();

        //Implicit Timeout
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);

        driver.get("http://omayo.blogspot.com/?m=0");
        driver.findElement(By.id("uploadfile")).sendKeys(excel.getData(0,0,0));
        driver.quit();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("Image"+result.getName()+".jpeg"));
                    System.out.println("Screenshot taken");
                }
                catch (Exception e)
                {
                    System.out.println("Exception while taking screenshot "+e.getMessage());
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }


        }
    }
}

