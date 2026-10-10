package de.sunclient.mixin;

import de.sunclient.SunConfig;
import net.minecraft.class_1297;
import net.minecraft.class_310;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Zeigt den eigenen Nametag nur in Third-Person, wenn die Option aktiviert ist. */
@Mixin(value = class_897.class, remap = false)
public abstract class NametagMixin {
	@Inject(method = "method_3921", at = @At("RETURN"), cancellable = true, remap = false, require = 0)
	private void sunclient$showOwnNametag(class_1297 entity, double squaredDistanceToCamera,
		CallbackInfoReturnable<Boolean> cir) {
		class_310 mc = class_310.method_1551();
		if (SunConfig.INSTANCE.showOwnNametag
			&& mc.field_1724 != null
			&& entity == mc.field_1724
			&& mc.field_1690.method_31044().ordinal() != 0) {
			// Vanilla blendet den Nametag der Kamera-Entität normalerweise aus.
			// Im Third-Person-Modus überschreiben wir deshalb auch ein false-Ergebnis.
			cir.setReturnValue(true);
		}
	}
}
