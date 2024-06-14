package superscary.expirationdate.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ModCodecs {

    public static final Codec<ExpirationData> EXPIRATION_DATA_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("expired").forGetter(ExpirationData::expired),
                    Codec.FLOAT.optionalFieldOf("time", 24000f).forGetter(ExpirationData::time)
            ).apply(instance, ExpirationData::new)
    );
    public static final StreamCodec<ByteBuf, ExpirationData> EXPIRATION_DATA_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ExpirationData::expired,
            ByteBufCodecs.FLOAT, ExpirationData::time,
            ExpirationData::new
    );

    public record ExpirationData (boolean expired, float time) {
    }

}
