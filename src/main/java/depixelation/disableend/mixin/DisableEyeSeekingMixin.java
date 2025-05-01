package depixelation.disableend.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import depixelation.disableend.DisableEndPortals;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
    private PlayerEntity player;
    private World world;
    @ModifyExpressionValue(
            method = "use",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;locateStructure(Lnet/minecraft/registry/tag/TagKey;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;")
    )
    public BlockPos modifyEyeTarget(@Nullable BlockPos original){
        if (!world.isClient && !Objects.requireNonNull(world.getServer()).getGameRules().getBoolean(DisableEndPortals.ENABLE_EYE_SEEKING)) {
            return player.getBlockPos().add(0, 16, 0);
        }

        return original;
    }

    @Inject(method = "use", at = @At("HEAD"))
    public void captureVariables(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        this.player = user;
        this.world = world;
    }
}
