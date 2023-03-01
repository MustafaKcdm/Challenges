package Challenge_01;

import Utility.BaseDriver;
import Utility.My_Func;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class Challenge01 extends BaseDriver {

    @Test
    public void Test() {

        driver.navigate().to("https://www.saucedemo.com/");
        WebElement us = driver.findElement(By.xpath("//div[@id='login_credentials']"));
        ArrayList<String> usernames = new ArrayList<>(Arrays.asList(us.getText().substring(23).split("user", 0)));
        for (int i = 0; i < usernames.size(); i++) {
            usernames.set(i, usernames.get(i).trim().concat("user"));
        }
        ArrayList<String> successfulUser = new ArrayList<>();
        ArrayList<String> unSuccessfulUser = new ArrayList<>();
        for (int i = 0; i < usernames.size(); i++) {

            WebElement usernameTB = driver.findElement(By.xpath("//input[@placeholder='Username']"));
            WebElement passwordTB = driver.findElement(By.xpath("//input[@placeholder='Password']"));
            WebElement button = driver.findElement(By.xpath("//input[@class='submit-button btn_action']"));
            String currentUser = usernames.get(i);
            My_Func.Bekle(1);
            usernameTB.sendKeys(currentUser);
            passwordTB.clear();
            My_Func.Bekle(1);
            passwordTB.sendKeys("secret_sauce");
            My_Func.Bekle(1);
            button.click();
            My_Func.Bekle(1);
            if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
                successfulUser.add(usernames.get(i));
                driver.navigate().back();
            } else {
                unSuccessfulUser.add(usernames.get(i));
                usernameTB.clear();
                My_Func.Bekle(2);
                passwordTB.clear();
            }
        }
        Assert.assertEquals("Giriş İşlemleri Başarısız", "https://www.saucedemo.com/", driver.getCurrentUrl());
        System.out.println("---------------- TEST SUMMARY ----------------");
        for (int i = 0; i < successfulUser.size(); i++) {
            System.out.println((i + 1) + ". User With Successful Login = " + successfulUser.get(i));
        }
        System.out.println("----------------------------------------------");
        for (int i = 0; i < unSuccessfulUser.size(); i++) {
            System.out.println((i + 1) + ". User With Failed Login = " + unSuccessfulUser.get(i));
        }
        System.out.println("----------------------------------------------");

    }
}
