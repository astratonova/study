package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

 /*@DataProvider
 public Iterator<Object[]> validGroups() throws IOException {
   List<Object[]> list = new ArrayList<Object[]>();
   BufferedReader reader =new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
   String line = reader.readLine();
   while(line != null){
     String[] split = line.split(";");
     list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
     line = reader.readLine();
   }
   return list.iterator();
  }*/

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups;
      groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());
      return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validGroupsJSON")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();

    assertThat(before.size() + 1,equalTo(after.size()));
    assertThat(before.withAdded(group.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt())),
            equalTo(after));
  }

  @Test
  public void testGroupCreationEmpty() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("group3");
    app.group().create(group);

    Groups after = app.group().all();
    assertThat(before.size() + 1,equalTo(after.size()));
    group.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt());
    assertThat(before.withAdded(group.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt())),
            equalTo(after));
  }
}