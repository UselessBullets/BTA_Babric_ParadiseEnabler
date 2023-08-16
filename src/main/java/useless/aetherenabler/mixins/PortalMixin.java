package useless.aetherenabler.mixins;

import net.minecraft.core.block.BlockPortal;
import net.minecraft.core.block.BlockTransparent;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockPortal.class, remap = false)
public class PortalMixin extends BlockTransparent {

    public PortalMixin(String key, int id, Material material, boolean renderInside) {
        super(key, id, material, renderInside);
    }

    /**
     * @author Useless
     * @reason Hasty serverside patch
     */
    @Overwrite
    public boolean tryToCreatePortal(World world, int x, int y, int z) {
        int[] bounds = this.getPortalDims(world, x, y, z);
        if (bounds == null) {
            return false;
        } else {
            x = bounds[1];
            y = bounds[2];
            z = bounds[3];
            world.editingBlocks = true;

            for(int ra = 1; ra < bounds[4]; ++ra) {
                for(int ry = 1; ry < bounds[5]; ++ry) {
                    world.setBlockAndMetadata(x + (bounds[0] == 0 ? ra : 0), y + ry, z + (bounds[0] == 1 ? ra : 0), this.id, bounds[0] & 1);
                    world.notifyBlockChange(x + (bounds[0] == 0 ? ra : 0), y + ry, z + (bounds[0] == 1 ? ra : 0), this.id);
                }
            }

            world.setBlockMetadata(x + (bounds[0] == 0 ? 1 : 0), y + 1, z + (bounds[0] == 1 ? 1 : 0), bounds[0] & 15 | 2);
            world.markBlocksDirty(x + (bounds[0] == 0 ? 1 : 0), y + 1, z + (bounds[0] == 1 ? 1 : 0), x + (bounds[0] == 0 ? bounds[4] : 0), y + bounds[5], z + (bounds[0] == 1 ? bounds[4] : 0));
            world.editingBlocks = false;
            return true;
        }
    }
    @Shadow
    private int[] getPortalDims(World world, int x, int y, int z) {
        return new int[0];
    }
}
