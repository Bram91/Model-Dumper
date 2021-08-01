package net.bram91.modeldumper;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import net.runelite.api.Model;
import net.runelite.api.TileItem;
import net.runelite.api.coords.WorldPoint;

@Data
@Builder
class GroundItem {
    @Getter
    private int id;
    
    private TileItem item;
    private WorldPoint location;
    private int quantity;

    public Model getModel() {
        return this.item.getModel();
    }
}
