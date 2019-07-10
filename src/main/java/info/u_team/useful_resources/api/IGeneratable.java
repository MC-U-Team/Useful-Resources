package info.u_team.useful_resources.api;

import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.*;

public interface IGeneratable {
	
	boolean isEnabled();
	
	ListType getBiomeCategoryListType();
	
	List<Category> getBiomeCategories();
	
	ListType getBiomeListType();
	
	List<Biome> getBiomes();
	
	int getVeinSize();
	
	GenerationConfig getGenerationConfig();
	
	CountRangeConfig getCountRangeConfig();
	
	DepthAverageConfig getDepthAverageConfig();
	
	public static enum GenerationConfig {
		COUNT_RANGE("count_range"),
		COUNT_DEPTH_AVERAGE("count_depth_average");
		
		private final String name;
		
		private GenerationConfig(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static GenerationConfig byName(String name) {
			if ("count_range".equals(name)) {
				return COUNT_RANGE;
			}
			if ("count_depth_average".equals(name)) {
				return COUNT_DEPTH_AVERAGE;
			}
			return COUNT_RANGE;
		}
	}
	
	public static enum ListType {
		BLACKLIST("blacklist"),
		WHITELIST("whitelist");
		
		private final String name;
		
		private ListType(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static ListType byName(String name) {
			if ("blacklist".equals(name)) {
				return BLACKLIST;
			}
			if ("whitelist".equals(name)) {
				return WHITELIST;
			}
			return BLACKLIST;
		}
	}
}
