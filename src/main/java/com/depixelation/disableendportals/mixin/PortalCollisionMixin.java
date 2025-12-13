package com.depixelation.disableendportals.mixin;

import com.depixelation.disableendportals.DisableEndPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
public class PortalCollisionMixin {
    @Inject(at= @At("HEAD"), method = "entityInside", cancellable = true)
    void cancelTeleport(BlockState p_53025_, Level level, BlockPos p_53027_, Entity p_53028_, InsideBlockEffectApplier p_405056_, boolean p_451764_, CallbackInfo ci){
        if (level.isClientSide()) return;
        if (!((ServerLevel) level).getGameRules().get(DisableEndPortals.END_PORTAL_COLLISION)) ci.cancel();
    }
}
