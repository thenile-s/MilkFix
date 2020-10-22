package net.nile.milkfix;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;

public class StatusEffectAddedCallback implements RegistryEntryAddedCallback<StatusEffect> {

    @Override
    public void onEntryAdded(int rawId, Identifier id, StatusEffect object) {
        MilkFix.StatusEffectAdded(object, id);
    }
    
}