package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().goToContactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withLastname("Lastname").withMiddlename("Middlename").withFirstname("Ann").withGroup("group4");
    app.contact().create(contact, true);

    Contacts after = app.contact().all();
    assertThat(before.size()+1,equalTo(after.size()));

    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(c->c.getId()).max().getAsInt())),equalTo(after));
  }
}