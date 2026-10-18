package depixelation.disableend.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import depixelation.disableend.DisableEndPortals;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;

@Mixin(EnderEyeItem.class)
public class DisableEyeSeekingMixin {
    @ModifyExpressionValue(
            method = {"use"},
            at = {
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/server/level/ServerLevel;findNearestMapStructure(Lnet/minecraft/tags/TagKey;Lnet/minecraft/core/BlockPos;IZ)Lnet/minecraft/core/BlockPos;"
                    ),
            },
            require = 1
    )
    public BlockPos modifyEyeTarget(
            @Nullable BlockPos original,
            Level world,
            Player player,
            InteractionHand hand
    ){
        if (!world.isClientSide() && !((ServerLevel) world).getGameRules().get(DisableEndPortals.ENABLE_EYE_SEEKING)) {
            return player.blockPosition().offset(0, 16, 0);
        }

        return original;
    }
}
