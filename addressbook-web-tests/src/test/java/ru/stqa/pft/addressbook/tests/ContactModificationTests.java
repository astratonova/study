package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","group4"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(before.get(0).getId(),"MAnn2", "MAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnContactPage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(),after.size());
    before.remove(0);
    before.add(contact);
    Comparator<? super ContactData> byId= (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}