package net.nile.milkfix.mixins;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.nile.milkfix.MilkFix;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin{
    @Inject(at = @At("HEAD"), method = "finishUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;", cancellable = true)
    private void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cb)
    {
            if (user instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
                Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
                serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.MILK_BUCKET));
             }
             
             if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
                stack.decrement(1);
             }
       
             if (!world.isClient) {
                StatusEffectInstance[] effects = user.getStatusEffects().toArray(new StatusEffectInstance[user.getStatusEffects().size()]);
                 for (int i = 0; i < effects.length; i++) {
                     if(MilkFix.milkRemoveEffects.contains(effects[i].getEffectType()))
                     {
                         user.removeStatusEffect(effects[i].getEffectType());
                     }
                 }
             }

         cb.setReturnValue(stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack);
    }
}