package info.u_team.useful_resources.config;

public class DefaultConfig {
	
	private final float oreHardness;
	private final float oreResistance;
	private final float netherOreHardness;
	private final float netherOreResistance;
	private final float blockHardness;
	private final float blockResistance;
	private final GeneratableConfig oreConfig;
	private final GeneratableConfig netherOreConfig;
	
	public DefaultConfig(float oreHardness, float oreResistance, float netherOreHardness, float netherOreResistance, float blockHardness, float blockResistance, GeneratableConfig oreConfig, GeneratableConfig netherOreConfig) {
		super();
		this.oreHardness = oreHardness;
		this.oreResistance = oreResistance;
		this.netherOreHardness = netherOreHardness;
		this.netherOreResistance = netherOreResistance;
		this.blockHardness = blockHardness;
		this.blockResistance = blockResistance;
		this.oreConfig = oreConfig;
		this.netherOreConfig = netherOreConfig;
	}
	
	public float getOreHardness() {
		return oreHardness;
	}
	
	public float getOreResistance() {
		return oreResistance;
	}
	
	public float getNetherOreHardness() {
		return netherOreHardness;
	}
	
	public float getNetherOreResistance() {
		return netherOreResistance;
	}
	
	public float getBlockHardness() {
		return blockHardness;
	}
	
	public float getBlockResistance() {
		return blockResistance;
	}
	
	public GeneratableConfig getOreConfig() {
		return oreConfig;
	}
	
	public GeneratableConfig getNetherOreConfig() {
		return netherOreConfig;
	}
}
