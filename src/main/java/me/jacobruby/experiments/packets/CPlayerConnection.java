package me.jacobruby.experiments.packets;

import me.jacobruby.experiments.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.*;

public class CPlayerConnection extends PlayerConnection {

    public static int value = 1;

    public CPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet packet) {
        if (packet instanceof PacketPlayOutSpawnEntity) {
            PacketPlayOutSpawnEntity p = (PacketPlayOutSpawnEntity) packet;

            ReflectionUtil.setFieldValue(p, "j", value);
        }

        super.sendPacket(packet);
    }
}
