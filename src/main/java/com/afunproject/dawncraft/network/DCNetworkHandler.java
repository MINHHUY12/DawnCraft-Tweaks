package com.afunproject.dawncraft.network;

import com.afunproject.dawncraft.ModDefinitions;
import com.afunproject.dawncraft.client.ClientHandler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.smileycorp.atlas.api.network.SimpleMessageDecoder;
import net.smileycorp.atlas.api.network.SimpleMessageEncoder;
import net.smileycorp.atlas.api.network.SimpleStringMessage;

public class DCNetworkHandler {

	public static SimpleChannel NETWORK_INSTANCE;

	public static void initPackets() {
		NETWORK_INSTANCE = NetworkRegistry.newSimpleChannel(ModDefinitions.getResource("main"), ()-> "1", "1"::equals, "1"::equals);
		NETWORK_INSTANCE.registerMessage(0, SimpleStringMessage.class, new SimpleMessageEncoder<SimpleStringMessage>(),
				new SimpleMessageDecoder<SimpleStringMessage>(SimpleStringMessage.class), (T, K)-> processNotificationMessage(T, K.get()));

	}

	public static void processNotificationMessage(SimpleStringMessage message, Context ctx) {
		ctx.enqueueWork(() -> DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> ClientHandler.displayMessage(message.getText())));
		ctx.setPacketHandled(true);
	}

}
