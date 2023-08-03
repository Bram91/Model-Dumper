package net.bram91.modeldumper.types;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NPCData implements Comparable<NPCData> {
    @Getter
    private final String name;
    private final int id;
    private final int standingAnimation;
    private final int walkingAnimation;

    public String toString()
    {
        return name;
    }

    @Override
    public int compareTo(NPCData o) {
        return this.name.compareTo(o.toString());
    }
}
