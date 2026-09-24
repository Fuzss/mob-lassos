package fuzs.moblassos.common.data.client;

import fuzs.moblassos.common.MobLassos;
import fuzs.moblassos.common.init.ModRegistry;
import fuzs.moblassos.common.world.item.ContractItem;
import fuzs.moblassos.common.world.item.LassoItem;
import fuzs.moblassos.common.world.item.LassoType;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), MobLassos.MOD_NAME);
        this.add(ModRegistry.GOLDEN_LASSO_ITEM.value(), "Golden Lasso");
        this.add(((LassoItem) ModRegistry.GOLDEN_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Animals");
        this.add(ModRegistry.AQUATIC_LASSO_ITEM.value(), "Aquatic Lasso");
        this.add(((LassoItem) ModRegistry.AQUATIC_LASSO_ITEM.value()).getDescriptionComponent(),
                "Holds: Water Animals");
        this.add(ModRegistry.DIAMOND_LASSO_ITEM.value(), "Diamond Lasso");
        this.add(((LassoItem) ModRegistry.DIAMOND_LASSO_ITEM.value()).getDescriptionComponent(),
                "Holds: Any Animals");
        this.add(ModRegistry.EMERALD_LASSO_ITEM.value(), "Emerald Lasso");
        this.add(((LassoItem) ModRegistry.EMERALD_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Villagers");
        this.add(ModRegistry.HOSTILE_LASSO_ITEM.value(), "Hostile Lasso");
        this.add(((LassoItem) ModRegistry.HOSTILE_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Monsters");
        this.add(ModRegistry.CREATIVE_LASSO_ITEM.value(), "Creative Lasso");
        this.add(((LassoItem) ModRegistry.CREATIVE_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Any");
        this.add(ModRegistry.CONTRACT_ITEM.value(), "Contract");
        this.add(((ContractItem) ModRegistry.CONTRACT_ITEM.value()).getDescriptionComponent(),
                "Allows picking-up villagers in an emerald lasso. Leveling up the villager increases the chance to accept.");
        this.add(ModRegistry.HOLDING_ENCHANTMENT, "Holding");
        this.add(ModRegistry.HOLDING_ENCHANTMENT, "desc", "Increases the time a lasso is able to hold a mob.");
        this.add(LassoType.MOB_LASSO_FAILURE_KEY, "%s cannot be captured");
        this.add(LassoType.MOB_LASSO_CONTRACT_KEY, "%s must accept a contract first");
        this.add(LassoType.MOB_LASSO_HEALTH_KEY, "%s must be below %s health (currently %s)");
        this.add(ModRegistry.CONTRACT_ITEM.value().getDescriptionId() + ".accept", "%s accepts the contract");
        this.add(ModRegistry.CONTRACT_ITEM.value().getDescriptionId() + ".reject", "%s rejects the contract");
        this.add(LassoItem.KEY_REMAINING_TIME_IN_SECONDS, "Remaining: %ss");
        this.add(ModRegistry.LASSO_PICK_UP_SOUND_EVENT.value(), "Mob is picked-up");
        this.add(ModRegistry.LASSO_RELEASE_SOUND_EVENT.value(), "Mob is released");
    }
}
