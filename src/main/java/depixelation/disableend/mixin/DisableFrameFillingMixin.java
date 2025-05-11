package depixelation.disableend.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import depixelation.disableend.DisableEndPortals;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(EnderEyeItem.class)
public class DisableFrameFillingMixin {
    private ItemUsageContext context;
    @ModifyExpressionValue(
            method = "useOnBlock",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;")
    )

    //For the conditional check for if the eye should NOT be used
    public Comparable modifyCondition(Comparable original) {
        return ((Boolean) original) || !context.getWorld().isClient && !Objects.requireNonNull(context.getWorld().getServer()).getGameRules().getBoolean(DisableEndPortals.ENABLE_PORTAL_FILLING);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    public void captureUsageContext(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir){
        this.context = context;
    }
}
