package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","group3"), true);
    }
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("MAnn2", "MAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnContactPage();
  }
}