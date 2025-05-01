package depixelation.disableend.mixin;

import depixelation.disableend.DisableEndPortals;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(EndPortalBlock.class)
public class DisableCollisionMixin {
	@Inject(at = @At("HEAD"), method = "onEntityCollision", cancellable = true)
	private void init(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		if (!world.isClient && !Objects.requireNonNull(world.getServer()).getGameRules().getBoolean(DisableEndPortals.ENABLE_PORTAL_COLLISION)) {
			ci.cancel();
		}
	}
}