package useless.aetherenabler.mixins;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.block.BlockPortal;
import net.minecraft.core.block.material.Material;

import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(value = BlockFluid.class, remap = false)
public class WaterMixin extends Block {

    public WaterMixin(String key, int id, Material material) {
        super(key, id, material);
    }
    @Shadow
    private void checkForHarden(World world, int x, int y, int z) {
    }
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {

        if (world.getBlockId(x, y-1, z) == ((BlockPortal)Block.portalParadise).portalFrameId){
            ((BlockPortal)Block.portalParadise).tryToCreatePortal(world, x, y, z);
        }
        this.checkForHarden(world, x, y, z);

    }




}
