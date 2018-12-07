package com.sqs.weather;
//import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Assert;
//import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AppTest {

    private static WebDriver driver;
    private String cityName = "George";

    //TODO  Remove Any sleep statements
    //TODO Be able to choose between Firefox and Chrome
    //TODO  Create XML file

    @Test // http://www.weathersa.co.za/city-pages/ && //http://weather.news24.com/sa/george
    public void sameWeatherForecast() throws InterruptedException {

        //createXML.createPrettyXMLUsingDOM();
       // createXMLWeather.createPrettyXMLUsingDOM();
        //System.exit(0);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\6495\\IdeaProjects\\Assignment\\src\\Additionals\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        int[] tempWeatherSAMax;
        int[] tempWeatherSAMin;
        int[] tempnews24Max;
        int[] tempnews24Min;

        // check if weatherSA is online
        String baseUrl = "http://www.weathersa.co.za/";
        String expectedTitle = "Forecast";
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        System.out.println("WeatherSA: Actual Title is: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

        // get weather from weatherSA
        weatherSAObject weatherSA = new weatherSAObject(driver);
        tempWeatherSAMax = weatherSA.populate(cityName, "Max temperature");
        tempWeatherSAMin = weatherSA.getParam("Min temperature");

        //System.out.println(Arrays.toString(tempWeatherSAMax));
       // System.out.println(Arrays.toString(tempWeatherSAMin));

        // news24
        baseUrl = "http://weather.news24.com/";
        expectedTitle = "";
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        System.out.println("weather24: Actual Title is: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

        // get weather from news24
        news24Object news24 = new news24Object(driver);
        tempnews24Max = news24.populate(cityName, "High Temp");
        tempnews24Min = news24.getParam("Low Temp");
        //System.out.println(Arrays.toString(tempnews24Max));
        //System.out.println(Arrays.toString(tempnews24Min));

        // compare temperature
        String[] nextDays = {"Tomorrow", "In Two days", "In Three days", "In four days"};

        System.out.print(String.format(" %13s ", " "));
        System.out.print(String.format("%3s ", "WSA"));
        System.out.println(String.format(" %3s ", "W24"));
        System.out.print(String.format(" %13s ", " "));
        System.out.print(String.format("%3s ", "Min"));
        System.out.println(String.format(" %3s ", "Min"));

        for (int i = 1; i<5;i++) {
            System.out.print(String.format("|%-13s|", nextDays[i - 1]));
            System.out.print(String.format("%3s|", tempWeatherSAMin[i - 1]) + String.format("%3s|", tempnews24Min[i - 1]));
            if (!Objects.equals(tempWeatherSAMin[i - 1], tempnews24Min[i - 1])) {
                System.out.print(" Different");
            }
            System.out.println(" ");
        }

        System.out.print("\n\n" + String.format(" %13s ", " "));
        System.out.print(String.format("%3s ", "WSA"));
        System.out.println(String.format(" %3s ", "W24"));
        System.out.print(String.format(" %13s ", " "));
        System.out.print(String.format("%3s ", "Max"));
        System.out.println(String.format(" %3s ", "Max"));

        for (int i = 1; i<5;i++){
            System.out.print(String.format("|%-13s|", nextDays[i-1]));
            System.out.print(String.format("%3s|", tempWeatherSAMax[i-1]) + String.format("%3s|", tempnews24Max[i-1]));
            if (!Objects.equals(tempWeatherSAMax[i - 1], tempnews24Max[i - 1])) {
                System.out.print(" Different");
            }
            System.out.println(" ");
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}