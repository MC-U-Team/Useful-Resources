package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.world.storage.loot.LootTable;

public interface IDataGeneratorConfigurator {
	
	ResourceType getResourceType();
	
	Map<String, Supplier<WorldGenFeature>> getWorldGeneration();
	
	Map<BlockResourceType, Supplier<LootTable>> getExtraLootTables();
	
	Map<String, Object> getExtraProperties();
	
	public enum ResourceType {
		
		INGOT("ingot"),
		GEM("gem"),
		DUST("dust");
		
		private final String name;
		
		private ResourceType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
