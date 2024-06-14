package superscary.expirationdate.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import superscary.expirationdate.ExpirationDate;
import superscary.expirationdate.helpers.TimeConverter;
import superscary.expirationdate.timer.ExpirationRegistry;

@EventBusSubscriber(modid = ExpirationDate.MOD_ID)
public class ClientEvents {

    private static int ticks = 0;

    @SubscribeEvent
    public static void toolTipEvent (ItemTooltipEvent event) {
        if (event.getItemStack().isEmpty() || event.getItemStack().getItem().getFoodProperties(event.getItemStack(), event.getEntity()) == null || event.getEntity() == null) {
            return;
        }

        float time = 0;
        if (!ExpirationRegistry.isFoodExpired(event.getItemStack())) {
            time = ExpirationRegistry.getTime(event.getItemStack());
            event.getToolTip().add(TimeConverter.tickToTime(time));
        } else {
            event.getToolTip().add(Component.translatable("chat.expirationdate.expired"));
        }
    }

    @SubscribeEvent
    public static void clientTick (ClientTickEvent.Pre event) {
        ticks++;
        if (ticks % 20 == 0) {
            assert Minecraft.getInstance().player != null;
            if (!Minecraft.getInstance().player.isCreative()) {
                ExpirationRegistry.updateTick(Minecraft.getInstance().player);
            }
            ticks = 0;
        }
    }

}
