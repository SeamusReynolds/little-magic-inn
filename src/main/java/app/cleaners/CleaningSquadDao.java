package app.cleaners;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class CleaningSquadDao {
    
    private static List<CleaningSquad> cleaningSquads = ImmutableList.of(
            new CleaningSquad(8, 1.0, 0.5)
    );
    
    public List<CleaningSquad> getAllCleaningSquads() {
        return cleaningSquads;
    }
    
}
