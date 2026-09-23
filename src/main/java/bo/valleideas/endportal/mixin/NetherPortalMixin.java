package bo.valleideas.endportal.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFireBlock.class)
public abstract class NetherPortalMixin {
    @Inject(method = "shouldLightPortalAt", at = @At("HEAD"), cancellable = true)
    private static void endnetherportal$allowEnd(
            World world,
            BlockPos pos,
            Direction direction,
            CallbackInfoReturnable<Boolean> cir) {

        if (world.getRegistryKey() == World.END) {
            Direction.Axis axis = direction.rotateYClockwise().getAxis();
            cir.setReturnValue(NetherPortal.getNewPortal(world, pos, axis).isPresent());
        }
    }
}
