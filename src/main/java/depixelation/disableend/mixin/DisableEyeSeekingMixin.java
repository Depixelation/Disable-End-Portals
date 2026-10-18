package depixelation.disableend.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import depixelation.disableend.DisableEndPortals;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(EnderEyeItem.class)
public class DisableEyeSeekingMixin {
    @ModifyExpressionValue(
            method = {"use", "method_7836"},
            at = {
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/server/world/ServerWorld;locateStructure(Lnet/minecraft/registry/tag/TagKey;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;",
                            remap = false
                    ),

                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/class_3218;method_8487(Lnet/minecraft/class_6862;Lnet/minecraft/class_2338;IZ)Lnet/minecraft/class_2338;",
                            remap = false
                    ),
            },
            remap = false,
            require = 1
    )
    public BlockPos modifyEyeTarget(
            @Nullable BlockPos original,
            World world,
            PlayerEntity player,
            Hand hand
    ){
        if (!world.isClient && !Objects.requireNonNull(world.getServer()).getGameRules().getBoolean(DisableEndPortals.ENABLE_EYE_SEEKING)) {
            return player.getBlockPos().add(0, 16, 0);
        }

        return original;
    }
}
