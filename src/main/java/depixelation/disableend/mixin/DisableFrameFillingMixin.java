package depixelation.disableend.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import depixelation.disableend.DisableEndPortals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.context.UseOnContext;

@Mixin(EnderEyeItem.class)
public class DisableFrameFillingMixin {
    @ModifyExpressionValue(
            method = "useOn",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;")
    )

    //For the conditional check for if the eye should NOT be used
    public Comparable modifyCondition(Comparable original, UseOnContext context) {
        return ((Boolean) original) || !context.getLevel().isClientSide() && !((ServerLevel) (context.getLevel())).getGameRules().get(DisableEndPortals.ENABLE_PORTAL_FILLING);
    }
}
