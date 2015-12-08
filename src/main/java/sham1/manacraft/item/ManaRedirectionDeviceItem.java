package sham1.manacraft.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sham1.manacraft.ManaCraft;

import java.util.List;
import java.util.Optional;

public class ManaRedirectionDeviceItem extends Item {

    public ManaRedirectionDeviceItem() {
        setCreativeTab(ManaCraft.creativeTab);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote && playerIn.isSneaking()) {
            if (itemStack.getTagCompound() == null) {
                itemStack.setTagCompound(new NBTTagCompound());
                itemStack.getTagCompound().setString("mode", "bind");
            } else {
                switch (itemStack.getTagCompound().getString("mode")) {
                    case "bind":
                        itemStack.getTagCompound().setString("mode", "func");
                        break;
                    case "func":
                        itemStack.getTagCompound().setString("mode", "bind");
                        break;
                    default:
                        break;
                }
            }
        }

        return itemStack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        Optional<NBTTagCompound> optTag = Optional.ofNullable(stack.getTagCompound());
        if (optTag.isPresent()) {
            NBTTagCompound tag = optTag.get();
            String mode = tag.getString("mode");

            if (!mode.equals("")) {
                if (mode.equals("func")) mode = "function";
                tooltip.add(I18n.format("wand.function.selected", mode));
            } else {
                tooltip.add(I18n.format("no.wand.function.selected"));
            }
        } else {
            tooltip.add(I18n.format("no.wand.function.selected"));
        }
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("mode", "func");

        stack.setTagCompound(tag);
    }
}
