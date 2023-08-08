package net.bram91.modeldumper.types;

import lombok.Getter;
import lombok.Setter;

public class Animation implements Comparable<Animation> {
    @Getter
    private int id;

    @Getter
    @Setter
    private String name;

    public Animation(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Animation) {
            return id == ((Animation) o).id;
        }
        return false;
    }

    public String toString() {
        if (name == null) {
            return id + "";
        }
        return id + " - " + name;
    }

    @Override
    public int compareTo(Animation o) {
        return ((Integer) this.id).compareTo(o.getId());
    }
}
