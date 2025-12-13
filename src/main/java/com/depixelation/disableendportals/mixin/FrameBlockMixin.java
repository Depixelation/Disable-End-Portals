package com.depixelation.disableendportals.mixin;

import com.depixelation.disableendportals.DisableEndPortals;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderEyeItem.class)
public class FrameBlockMixin {

    @ModifyExpressionValue(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;")
    )

    private Comparable<?> modifyCondition(Comparable<?> original, @Local(argsOnly = true) UseOnContext context) {

        // If on client side, just return original behavior
        if (context.getLevel().isClientSide()) {
            return original;
        }

        // On server: return true (block usage) if frame has eye OR if game rule disables filling
        return ((Boolean) original) || !((ServerLevel) context.getLevel()).getGameRules().get(DisableEndPortals.FRAME_FILLING);
    }
}