package fr.mosca421.commandsplus.proxy;

import fr.mosca421.commandsplus.CommandsPlus;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy {

	
/**
 * 
 * @author Mosca421
 */
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerRenders();
	}

	private void registerRenders() {

		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

	}
	public void Render(Class<? extends Entity> par1, Render par2)
	{
		RenderingRegistry.registerEntityRenderingHandler(par1, par2);
	}
	@Override
	public void registerItemTexture(Item item, int metadata, String name)
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(item, metadata, new ModelResourceLocation(CommandsPlus.MODID + ":" + name, "inventory"));
	}

	@Override
	public void registerItemTexture(Item item, String name)
	{
		registerItemTexture(item, 0, name);
	}

	@Override
	public void registerBlockTexture(Block block, int metadata, String name)
	{
		registerItemTexture(Item.getItemFromBlock(block), metadata, name);
	}

	@Override
	public void registerBlockTexture(Block block, String name)
	{
		registerBlockTexture(block, 0, name);
	}

}
