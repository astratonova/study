package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    Contacts after = app.contact().all();
    assertThat(before.size(), equalTo(after.size() + 1));
    assertThat(before.without(deletedContact), equalTo(after));
  }

  @Test
  public void testContactCancelDeletion() {
    app.contact().selectContact(0);
    app.contact().delete();
    app.contact().cancelDeletion();
  }
}