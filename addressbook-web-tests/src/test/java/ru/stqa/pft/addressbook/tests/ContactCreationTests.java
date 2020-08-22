package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/159.jpg");
    ContactData contact = new ContactData().withLastname("Lastname").withMiddlename("Middlename")
            .withFirstname("Ann").withGroup("group4").withPhoto(photo);
    app.contact().create(contact, true);

    Contacts after = app.contact().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt())), equalTo(after));
  }

/* не хочу убирать
  @Test
  public void textCurrentDir() {
    File photo = new File("src/test/resources/159.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
 */
}