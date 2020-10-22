package net.nile.milkfix;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class RottenMilkBucketItem extends MilkBucketItem{

    public RottenMilkBucketItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        //TODO maybe increment stats/criterion like vanilla mlik
        if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
            stack.decrement(1);
         }

        if (!world.isClient) {
            user.clearStatusEffects();
         }
         
        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
     }
}