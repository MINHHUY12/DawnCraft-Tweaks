package com.afunproject.dawncraft;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.eventbus.api.Event;

public class DCSubCommandsEvent extends Event {

	LiteralArgumentBuilder<CommandSourceStack> command;

	public DCSubCommandsEvent(LiteralArgumentBuilder<CommandSourceStack> command) {
		this.command = command;
	}

	public void addSubCommand(LiteralArgumentBuilder<CommandSourceStack> subcommand) {
		command = command.then(subcommand);
	}

}
