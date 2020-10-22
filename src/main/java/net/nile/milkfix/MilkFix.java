package net.nile.milkfix;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeatures.Configs;

public class MilkFix implements  ModInitializer {

public static final Logger logger = LogManager.getLogger();

    public static final String modid = "nilemilk";

    public static RottenMilkBucketItem ROTTEN_MILK_BUCKET = new RottenMilkBucketItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public static final Set<String> effectsToAdd = new HashSet<String>();

    public  static final Set<StatusEffect> milkRemoveEffects = new HashSet<StatusEffect>();
//TODO implement config registering of effects to remove
    @Override
    public void onInitialize() {
        // TODO Auto-generated method stub
        Registry.register(Registry.ITEM, new Identifier(modid, "rotten_milk_bucket"), ROTTEN_MILK_BUCKET);

        Path cfgFile = FabricLoader.getInstance().getConfigDir().resolve(modid);

        try {
            BufferedReader reader = Files.newBufferedReader(cfgFile);
            String line = null;
            while ((line = reader.readLine()) != null)  
            {
                effectsToAdd.add(line);
            }
            logger.info("MilkFix config found!");
        } catch (Exception e) {
            //TODO: handle exception
            logger.error("Error loading config file for MilkFix. Loading default config.", e);
            effectsToAdd.clear();
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

        Registry.STATUS_EFFECT.forEach((se)->{
            Identifier id = Registry.STATUS_EFFECT.getId(se);
            if(effectsToAdd.remove(id.toString()))
            {
                addStatusEffectToRemove(se);
            }
        });

        RegistryEntryAddedCallback.event(Registry.STATUS_EFFECT).register(new StatusEffectAddedCallback());
    }
    
    public static void addStatusEffectToRemove(StatusEffect effect)
    {
        milkRemoveEffects.add(effect);
    }

    public static void StatusEffectAdded(StatusEffect effect, Identifier id)
    {
        if(effectsToAdd.remove(id.toString()))
        {
            addStatusEffectToRemove(effect);
        }
    }
}
