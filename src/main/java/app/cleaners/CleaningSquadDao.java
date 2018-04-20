package app.cleaners;

import com.google.common.collect.ImmutableList;

import java.util.HashSet;
import java.util.Set;

public class CleaningSquadDao {
    
    private static Set<CleaningSquad> cleaningSquads = new HashSet<>(ImmutableList.of(
            new CleaningSquad("Gnocchi Gnomes", 8.0, 15)
    ));
    
    public Set<CleaningSquad> getAllCleaningSquads() {
        return cleaningSquads;
    }
    
}
