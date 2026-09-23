package bo.valleideas.endportal.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFireBlock.class)
public abstract class NetherPortalMixin {
    @Redirect(
        method = "shouldLightPortalAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractFireBlock;isOverworldOrNether(Lnet/minecraft/world/World;)Z"
        )
    )
    private static boolean endnetherportal$allowEnd(World world) {
        return true;
    }
}
