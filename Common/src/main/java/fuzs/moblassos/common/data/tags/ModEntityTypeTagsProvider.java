package fuzs.moblassos.common.data.tags;

import fuzs.moblassos.common.world.item.LassoType;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTagsProvider extends AbstractTagProvider<EntityType<?>> {

    public ModEntityTypeTagsProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        for (LassoType lassoType : LassoType.values()) {
            this.tag(lassoType.getEntityTypeTagKey()).addOptionalTag("c:capturing_not_supported");
        }
    }
}
