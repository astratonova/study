package ru.stqa.pft.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    Contacts contactsWithGroup = new Contacts();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData newContact= new ContactData().
            withLastname("Lastname").withMiddlename("Middlename").withFirstname("Ann").
            withMobile("").withWorkphone("").withHomephone("").inGroup(groups.iterator().next());
    if (contacts.size() == 0) {
      if (groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group4"));
        groups.add(new GroupData().withName("group4"));
      }
      app.goTo().homePage();
      app.contact().create(newContact, true);
      contactsWithGroup.add(newContact);
    } else {
      for (ContactData contact : contacts) {
        if(contact.getGroups()!=null){
          contactsWithGroup.add(contact);
        }
        if(contactsWithGroup.size()==0){
          if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group4"));
            groups.add(new GroupData().withName("group4"));
          }
          app.goTo().homePage();
          app.contact().create(new ContactData().
                  withLastname("Lastname").withMiddlename("Middlename").withFirstname("Ann").
                  withMobile("").withWorkphone("").withHomephone("").inGroup(groups.iterator().next()), true);
          contactsWithGroup.withAdded(newContact);

        }
      }
    }
  }

  @Test
  public void testContactDeleteGroup(){
    Contacts contacts = app.db().contacts();
    Contacts contactsWithGroup= new Contacts();
    for (ContactData contact : contacts) {
      if(contact.getGroups()!=null){
        contactsWithGroup.add(contact);
      }
    ContactData contactWithGroup = contactsWithGroup.iterator().next();
    Groups before = contactWithGroup.getGroups();
    app.goTo().homePage();
    GroupData groupForDeleted = before.iterator().next();
    app.contact().deleteFromGroup(contactWithGroup, groupForDeleted);
    ContactData modifiedContact = new ContactData();
    Contacts contactsAfter = app.db().contacts();
    for(ContactData contactAfter: contactsAfter) {
      if(contactAfter.getId() == contactWithGroup.getId()) {
        modifiedContact = contactAfter;
      }
    }
    Groups after = modifiedContact.getGroups();
    assertThat(before, equalTo(after.withAdded(groupForDeleted)));
  }
}
}