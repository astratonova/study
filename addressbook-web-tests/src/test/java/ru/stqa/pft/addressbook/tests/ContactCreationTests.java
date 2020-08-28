package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsJSON() throws IOException {
   try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
     String json = "";
     String line = reader.readLine();
     while (line != null) {
       json += line;
       line = reader.readLine();
     }
     Gson gson = new Gson();
     List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
     }.getType());
     return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
   }
  }

  @Test(dataProvider = "validContactsJSON")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact, true);

    Contacts after = app.contact().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt())), equalTo(after));
  }

  @Test
  public void testContactCreationWithPhoto() {
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