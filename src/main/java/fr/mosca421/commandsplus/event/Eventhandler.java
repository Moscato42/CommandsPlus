package fr.mosca421.commandsplus.event;

import fr.mosca421.commandsplus.commands.CommandSpawn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Eventhandler {

	public static void preInit(FMLPreInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(new CommandSpawn());
		

	}

}
