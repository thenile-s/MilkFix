# Nile Milk Fix

## What it does

Change the behaviour of milk; remove/keep effects you choose!

## Config file
Located in the game directory in /config/milkfix  
The first line should be either 'whitelist' or 'blacklist' depending on how you wish to filter the status effects. The following lines should include the namespaced ids of the effects to be used by the filter.

### Example:
blacklist  
minecraft:poison  
minecraft:weakness  

This configuration will remove vanilla poison and weakness status effects when milk is drunk.

### Reverting to vanilla behaviour:
whitelist

This configuration has the same behaviour as vanilla milk

### If the config is missing
The mod will use its default config of blacklisting vanilla debuffs and other effects like invisibility and levitation.

### Important: If the configuration does not remove invisibility upon milk drinking, the wandering trader will drink milk forever and be annoying! It is highly reccomended to not whitelist invisibility or to blacklist invisibility in order to remove it upon milk drinking!

## Rotten mix
To compensate for the removal of the old milk, a new item, 'Rotten Mix' is crafted with rotten flesh, a spider eye and a slimeball. It acts as the old milk, removing all status effects upon use.

## Licence
https://creativecommons.org/licenses/by-nc/4.0/legalcode
TL;DR; You may distribute and modify this mod non-commercially, as long as you credit me and indicate the changes you have made.
