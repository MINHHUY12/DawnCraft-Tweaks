package com.afunproject.dawncraft.integration.quests.custom.quests.dc;

import com.afunproject.dawncraft.integration.quests.custom.QuestEntity;
import com.afunproject.dawncraft.integration.quests.custom.QuestResponseType;
import com.afunproject.dawncraft.integration.quests.custom.entity.QuestEntityBase;
import com.afunproject.dawncraft.integration.quests.custom.quests.ItemQuest;
import com.afunproject.dawncraft.integration.quests.network.OpenQuestMessage;
import com.afunproject.dawncraft.integration.quests.network.QuestNetworkHandler;

import net.mcreator.simplemobs.init.SimpleMobsModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkDirection;

public class CultQuest extends ItemQuest {

	public CultQuest() {
		super(new ItemStack(Items.PAPER), new ItemStack(SimpleMobsModItems.PLAGUEMASK_HELMET.get()));
	}

	@Override
	public void completeQuest(Player quest_completer, Mob entity, int phase, boolean accepted) {
		if (phase == -1) {
			QuestEntity.safeCast(entity).setQuestPhase(2);
		}
		if (phase == 1 && accepted) {
			QuestEntity quest_entity = QuestEntity.safeCast(entity);
			quest_entity.setQuestPhase(-1);
			quest_entity.setQuestText(getText() + "1b");
			QuestNetworkHandler.NETWORK_INSTANCE.sendTo(new OpenQuestMessage(entity, this), ((ServerPlayer) quest_completer).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		} else {
			super.completeQuest(quest_completer, entity, phase, accepted);
		}
	}

	@Override
	protected void completeItemQuest(Player quest_completer, Mob entity, int phase, boolean accepted) {
		if (phase == 3) {
			ItemStack map = createMap((ServerLevel)quest_completer.level, quest_completer.blockPosition(), new ResourceLocation("minecraft", "dg2q"), "map.dawncraft.cultist");
			giveItem(quest_completer, map);
		}
		if (phase == end_phase) {
			if (entity instanceof QuestEntityBase) {
				((QuestEntityBase) entity).setDespawnable(true);
			}
			ItemStack map = createMap((ServerLevel)quest_completer.level, quest_completer.blockPosition(), new ResourceLocation("minecraft", "dg2q2"), "map.dawncraft.cultist_2");
			giveItem(quest_completer, map);
		}
	}

	@Override
	public QuestResponseType getQuestType(int phase) {
		if (phase == -1) return QuestResponseType.AUTO_CLOSE;
		return super.getQuestType(phase);
	}

	@Override
	protected String getText() {
		return "text.dawncraft.quest.cult";
	}

}
