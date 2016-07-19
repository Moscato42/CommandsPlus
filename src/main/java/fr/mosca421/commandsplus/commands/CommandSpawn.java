package fr.mosca421.commandsplus.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandSpawn implements ICommand {
	private static final HashMap<EntityPlayer, Object[]> tpTimers = new HashMap<EntityPlayer, Object[]>();

	public static final List<String> aliases = Lists.newArrayList();
	static {
		aliases.add("spawn");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "spawn";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/spawn";
	}

	@Override
	public List<String> getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			tpTimers.put((EntityPlayer) sender, new Object[] { sender.getPosition(), 0 });

		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	public static void onPlayerTick(TickEvent.WorldTickEvent event) {
		System.out.println("ffff");
		if (!event.world.isRemote && event.world.equals(MinecraftServer.getServer().getEntityWorld())) {
			List<EntityPlayer> remove = new ArrayList<EntityPlayer>();

			Iterator<Entry<EntityPlayer, Object[]>> iterator = tpTimers.entrySet().iterator();
			if (iterator.hasNext()) {
				Entry<EntityPlayer, Object[]> entry = iterator.next();

				if (entry.getKey().getPosition().equals(entry.getValue()[0])) {
					Integer tick = (Integer) entry.getValue()[1];
					tick += 1;
					entry.getValue()[1] = tick;
					if (tick % 20 == 0) {
						if (tick / 20 >= 5) {

							BlockPos pos = event.world.getSpawnPoint();
							entry.getKey().setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
							remove.add(entry.getKey());
						} else {
							remove.add(entry.getKey());
							entry.getKey().addChatComponentMessage(new ChatComponentText("Erreur"));
						}

					}else {
						remove.add(entry.getKey());
						entry.getKey().addChatComponentMessage(new ChatComponentText("Erreur"));
					}
				}
			}

			for (EntityPlayer player : remove) {
				tpTimers.remove(player);
			}
		}
	}

}
