package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size()==0) {
      Groups groups = app.db().groups();
      if(groups.size()==0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group4"));
        groups.add(new GroupData().withName("group4"));
      }
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").
              withFirstname("Ann").inGroup(groups.iterator().next()).
              withMobile("").withWorkphone("").withHomephone(""), true);
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    Thread.sleep(1001);
    Contacts after = app.db().contacts();
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