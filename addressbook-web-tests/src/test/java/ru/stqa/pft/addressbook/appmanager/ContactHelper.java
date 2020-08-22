package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

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
    type("home", contactData.getHomephone());
    type("mobile", contactData.getMobile());
    type("work", contactData.getWorkphone());
    type("email", contactData.getEmail());
    attach("photo", contactData.getPhoto());
    if (creation) {
      click(By.name("new_group"));
      //создается контакт с group4: здесь проверяется символ 4 в списке групп,
      // если в создании контакта передавать другую группу, то надо изменить символ
      if ((wd.findElement(By.name("new_group")).getText().contains("4"))) {
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

  public void selectContact(int index) {
    wd.findElements(By.xpath("(//input[@name='selected[]'])")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void create(ContactData contactData, boolean creation) {
    initCreateContact();
    fillContactForm(contactData, creation);
    submitContact();
    returnContactPage();
  }

  public void modify(ContactData contact) {
    editContactByID(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    delete();
    acceptDeletion();
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDeletion() {
    acceptModalWindow();
  }

  public void cancelDeletion() {
    cancelModalWindow();
  }

  public void editContact() {
    wd.findElement(By.xpath("(//img[@alt='Edit'])")).click();
  }

  public void editContactByID(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("(//img[@alt='Edit'])"));
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withLastname(lastName).withFirstname(firstName);
      contacts.add(contact);

    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = wd.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String phones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String emails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withLastname(lastName).
              withFirstname(firstName).withAllphones(phones).withAddress(address).withAllemail(emails);

      contacts.add(contact);

    }
    return contacts;
  }


  public ContactData InfoFromEditForm(ContactData contact) {
    editContactByID(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email= wd.findElement(By.name("email")).getAttribute("value");
    String email2= wd.findElement(By.name("email2")).getAttribute("value");
    String email3= wd.findElement(By.name("email3")).getAttribute("value");
    return new ContactData().withFirstname(firstName).withLastname(lastName).withHomephone(homephone)
            .withMobile(mobile).withWorkphone(workphone).withAddress(address).withEmail(email)
            .withEmail2(email2).withEmail3(email3);
  }
}