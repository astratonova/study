package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withName("group3").withFooter("group3(Logo)").withHeader("group3(Comment)");
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