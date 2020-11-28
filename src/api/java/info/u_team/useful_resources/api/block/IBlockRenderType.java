package info.u_team.useful_resources.api.block;

public interface IBlockRenderType {
	
	BlockRenderType getType();
	
	public static enum BlockRenderType {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT,
		TRIPWIRE;
	}
	
}
