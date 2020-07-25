package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type("firstname", contactData.getFirstname());
    type("middlename", contactData.getMiddlename());
    type("lastname", contactData.getLastname());
    type("nickname", contactData.getNickname());
    type("title", contactData.getTitle());
    type("company", contactData.getCompany());
    type("address", contactData.getAddress());
    type("home", contactData.getHome());
    type("mobile", contactData.getMobile());
    type("work", contactData.getWorkphone());
    type("email", contactData.getEmail());
  }

  public void fillNewGroup(String locator, String groupname) {
    click(By.name(locator));
    new Select(wd.findElement(By.name(locator))).selectByVisibleText(groupname);
    click(By.xpath("(//option[@value='1'])[3]"));
  }

  public void initCreateContact() {
    click(By.linkText("add new"));
  }

  public void returnContactPage() {
    click(By.linkText("home page"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDeletion() {
    acceptModalWindow();
  }

  public void cancelDeletion() {
    cancelModalWindow();
  }

  public void editContact() {
    click(By.xpath("(//img[@alt='Edit'])[4]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}