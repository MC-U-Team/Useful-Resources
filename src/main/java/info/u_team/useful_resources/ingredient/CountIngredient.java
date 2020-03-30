package info.u_team.useful_resources.ingredient;

import java.util.stream.Stream;

import com.google.gson.*;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.crafting.*;

public class CountIngredient extends Ingredient {
	
	private final ItemStack stack;
	
	protected CountIngredient(ItemStack stack) {
		super(Stream.of(new Ingredient.SingleItemList(stack)));
		this.stack = stack;
	}
	
	@Override
	public boolean test(ItemStack input) {
		if (input == null)
			return false;
		return stack.getItem() == input.getItem() && stack.getDamage() == input.getDamage() && stack.areShareTagsEqual(input) && stack.getCount() <= input.getCount();
	}
	
	@Override
	public boolean isSimple() {
		return false;
	}
	
	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return Serializer.INSTANCE;
	}
	
	@Override
	public JsonElement serialize() {
		JsonObject json = new JsonObject();
		json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
		json.addProperty("item", stack.getItem().getRegistryName().toString());
		json.addProperty("count", stack.getCount());
		if (stack.hasTag())
			json.addProperty("nbt", stack.getTag().toString());
		return json;
	}
	
	public static class Serializer implements IIngredientSerializer<CountIngredient> {
		
		public static final Serializer INSTANCE = new Serializer();
		
		@Override
		public CountIngredient parse(PacketBuffer buffer) {
			return new CountIngredient(buffer.readItemStack());
		}
		
		@Override
		public CountIngredient parse(JsonObject json) {
			return new CountIngredient(CraftingHelper.getItemStack(json, true));
		}
		
		@Override
		public void write(PacketBuffer buffer, CountIngredient ingredient) {
			buffer.writeItemStack(ingredient.stack);
		}
	}
}