package info.u_team.useful_ores.api;

import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.*;

public interface IGeneratable {
	
	boolean isEnabled();
	
	boolean isBiomeCategoryBlackList();
	
	List<Category> getBiomeCategories();
	
	boolean isBiomeBlackList();
	
	List<Biome> getBiomes();
	
	int getVeinSize();
	
	Type getType();
	
	CountRangeConfig getCountRangeConfig();
	
	DepthAverageConfig getDepthAverageConfig();
	
	public static enum Type {
		COUNT_RANGE("count_range"),
		COUNT_DEPTH_AVERAGE("count_depth_average");
		
		private final String name;
		
		private Type(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static Type byName(String name) {
			if ("count_range".equals(name)) {
				return COUNT_RANGE;
			}
			if ("count_depth_average".equals(name)) {
				return COUNT_DEPTH_AVERAGE;
			}
			return null;
		}
	}
	
}
