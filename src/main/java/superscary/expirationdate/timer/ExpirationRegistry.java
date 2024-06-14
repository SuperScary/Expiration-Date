package superscary.expirationdate.timer;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import superscary.expirationdate.data.ModCodecs;
import superscary.expirationdate.reg.ModCodecRegistry;

import java.util.Objects;

public class ExpirationRegistry {

    static Logger logger = LoggerFactory.getLogger(ExpirationRegistry.class);

    public static float createNew (ItemStack stack) {
        if (stack.get(ModCodecRegistry.EXPIRATION_DATA.get()) != null)
            return Objects.requireNonNull(stack.get(ModCodecRegistry.EXPIRATION_DATA.get())).time();
        return Objects.requireNonNull(stack.set(ModCodecRegistry.EXPIRATION_DATA.get(), new ModCodecs.ExpirationData(false, 24000f))).time();
    }

    public static boolean isFoodExpired (ItemStack stack) {
        return Objects.requireNonNull(stack.get(ModCodecRegistry.EXPIRATION_DATA.get())).expired();
    }

    public static float getTime (ItemStack stack) {
        return Objects.requireNonNull(stack.get(ModCodecRegistry.EXPIRATION_DATA.get())).time();
    }

    public static void updateTick (Player player) {
        Inventory inventory = player.getInventory();
        for (var item : inventory.items) {
            if (item.getFoodProperties(player) != null && item.get(ModCodecRegistry.EXPIRATION_DATA.get()) != null) {
                var data = item.get(ModCodecRegistry.EXPIRATION_DATA.get());
                float currentTime = data.time();
                item.set(ModCodecRegistry.EXPIRATION_DATA.get(), new ModCodecs.ExpirationData(false, currentTime - 20));
                if (currentTime <= 0) {
                    setExpired(item);
                }
            }
        }
    }

    public static void setExpired (ItemStack stack) {
        stack.set(ModCodecRegistry.EXPIRATION_DATA.get(), new ModCodecs.ExpirationData(true, 0));
    }

}
