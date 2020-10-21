package net.nile.milkfix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
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

    @Override
    public void onInitialize() {
        // TODO Auto-generated method stub
        Registry.register(Registry.ITEM, new Identifier(modid, "rotten_milk_bucket"), ROTTEN_MILK_BUCKET);
    }
    
}
