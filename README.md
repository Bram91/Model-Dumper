# Model Exporter

Allows exporting player/npc/object models and ground items by shift + right-clicking them and selecting the `Export Model` option.
To export the local player right-click the equipment tab(If you use resizable mode with the side panel option enabled it will be on right click inventory).

##### Colors can be exported with the exception of firecape/infernal cape textures

Models will be stored in the `.runelite/models/` folder.

##### Sequences can be exported by right-clicking the equipment tab and selecting the sequence export button
_This feature will work with the transmog settings in the plugin and thus only works with the local player model._

`Store OBJ material data for all frames` will store MTL data for each frame, but for sequence importing into blender only the first frame materials are used, so by keeping this disabled importing in blender is a lot faster.

Sequences will be exported in the `.runelite/models/sequences` directory in the following format:
`year-month-day_time/<player or npc id>-animation-frame.extension`

To import OBJ sequences you can use the following blender addon: [OSRSObjSequence.py](https://gist.githubusercontent.com/Bram91/5be4190c374a90f9fb0b13b17685d8c3/raw/155a71d535817b5dc57c6f262b6845970852a78a/OSRSObjSequence.py)(This contains edits to make it work with frames without any material data attached)

For example exporting npc id 12214 with animation 10276 will result in: `12214-10276-0.obj` through `12214-10276-119.obj`
where exporting the local player model with animation 7121 will result in `player-7121-0.obj` through `player-7121-43.obj`

### When using the transmog option it is recommended to disable animation smoothing


## After enabling the sidepanel in the config
The sidepanel allows you to search through the npcs and corresponding animations.

You can give an animation a custom name by right-clicking an animation id and entering the name you prefer. After editing names make sure to save the animation names with the corresponding button. Names are stored in `.runelite/models/animationNames.txt` when editing this file directly it's important you don't add any trailing empty lines at the bottom of the file.

The `Set Config` button saves the current selected npc+animation to the config, so you can step through frames there. Config panels don't automatically refresh so make sure to close and reopen it yourself.

The `Export Sequence` button exports the full currently selected animation.

The `Reset` button resets the local player model back to the defaults with a static pose, equipping/equipping gear will reset this pose back to normal.

The `Open Folder` button will open the `.runelite/models` directory in your file explorer.
