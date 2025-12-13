package com.depixelation.disableendportals.mixin;

import com.depixelation.disableendportals.DisableEndPortals;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class EyeSeekingMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    void modifyEyeBehavior(Level level, Player p_41185_, InteractionHand p_41186_, CallbackInfoReturnable<InteractionResult> cir){
        if (level.isClientSide()) return;
        if (!((ServerLevel) level).getGameRules().get(DisableEndPortals.EYE_LOCATING)) cir.setReturnValue(InteractionResult.PASS);
    }
}
