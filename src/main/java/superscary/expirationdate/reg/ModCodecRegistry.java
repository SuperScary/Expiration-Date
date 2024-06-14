package superscary.expirationdate.reg;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import superscary.expirationdate.ExpirationDate;
import superscary.expirationdate.data.ModCodecs;

import static superscary.expirationdate.data.ModCodecs.EXPIRATION_DATA_CODEC;
import static superscary.expirationdate.data.ModCodecs.EXPIRATION_DATA_STREAM_CODEC;

public class ModCodecRegistry {

    public static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, ExpirationDate.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ModCodecs.ExpirationData>> EXPIRATION_DATA = REGISTRY.register("expiration_data",
            () -> DataComponentType.<ModCodecs.ExpirationData>builder()
                    .persistent(EXPIRATION_DATA_CODEC)
                    .networkSynchronized(EXPIRATION_DATA_STREAM_CODEC)
                    .build()
    );

}
