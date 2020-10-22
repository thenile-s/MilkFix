package net.nile.milkfix;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MilkFix implements  ModInitializer {

public static final Logger logger = LogManager.getLogger();

    public static final String modid = "nilemilk";

    public static RottenMilkBucketItem ROTTEN_MILK_BUCKET = new RottenMilkBucketItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public  static final Set<StatusEffect> milkRemoveEffects = new HashSet<StatusEffect>();

    @Override
    public void onInitialize() {
        // TODO Auto-generated method stub
        Registry.register(Registry.ITEM, new Identifier(modid, "rotten_milk_bucket"), ROTTEN_MILK_BUCKET);

        milkRemoveEffects.add(StatusEffects.POISON);
        milkRemoveEffects.add(StatusEffects.BAD_OMEN);
        milkRemoveEffects.add(StatusEffects.BLINDNESS);
        milkRemoveEffects.add(StatusEffects.SLOWNESS);
        milkRemoveEffects.add(StatusEffects.WEAKNESS);
        milkRemoveEffects.add(StatusEffects.WITHER);
        milkRemoveEffects.add(StatusEffects.HUNGER);
        milkRemoveEffects.add(StatusEffects.MINING_FATIGUE);
        milkRemoveEffects.add(StatusEffects.NAUSEA);
        milkRemoveEffects.add(StatusEffects.UNLUCK);
    }
    
}
