package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("TAnn2", "TAnn3", "TAnn", "TAS", "Title", "Company", "address", "555", "444", "333", "123@123.com","group4");

    app.getContactHelper().createNewContact(contact, true);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size()+1,after.size());

    before.add(contact);
    Comparator<? super ContactData> byId= (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}