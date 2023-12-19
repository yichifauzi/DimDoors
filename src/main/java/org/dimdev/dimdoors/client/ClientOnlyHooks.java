package org.dimdev.dimdoors.client;

import org.dimdev.dimdoors.config.DDProperties;
import org.dimdev.dimdoors.DimDoors;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.event.world.WorldEvent;

public class ClientOnlyHooks {
    private final DDProperties properties;

    private final ISound limboMusic;

    public ClientOnlyHooks(DDProperties properties) {
        this.properties = properties;
        this.limboMusic = PositionedSoundRecord.func_147673_a(new ResourceLocation(DimDoors.modid + ":creepy"));
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onSoundEffectResult(PlaySoundEvent17 event) {
        ResourceLocation playingSound = event.sound.getPositionedSoundLocation();
        if (playingSound != null && playingSound.getResourceDomain().equals("minecraft") && (playingSound.getResourcePath().equals("music.game") || playingSound.getResourcePath().equals("music.game.creative"))) {
            if (FMLClientHandler.instance().getClient().thePlayer.worldObj.provider.dimensionId == DimDoors.properties.LimboDimensionID) {
                ResourceLocation sound = new ResourceLocation(DimDoors.modid + ":creepy");

                if (!Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(limboMusic)) {
                    event.result = limboMusic;
                } else {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.world.provider.dimensionId == DimDoors.properties.LimboDimensionID &&
                event.world.isRemote && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(limboMusic)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(limboMusic);
        }
    }
}
