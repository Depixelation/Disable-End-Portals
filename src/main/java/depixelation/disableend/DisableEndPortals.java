package depixelation.disableend;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisableEndPortals implements ModInitializer {
	public static final String MOD_ID = "disable-end-portals";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final GameRule<Boolean> ENABLE_PORTAL_COLLISION = GameRuleBuilder.forBoolean(true).buildAndRegister(Identifier.parse("end_portal_collision"));
	public static final GameRule<Boolean> ENABLE_PORTAL_FILLING = GameRuleBuilder.forBoolean(true).buildAndRegister(Identifier.parse("end_portal_frame_filling"));
	public static final GameRule<Boolean> ENABLE_EYE_SEEKING = GameRuleBuilder.forBoolean(true).buildAndRegister(Identifier.parse("ender_eye_seek_stronghold"));

	@Override
	public void onInitialize() {
	}
}