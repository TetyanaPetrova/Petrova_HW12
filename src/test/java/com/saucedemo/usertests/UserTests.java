package com.saucedemo.usertests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @BeforeClass

    public void beforeClass() {
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
    }


    @Test(priority = 1)
    public void productCheck() {
        Product product1 = new Product(driver.findElement(By.cssSelector("a#item_4_title_link>div")).getText(), driver.findElement(By.xpath("//a[@id='item_4_title_link']/../../div/div[@class='inventory_item_price']")).getText());
        driver.findElement(By.cssSelector("a#item_4_title_link>div")).click();
        String productNameNew = driver.findElement(By.cssSelector(".inventory_details_name")).getText();
        Assert.assertEquals(productNameNew, product1.name);
        String productPriceNew = driver.findElement(By.cssSelector(".inventory_details_price")).getText();
        Assert.assertEquals(productPriceNew, product1.price);
    }

    @Test(priority = 2)
    public void BackButtonCheck() {
        driver.get("https://www.saucedemo.com/v1/inventory.html");
        driver.findElement(By.cssSelector("a#item_4_title_link>div")).click();
        driver.findElement(By.cssSelector(".inventory_details_back_button")).click();
        String products = driver.findElement(By.cssSelector(".product_label")).getText();
        Assert.assertEquals(products, "Products");
    }

    @Test(priority = 3)
    public void BasketCheck() {
        Product product1 = new Product(driver.findElement(By.cssSelector("a#item_4_title_link>div")).getText(), driver.findElement(By.xpath("//a[@id='item_4_title_link']/../../div/div[@class='inventory_item_price']")).getText());
        driver.findElement(By.cssSelector("a#item_4_title_link>div")).click();
        driver.findElement(By.cssSelector("div.inventory_details_desc_container>button")).click();
        String basketCount = driver.findElement(By.cssSelector("span[class^='fa-layers-counter']")).getText();
        Assert.assertEquals(basketCount, "1");
    }
@AfterClass
    public void afterClass(){
    driver.findElement(By.cssSelector(".bm-burger-button")).click();
    driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();

   }
}
