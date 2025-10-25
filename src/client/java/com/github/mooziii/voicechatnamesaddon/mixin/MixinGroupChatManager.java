package com.github.mooziii.voicechatnamesaddon.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import de.maxhenkel.voicechat.voice.client.GroupChatManager;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GroupChatManager.class)
public class MixinGroupChatManager {

    @Inject(
            method = "renderIcons",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private static void groupNames$render(
            GuiGraphics guiGraphics, CallbackInfo ci, @Local ClientVoicechat client, @Local Minecraft mc,
            @Local(ordinal = 0) int posX, @Local(ordinal = 1) int posY,
            @Local PlayerState state
    ) {
        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().scale(0.5F, 0.5F);
        guiGraphics.drawString(mc.font, state.getName(), (posX < 0 ? -9 : 1) + 22, (posY < 0 ? -9 : 1) + 8 - mc.font.lineHeight / 2, -1);
        guiGraphics.pose().popMatrix();
    }
}