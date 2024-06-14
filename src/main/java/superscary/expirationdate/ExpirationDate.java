package superscary.expirationdate;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import superscary.expirationdate.reg.ModCodecRegistry;

@Mod(ExpirationDate.MODID)
public final class ExpirationDate {

    public static final String MODID = "expirationdate";

    public ExpirationDate (IEventBus modEventBus) {
        ModCodecRegistry.REGISTRY.register(modEventBus);
    }
}
