package net.timardo.indrevemiplugin.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kneelawk.extramodintegrations.indrev.IRIntegration;

import dev.emi.emi.api.EmiRegistry;
import net.timardo.indrevemiplugin.IndustrialRevolutionEMIPlugin;
import net.timardo.indrevemiplugin.config.IREmiPluginConfig;

@Mixin(IRIntegration.class)
public class ExtraIntegrationsRegisterHook {
    @Inject(at = @At("HEAD"), method = "registerImpl", cancellable = true, remap = false)
    private void init(EmiRegistry registryIn, CallbackInfo info) {
        // the config will be reloaded twice each time EMI loads the registry
        // this is because I don't know if my or the Extra Integrations' registry method will get called first
        if (IREmiPluginConfig.INSTANCE.reload().disableExtraModIntegration) {
            IndustrialRevolutionEMIPlugin.LOGGER.info("Disabling ExtraIntegrations registrations");
            info.cancel();
        }
    }
}
