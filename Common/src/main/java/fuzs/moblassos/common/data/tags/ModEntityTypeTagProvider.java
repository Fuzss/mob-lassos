package fuzs.moblassos.common.data.tags;

import fuzs.moblassos.common.init.ModTags;
import fuzs.moblassos.common.world.item.LassoType;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;

public class ModEntityTypeTagProvider extends AbstractTagProvider<EntityType<?>> {

    public ModEntityTypeTagProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider context) {
        for (LassoType type : LassoType.VALUES) {
            if (type.capturedBy != null) {
                AbstractTagAppender<EntityType<?>> tag = this.tag(type.capturedBy);
                if (type.capturedBy == ModTags.CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG) {
                    tag.add(EntityTypeIds.VILLAGER, EntityTypeIds.WANDERING_TRADER);
                }
            }

            if (type.notCapturedBy != null) {
                this.tag(type.notCapturedBy).addOptionalTag("c:capturing_not_supported", "c:bosses");
            }
        }
    }
}
