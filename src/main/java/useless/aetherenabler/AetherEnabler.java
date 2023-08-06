package useless.aetherenabler;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockPortal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AetherEnabler implements ModInitializer {
    public static final String MOD_ID = "aetherenabler";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        LOGGER.info("AetherEnabler initialized.");
        ((BlockPortal)Block.portalParadise).portalTriggerId = Block.fluidWaterFlowing.id;
    }
}
