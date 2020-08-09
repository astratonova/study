package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToContactPage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","Group1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().acceptDeletion();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(),after.size() + 1);
    before.remove(before.size()-1);
    Assert.assertEquals(before, after);

  }

  @Test
  public void testContactCancelDeletion() {
    app.getNavigationHelper().goToContactPage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createNewContact(new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","Group1"), true);
    }
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteContact();
    app.getContactHelper().cancelDeletion();
  }
}