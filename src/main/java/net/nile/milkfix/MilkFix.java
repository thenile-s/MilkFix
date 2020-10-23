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
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MilkFix implements  ModInitializer {

public static final Logger logger = LogManager.getLogger();

    public static final String modid = "nilemilk";

    public static RottenMilkBucketItem ROTTEN_MILK_BUCKET = new RottenMilkBucketItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    public static final Set<String> effectsToAdd = new HashSet<String>();

    public static boolean blacklist;

    public  static final Set<StatusEffect> milkAffectedEffects = new HashSet<StatusEffect>();
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(modid, "rotten_milk_bucket"), ROTTEN_MILK_BUCKET);

        Path cfgFile = FabricLoader.getInstance().getConfigDir().resolve(modid);

        try {
            BufferedReader reader = Files.newBufferedReader(cfgFile);
            String line = null;
            line = reader.readLine();
            if(line.equals("blacklist"))
            {
                blacklist = true;
            }
            else if (line.equals("whitelist"))
            {
                blacklist = false;
            }
            else{
                throw new Exception("Not specified whether a whitelist or blacklist is used!");
            }
            while ((line = reader.readLine()) != null)  
            {
                effectsToAdd.add(line);
            }
            reader.close();
            logger.info("MilkFix config found!");
        } catch (Exception e) {
            logger.error("Error loading config file for MilkFix. Loading default config: blacklisting vanilla debuffs.", e);
            effectsToAdd.clear();
            milkAffectedEffects.add(StatusEffects.POISON);
            milkAffectedEffects.add(StatusEffects.BAD_OMEN);
            milkAffectedEffects.add(StatusEffects.BLINDNESS);
            milkAffectedEffects.add(StatusEffects.SLOWNESS);
            milkAffectedEffects.add(StatusEffects.WEAKNESS);
            milkAffectedEffects.add(StatusEffects.WITHER);
            milkAffectedEffects.add(StatusEffects.HUNGER);
            milkAffectedEffects.add(StatusEffects.MINING_FATIGUE);
            milkAffectedEffects.add(StatusEffects.NAUSEA);
            milkAffectedEffects.add(StatusEffects.UNLUCK);
            milkAffectedEffects.add(StatusEffects.INVISIBILITY);
            milkAffectedEffects.add(StatusEffects.LEVITATION);
            milkAffectedEffects.add(StatusEffects.SLOW_FALLING);
            milkAffectedEffects.add(StatusEffects.GLOWING);
            blacklist = true;
        }

        Registry.STATUS_EFFECT.forEach((se)->{
            Identifier id = Registry.STATUS_EFFECT.getId(se);
            if(effectsToAdd.remove(id.toString()))
            {
                addStatusEffectToRemove(se);
            }
        });

        RegistryEntryAddedCallback.event(Registry.STATUS_EFFECT).register(new StatusEffectAddedCallback());

        //TODO: maybe check if all effects in the config are registered in the game
    }
    
    public static void addStatusEffectToRemove(StatusEffect effect)
    {
        milkAffectedEffects.add(effect);
    }

    public static void StatusEffectAdded(StatusEffect effect, Identifier id)
    {
        if(effectsToAdd.remove(id.toString()))
        {
            addStatusEffectToRemove(effect);
        }
    }
}
