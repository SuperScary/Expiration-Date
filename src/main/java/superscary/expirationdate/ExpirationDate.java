package superscary.expirationdate;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import superscary.expirationdate.reg.ModCodecRegistry;

@Mod(ExpirationDate.MOD_ID)
public final class ExpirationDate {

    public static final String MOD_ID = "expirationdate";

    public ExpirationDate (IEventBus modEventBus) {
        ModCodecRegistry.REGISTRY.register(modEventBus);
    }
}
