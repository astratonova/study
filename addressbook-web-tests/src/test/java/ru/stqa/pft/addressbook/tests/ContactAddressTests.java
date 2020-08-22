package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressTests extends TestBase {

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
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.getAddress(),equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\n","");
  }
}