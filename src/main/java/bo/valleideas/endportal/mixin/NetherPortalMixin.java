package bo.valleideas.endportal.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(AbstractFireBlock.class)
public abstract class NetherPortalMixin {
    @Inject(method = "onBlockAdded", at = @At("HEAD"), cancellable = true)
    private void endnetherportal$createPortalInEnd(
            BlockState state,
            World world,
            BlockPos pos,
            BlockState oldState,
            boolean notify,
            CallbackInfo ci) {

        if (world.getRegistryKey() != World.END) return;

        Optional<NetherPortal> portal = NetherPortal.getNewPortal(world, pos, Direction.Axis.X);
        if (portal.isEmpty()) {
            portal = NetherPortal.getNewPortal(world, pos, Direction.Axis.Z);
        }

        if (portal.isPresent()) {
            portal.get().createPortal();
            ci.cancel();
        }
    }
}
