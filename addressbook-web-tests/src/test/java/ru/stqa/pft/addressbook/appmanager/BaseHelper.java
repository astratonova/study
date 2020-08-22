package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
    this.wd=wd;
  }

  public void click(String locator) {
    click(By.name(locator));
  }

  public void type(String locator, String text) {
    if(text!=null) {
      String existingText= wd.findElement(By.name(locator)).getAttribute("value");
      if (! existingText.equals(text)) {
        click(By.name(locator));
        wd.findElement(By.name(locator)).clear();
        wd.findElement(By.name(locator)).sendKeys(text);
      }
    }
  }
  public void attach(String locator, File file) {
    if(file!=null) {
        wd.findElement(By.name(locator)).sendKeys(file.getAbsolutePath());
      }
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void acceptModalWindow() {
    wd.switchTo().alert().accept();
  }
  public void cancelModalWindow() {
    wd.switchTo().alert().dismiss();
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
