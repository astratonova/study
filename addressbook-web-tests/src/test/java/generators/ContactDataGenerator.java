package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c",description = "Contacts count")
  public int count;

  @Parameter(names = "-f",description = "Target file")
  public String file;

  @Parameter(names = "-d",description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    }catch (ParameterException ex) {
      jcommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> groups = generateContacts(count);
    if(format.equals("xml")){
      saveAsXML(groups,new File(file));
    }else if(format.equals("json")){
      saveAsJSON(groups,new File(file));
    }else{
      System.out.println("Фомат"+format+"не поддерживается");
    }
  }

  private void saveAsJSON(List<ContactData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation().create();
    String json =gson.toJson(groups);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(groups);
    try(Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> groups = new ArrayList<ContactData>();
    for (int i=0; i<count; i++){
      groups.add(new ContactData().withFirstname(String.format("Firstname%s",i))
              .withMiddlename(String.format("Middlename%s",i))
              .withLastname(String.format("Lastname%s",i)).withGroup(String.format("group4")));

    }
    return groups;
  }
}
