package net.engineeringdigest.journalApp.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data   //this includes 4 annotations -> getter, setter...
@NoArgsConstructor
@Getter
@Setter
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}
