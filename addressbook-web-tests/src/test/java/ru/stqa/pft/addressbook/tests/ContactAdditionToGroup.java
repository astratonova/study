package ru.stqa.pft.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAdditionToGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      if (groups.size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group4"));
        groups.add(new GroupData().withName("group4"));
      }
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").withFirstname("Ann").
              withMobile("").withWorkphone("").withHomephone(""), true);
    }
  }

  @Test
  public void testContactAddToGroup(){
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contact = contacts.iterator().next();
    Groups before = contact.getGroups();
    Sets.SetView<GroupData> difference = Sets.difference(groups, before);
    Set<GroupData> d1 = new HashSet<GroupData>(difference);
    if(d1.size()==0){
      app.goTo().groupPage();
      GroupData group = new GroupData().withName("group999");
      app.group().create(group);
      d1.add(group);
    }

    app.goTo().homePage();
    GroupData groupForAdded = d1.iterator().next();
    app.contact().addToGroup(contact, groupForAdded);
    ContactData modifiedContact = new ContactData();
    Contacts contactsAfter = app.db().contacts();
    for(ContactData contactAfter: contactsAfter) {
      if(contactAfter.getId() == contact.getId()) {
        modifiedContact = contactAfter;
      }
    }
    Groups after = modifiedContact.getGroups();
    assertThat(before.withAdded(groupForAdded), equalTo(after));
  }
}