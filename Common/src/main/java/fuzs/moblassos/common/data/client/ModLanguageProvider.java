package fuzs.moblassos.common.data.client;

import fuzs.moblassos.common.MobLassos;
import fuzs.moblassos.common.init.ModRegistry;
import fuzs.moblassos.common.world.item.ContractItem;
import fuzs.moblassos.common.world.item.LassoItem;
import fuzs.moblassos.common.world.item.LassoType;
import fuzs.puzzleslib.common.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), MobLassos.MOD_NAME);
        builder.add(ModRegistry.GOLDEN_LASSO_ITEM.value(), "Golden Lasso");
        builder.add(((LassoItem) ModRegistry.GOLDEN_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Animals");
        builder.add(ModRegistry.AQUATIC_LASSO_ITEM.value(), "Aquatic Lasso");
        builder.add(((LassoItem) ModRegistry.AQUATIC_LASSO_ITEM.value()).getDescriptionComponent(),
                "Holds: Water Animals");
        builder.add(ModRegistry.DIAMOND_LASSO_ITEM.value(), "Diamond Lasso");
        builder.add(((LassoItem) ModRegistry.DIAMOND_LASSO_ITEM.value()).getDescriptionComponent(),
                "Holds: Any Animals");
        builder.add(ModRegistry.EMERALD_LASSO_ITEM.value(), "Emerald Lasso");
        builder.add(((LassoItem) ModRegistry.EMERALD_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Villagers");
        builder.add(ModRegistry.HOSTILE_LASSO_ITEM.value(), "Hostile Lasso");
        builder.add(((LassoItem) ModRegistry.HOSTILE_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Monsters");
        builder.add(ModRegistry.CREATIVE_LASSO_ITEM.value(), "Creative Lasso");
        builder.add(((LassoItem) ModRegistry.CREATIVE_LASSO_ITEM.value()).getDescriptionComponent(), "Holds: Any");
        builder.add(ModRegistry.CONTRACT_ITEM.value(), "Contract");
        builder.add(((ContractItem) ModRegistry.CONTRACT_ITEM.value()).getDescriptionComponent(),
                "Allows picking-up villagers in an emerald lasso. Leveling up the villager increases the chance to accept.");
        builder.add(ModRegistry.HOLDING_ENCHANTMENT, "Holding");
        builder.add(ModRegistry.HOLDING_ENCHANTMENT, "desc", "Increases the time a lasso is able to hold a mob.");
        builder.add(LassoType.MOB_LASSO_FAILURE_KEY, "%s cannot be captured");
        builder.add(LassoType.MOB_LASSO_CONTRACT_KEY, "%s must accept a contract first");
        builder.add(LassoType.MOB_LASSO_HEALTH_KEY, "%s must be below %s health (currently %s)");
        builder.add(ModRegistry.CONTRACT_ITEM.value().getDescriptionId() + ".accept", "%s accepts the contract");
        builder.add(ModRegistry.CONTRACT_ITEM.value().getDescriptionId() + ".reject", "%s rejects the contract");
        builder.add(LassoItem.KEY_REMAINING_TIME_IN_SECONDS, "Remaining: %ss");
        builder.add(ModRegistry.LASSO_PICK_UP_SOUND_EVENT.value(), "Mob is picked-up");
        builder.add(ModRegistry.LASSO_RELEASE_SOUND_EVENT.value(), "Mob is released");
    }
}
