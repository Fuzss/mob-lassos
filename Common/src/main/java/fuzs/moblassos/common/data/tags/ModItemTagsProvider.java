package fuzs.moblassos.common.data.tags;

import fuzs.moblassos.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ModRegistry.LASSOS_ITEM_TAG)
                .add(ModRegistry.GOLDEN_LASSO_ITEM,
                        ModRegistry.AQUATIC_LASSO_ITEM,
                        ModRegistry.DIAMOND_LASSO_ITEM,
                        ModRegistry.EMERALD_LASSO_ITEM,
                        ModRegistry.HOSTILE_LASSO_ITEM,
                        ModRegistry.CREATIVE_LASSO_ITEM);
        this.tag(ModRegistry.LASSO_ENCHANTABLE_ITEM_TAG).addTag(ModRegistry.LASSOS_ITEM_TAG);
    }
}
