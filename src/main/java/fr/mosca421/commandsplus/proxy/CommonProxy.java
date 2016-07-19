package fr.mosca421.commandsplus.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {


	public void preInit(FMLPreInitializationEvent event)
	{

	}

	public void init(FMLInitializationEvent event)
	{
		
	}

	public void registerItemTexture(Item item, int metadata, String name){}

	public void registerItemTexture(Item item, String name){}

	public void registerBlockTexture(Block block, int metadata, String name){}

	public void registerBlockTexture(Block block, String name){}

}
