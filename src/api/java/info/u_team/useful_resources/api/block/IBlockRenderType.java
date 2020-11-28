package info.u_team.useful_resources.api.block;

public interface IBlockRenderType {
	
	default BlockRenderType getType() {
		return BlockRenderType.SOLID;
	}
	
	public static enum BlockRenderType {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT,
		TRIPWIRE;
	}
	
}
