# Model Exporter

Allows exporting player/npc/object models and ground items by shift + right clicking them and selecting the `Export Model` option.
To export the local player right click the equipment tab(If you use resizable mode with the side panel option enabled it will be on right click inventory).

##### Colors can be exported with the exception of firecape/infernal cape textures

Models will be stored in the `.runelite/models/` folder.

##### Sequences can be exported by right clicking the equipment tab and selecting the sequence export button
_This feature will works with the transmog settings in the plugin and thus only works with the local player model._

Sequences will be exported in the `.runelite/models/sequences` directory in the following format:
`year-month-day_time/<player or npc id>-animation-frame.obj`

For example exporting npc id 12214 with animation 10276 will result in: `12214-10276-0.obj` through `12214-10276-119.obj`
where exporting the local player model with animation 7121 will result in `player-7121-0.obj` through `player-7121-43.obj`

### When using the transmog option it is recommended to disable animation smoothing
