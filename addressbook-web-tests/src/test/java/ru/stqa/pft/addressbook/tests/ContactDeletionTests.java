package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.goTo().goToContactPage();
    if(! app.contact().isThereAContact()){
      app.contact().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","Group1"), true);
    }
    List<ContactData> before = app.contact().list();
    app.contact().selectContact(before.size()-1);
    app.contact().deleteContact();
    app.contact().acceptDeletion();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(before.size(),after.size() + 1);
    before.remove(before.size()-1);
    Assert.assertEquals(before, after);

  }

  @Test
  public void testContactCancelDeletion() {
    app.goTo().goToContactPage();
    if(! app.contact().isThereAContact()){
      app.contact().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","Group1"), true);
    }
    app.contact().selectContact(0);
    app.contact().deleteContact();
    app.contact().cancelDeletion();
  }
}