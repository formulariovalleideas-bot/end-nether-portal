package bo.valleideas.endportal.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFireBlock.class)
public abstract class NetherPortalMixin {
 @Redirect(
   method = "onBlockAdded",
   at = @At(value="INVOKE", target="Lnet/minecraft/block/NetherPortalBlock;getNewPortal(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction$Axis;)Ljava/util/Optional;"),
   require = 0
 )
 private java.util.Optional<?> endnetherportal$portalInEnd(World world, BlockPos pos, net.minecraft.util.math.Direction.Axis axis) {
   return NetherPortalBlock.getNewPortal(world, pos, axis);
 }
}
