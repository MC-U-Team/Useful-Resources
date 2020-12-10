package info.u_team.useful_resources.data.util;

import java.util.*;
import java.util.function.Function;

import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.block.Block;
import net.minecraft.loot.LootTable;

public class LootTableGenerationDecider {
	
	public static LootTableGenerationDecider create() {
		return new LootTableGenerationDecider();
	}
	
	private final Map<IResourceType<Block>, LootTableGenerationConsumer> map;
	
	protected LootTableGenerationDecider() {
		map = new LinkedHashMap<>();
	}
	
	public void addSpecial(IResourceType<Block> type, LootTableGenerationConsumer consumer) {
		map.put(type, consumer);
	}
	
	public LootTable decide(IResource resource, IResourceType<Block> type, Block block, IDataGeneratorConfigurator configurator, Function<Block, LootTable> baseLootTable) {
		final LootTableGenerationConsumer consumer = map.get(type);
		if (consumer != null) {
			return consumer.apply(resource, type, block, configurator);
		} else {
			return baseLootTable.apply(block);
		}
	}
	
	@FunctionalInterface
	public interface LootTableGenerationConsumer {
		
		LootTable apply(IResource resource, IResourceType<Block> type, Block block, IDataGeneratorConfigurator configurator);
		
	}
	
}
