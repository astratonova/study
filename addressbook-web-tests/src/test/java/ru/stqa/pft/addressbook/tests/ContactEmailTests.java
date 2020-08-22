package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").
              withFirstname("Ann").withGroup("group4")
              .withMobile("123-123").withWorkphone("+7(8)9")
              .withEmail("qwerty@we.com").withEmail3("12_12@id.ru"), true);
    }
  }

  @Test
  public void testContactPones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.getAllemail(),equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  public String mergeEmails(ContactData contact) {

    return Stream.of(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .filter((s)->!s.equals("")).collect(Collectors.joining("\n"));
    /*
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)->!s.equals("")).collect(Collectors.joining("\n"));
     */
  }
}