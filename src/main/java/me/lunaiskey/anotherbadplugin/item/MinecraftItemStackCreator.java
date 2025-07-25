package me.lunaiskey.anotherbadplugin.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MinecraftItemStackCreator {

    public static ItemStack getMinecraftItemStackFromItem(Item item) {
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(name(item));
        meta.lore(lore(item));
        stack.setItemMeta(meta);
        stack = applyNBTToItemStack(stack,item);
        return stack;
    }

    private static Component name(Item item) {
        return Component.text(item.getName(),item.getRarity().color).decoration(TextDecoration.ITALIC,false);
    }

    private static List<Component> lore(Item item) {
        List<Component> list = new ArrayList<>();
        list.add(Component.text(item.getRarity().name(),item.getRarity().color).decoration(TextDecoration.ITALIC,false).decorate(TextDecoration.BOLD));
        return list;
    }

    private static ItemStack applyNBTToItemStack(ItemStack itemStack, Item item) {
        CompoundTag abpTag = NBTUtil.getABPCustomDataTag(itemStack);
        abpTag.putString("identifier",item.getIdentifier());
        return NBTUtil.applyABPCustomDataTag(itemStack,abpTag);
    }
}
