package depixelation.disableend.mixin;

import depixelation.disableend.DisableEndPortals;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
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

import java.util.Objects;

@Mixin(EndPortalBlock.class)
public class DisableCollisionMixin {
	@Inject(at = @At("HEAD"), method = "entityInside", cancellable = true)
	private void init(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl, CallbackInfo ci) {
		if (!world.isClientSide() && !((ServerLevel) world).getGameRules().get(DisableEndPortals.ENABLE_PORTAL_COLLISION)) {
			ci.cancel();
		}
	}
}