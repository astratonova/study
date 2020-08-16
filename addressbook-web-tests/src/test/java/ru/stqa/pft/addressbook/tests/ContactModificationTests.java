package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().goToContactPage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").
              withFirstname("Ann").withGroup("group4"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withLastname("LastnameM").withMiddlename("MiddlenameM").
            withFirstname("AnnM").withId(modifiedContact.getId());
    app.contact().modify(contact);

    Contacts after = app.contact().all();
    assertThat(before.size(),equalTo(after.size()));
    assertThat(before.without(modifiedContact).withAdded(contact),equalTo(after));
  }
}