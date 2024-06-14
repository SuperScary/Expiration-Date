package superscary.expirationdate.helpers;

import net.minecraft.network.chat.Component;

public class TimeConverter {

    public static Component tickToTime (long ticks) {
        long hours = ticks / 1200 * 60;
        long days = hours / 24;
        long minute = ticks / 1200;
        long seconds = ticks / 20 - minute * 60;
        return Component.translatable("chat.expirationdate.time_remaining", Math.round(days), Math.round(hours), Math.round(minute), Math.round(seconds));
    }

    public static Component tickToTime (float ticks) {
        return tickToTime((long) ticks);
    }

}
