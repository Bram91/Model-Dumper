# Model Exporter

Allows exporting player/npc/object models and ground items by shift + right clicking them and selecting the `Export Model` option.
To export the local player right click the equipment tab(If you use resizable mode with the side panel option enabled it will be on right click inventory).

##### Colors can be exported with the exception of firecape/infernal cape textures

Models will be stored in the `.runelite/models/` folder.

##### Sequences can be exported by right clicking the equipment tab and selecting the sequence export button
_This feature will works with the transmog settings in the plugin and thus only works with the local player model._

`Store OBJ material data for all frames` will store MTL data for each frame, but for sequence importing into blender only the first frame materials are used, so by keeping this disabled importing in blender is a lot faster.

Sequences will be exported in the `.runelite/models/sequences` directory in the following format:
`year-month-day_time/<player or npc id>-animation-frame.extension`

To import OBJ sequences you can use the following blender addon: [OSRSObjSequence.py](https://gist.githubusercontent.com/Bram91/5be4190c374a90f9fb0b13b17685d8c3/raw/155a71d535817b5dc57c6f262b6845970852a78a/OSRSObjSequence.py)(This contains edits to make it work with frames without any material data attached)

For example exporting npc id 12214 with animation 10276 will result in: `12214-10276-0.obj` through `12214-10276-119.obj`
where exporting the local player model with animation 7121 will result in `player-7121-0.obj` through `player-7121-43.obj`

### When using the transmog option it is recommended to disable animation smoothing
