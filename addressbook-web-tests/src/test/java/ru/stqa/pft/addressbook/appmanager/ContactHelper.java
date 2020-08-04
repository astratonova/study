package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, Boolean creation) {
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
    if (creation) {
      click(By.name("new_group"));
      //создается контакт с group4: здесь проверяется символ 4 в списке групп,
      // если в создании контакта передавать другую группу, то надо изменить символ
      if((wd.findElement(By.name("new_group")).getText().contains("4"))){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
    click(By.xpath("(//img[@alt='Edit'])"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return !isElementPresent(By.xpath("(//img[@alt='Edit'])"));
  }

  public void createNewContact(ContactData contactData, boolean creation) {
    initCreateContact();
    fillContactForm(contactData, creation);
    submitContact();
    returnContactPage();
  }
}