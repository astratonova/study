package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().
              withLastname("Lastname").withMiddlename("Middlename").
              withFirstname("Ann").withGroup("group4")
              .withMobile("123-123").withWorkphone("+7(8)9"), true);
    }
  }

  @Test
  public void testContactPones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.getAllphones(),equalTo(mergePhones(contactInfoFromEditForm)));
  }

  public String mergePhones(ContactData contact) {
    return Stream.of(contact.getHomephone(),contact.getMobile(),contact.getWorkphone()).filter((s)->!s.equals(""))
            .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));

    /*return Arrays.asList(contact.getHomephone(),contact.getMobile(),contact.getWorkphone())
            .stream().filter((s)->!s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
     */
  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}