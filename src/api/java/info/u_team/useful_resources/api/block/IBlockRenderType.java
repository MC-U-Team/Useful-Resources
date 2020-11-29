package info.u_team.useful_resources.api.block;

import java.util.function.Supplier;

import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.*;

public interface IBlockRenderType {
	
	@OnlyIn(Dist.CLIENT)
	default BlockRenderType getType() {
		return BlockRenderType.SOLID;
	}
	
	public static enum BlockRenderType {
		
		SOLID(() -> RenderType::getSolid),
		CUTOUT(() -> RenderType::getCutout),
		CUTOUT_MIPPED(() -> RenderType::getCutoutMipped),
		TRANSLUCENT(() -> RenderType::getTranslucent),
		TRIPWIRE(() -> RenderType::getTripwire);
		
		@OnlyIn(Dist.CLIENT)
		private final Supplier<Supplier<RenderType>> type;
		
		private BlockRenderType(Supplier<Supplier<RenderType>> type) {
			this.type = type;
		}
		
		@OnlyIn(Dist.CLIENT)
		public Supplier<Supplier<RenderType>> getType() {
			return type;
		}
	}
	
}
