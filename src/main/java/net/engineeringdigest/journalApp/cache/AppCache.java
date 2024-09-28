package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public Map<String, String> APP_CACHE;

    public enum keys{
        QUESTION_API;
    }

    @Autowired
    private ConfigJournalAppRepostitory configJournalAppRepostitory;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepostitory.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all) {
            System.out.println("key is "+ configJournalAppEntity.getKey()+ " and value is "+ configJournalAppEntity.getValue());
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
