package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().initCreateContact();
    app.getContactHelper().fillContactForm(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com"));
    app.getContactHelper().fillNewGroup("new_group", "Group1");
    app.getContactHelper().submitContact();
    app.getContactHelper().returnContactPage();
  }
}