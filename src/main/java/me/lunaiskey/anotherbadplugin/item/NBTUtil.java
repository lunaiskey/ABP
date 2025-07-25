package me.lunaiskey.anotherbadplugin.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class NBTUtil {
    public static ItemStack addTestTag(ItemStack itemStack) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CustomData customData = nmsStack.get(DataComponents.CUSTOM_DATA);
        CompoundTag customDataTag;
        if (customData != null) {
            customDataTag = customData.copyTag();
        } else {
            customDataTag = new CompoundTag();
        }
        customDataTag.putBoolean("test", true);
        nmsStack.set(DataComponents.CUSTOM_DATA,CustomData.of(customDataTag));
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static boolean hasTestTag(ItemStack itemStack) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CustomData customData = nmsStack.get(DataComponents.CUSTOM_DATA);
        CompoundTag customDataTag;
        if (customData != null) {
            customDataTag = customData.copyTag();
        } else {
            customDataTag = new CompoundTag();
        }
        Optional<Boolean> hasTag = customDataTag.getBoolean("test");
        return hasTag.isPresent();
    }

    private static CompoundTag getCustomDataTag(ItemStack itemStack) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CustomData customData = nmsStack.get(DataComponents.CUSTOM_DATA);
        CompoundTag customDataTag;
        if (customData != null) {
            customDataTag = customData.copyTag();
        } else {
            customDataTag = new CompoundTag();
        }
        return customDataTag;
    }

    private static CompoundTag getABPCustomDataTag(CompoundTag baseCustomDataTag) {
        Optional<CompoundTag> tag = baseCustomDataTag.getCompound("ABP");
        return tag.orElseGet(CompoundTag::new);
    }

    public static CompoundTag getABPCustomDataTag(ItemStack itemStack) {
        return getABPCustomDataTag(getCustomDataTag(itemStack));
    }

    public static ItemStack applyABPCustomDataTag(ItemStack itemStack, CompoundTag abpCustomDataTag) {
        CompoundTag customDataTag = getCustomDataTag(itemStack);
        customDataTag.put("ABP",abpCustomDataTag);
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        nmsStack.set(DataComponents.CUSTOM_DATA,CustomData.of(customDataTag));
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
