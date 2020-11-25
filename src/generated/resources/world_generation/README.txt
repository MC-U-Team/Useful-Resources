In this folder you can add or remove custom world generation definitions.
The generation files are in json. A definition can contain multiplate configured features
for each generation stage. Configured features are explained in the minecraft wiki.
Each file targets a specific set of biomes. If a feature can generate is determined with
a category and a biome white/black list. If both conditions are true the feature is added.

To get the updated definitions (aften an update) please delete the world_generation_marker file.
This will extract all the default custom world generation definitions and will override any
changes to these files. New files will not be deleted. If you want to have a completely clean
installation please delete the parent folder (worldgeneration) of this file and rerun the game
