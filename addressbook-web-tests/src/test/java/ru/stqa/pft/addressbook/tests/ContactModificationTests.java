package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size()==0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").
              withFirstname("Ann").withGroup("group4"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/159.jpg");
    ContactData contact = new ContactData().
            withLastname("LastnameM").withMiddlename("MiddlenameM").
            withFirstname("AnnM").withId(modifiedContact.getId()).withMobile("").
            withWorkphone("").withHomephone("").withPhoto(photo);
    app.goTo().homePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertThat(before.size(),equalTo(after.size()));
    assertThat(before.without(modifiedContact).withAdded(contact),equalTo(after));
  }
}