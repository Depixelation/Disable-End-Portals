package com.example.disableendportals;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRules;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DisableEndPortals.MOD_ID)
public class DisableEndPortals {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "disable_end_portals";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static GameRule<Boolean> FRAME_FILLING;
    public static String FRAME_FILLING_ID = "enable_end_portal_filling";

    public static GameRule<Boolean> EYE_LOCATING;
    public static String EYE_LOCATING_ID = "enable_stronghold_locating";

    public static GameRule<Boolean> END_PORTAL_COLLISION;
    public static String END_PORTAL_COLLISION_ID = "enable_end_portal_collision";

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public DisableEndPortals(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void registerGameRules(RegisterEvent event) {
        event.register(net.minecraft.core.registries.Registries.GAME_RULE, helper -> {
            FRAME_FILLING = GameRules.registerBoolean(FRAME_FILLING_ID, GameRuleCategory.MISC, false);
        });
        event.register(net.minecraft.core.registries.Registries.GAME_RULE, helper -> {
            EYE_LOCATING = GameRules.registerBoolean(EYE_LOCATING_ID, GameRuleCategory.MISC, false);
        });
        event.register(net.minecraft.core.registries.Registries.GAME_RULE, helper -> {
            END_PORTAL_COLLISION = GameRules.registerBoolean(END_PORTAL_COLLISION_ID, GameRuleCategory.MISC, false);
        });
    }
}
