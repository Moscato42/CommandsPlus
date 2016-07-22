package fr.mosca421.commandsplus;


import org.apache.logging.log4j.Logger;

import fr.mosca421.commandsplus.blocks.BlockRegistry;
import fr.mosca421.commandsplus.commands.CommandRegistry;
import fr.mosca421.commandsplus.event.Eventhandler;
import fr.mosca421.commandsplus.gui.GuiHandler;
import fr.mosca421.commandsplus.items.ItemRegistry;
import fr.mosca421.commandsplus.packet.PacketRegistry;
import fr.mosca421.commandsplus.proxy.CommonProxy;
import fr.mosca421.commandsplus.recipes.RecipeRegistry;
import fr.mosca421.commandsplus.tiles.TileEntityRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * 
 * @author Mosca421
 *
 */

@Mod(modid = CommandsPlus.MODID, version = CommandsPlus.VERSION)
public class CommandsPlus
{
    public static final String MODID = "CommandsPlus";
    public static final String VERSION = "1.0.0";
	public static CommandsPlus INSTANCE;
	public static SimpleNetworkWrapper network;	

	@SidedProxy(clientSide = "fr.mosca421.commandsplus.proxy.ClientProxy", serverSide = "fr.mosca421.commandsplus.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;
	public static CreativeTabs factionTabs = new CreativeTabs("FactionTab") {
		
		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
	};
	
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	Eventhandler.preInit(event);
		logger = event.getModLog();
		BlockRegistry.preInit(event);
		ItemRegistry.preInit(event);
		proxy.preInit(event);

    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(event);
		RecipeRegistry.init(event);
		TileEntityRegistry.init(event);
		network = NetworkRegistry.INSTANCE.newSimpleChannel("commandplus");
		//NetworkRegistry.INSTANCE.registerGuiHandler(CommandsPlus.INSTANCE, new GuiHandler());
		PacketRegistry.init(event);
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
		CommandRegistry.onServerStarting(event);
    }
}
