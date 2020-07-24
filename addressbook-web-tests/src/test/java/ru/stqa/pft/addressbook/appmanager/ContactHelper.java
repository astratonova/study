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
    click(By.name("new_group"));
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroupname());
    click(By.xpath("(//option[@value='1'])[3]"));
  }

  public void initCreateContact() {
    click(By.linkText("add new"));
  }

  public void returnContactPage() {
    click(By.linkText("home page"));
  }
}