package net.timardo.indrevemiplugin.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kneelawk.extramodintegrations.indrev.IRIntegration;

import dev.emi.emi.api.EmiRegistry;
import net.timardo.indrevemiplugin.IndustrialRevolutionEMIPlugin;

@Mixin(IRIntegration.class)
public class ExtraIntegrationsRegisterHook {
    @Inject(at = @At("HEAD"), method = "registerImpl", cancellable = true)
    private void init(EmiRegistry registryIn, CallbackInfo info) {
        IndustrialRevolutionEMIPlugin.LOGGER.info("Disabling ExtraIntegrations registrations");
        info.cancel();
    }
}
