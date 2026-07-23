package fuzs.moblassos.common.data.tags;

import com.google.common.collect.Sets;
import fuzs.moblassos.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagAppender;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.MobCategory;

import java.util.Collection;
import java.util.function.BiConsumer;

public class ModEntityTypeTagsProvider extends AbstractTagProvider<EntityType<?>> {

    public ModEntityTypeTagsProvider(DataProviderContext context) {
        super(Registries.ENTITY_TYPE, context);
    }

    @Override
    public void addTags(HolderLookup.Provider context) {
        this.addAll(context,
                ModRegistry.CAPTURED_BY_GOLDEN_LASSO_ENTITY_TYPE_TAG,
                Sets.immutableEnumSet(MobCategory.CREATURE, MobCategory.AMBIENT));
        this.addAll(context,
                ModRegistry.CAPTURED_BY_AQUATIC_LASSO_ENTITY_TYPE_TAG,
                Sets.immutableEnumSet(MobCategory.WATER_AMBIENT,
                        MobCategory.WATER_CREATURE,
                        MobCategory.UNDERGROUND_WATER_CREATURE));
        this.addAll(context,
                ModRegistry.CAPTURED_BY_DIAMOND_LASSO_ENTITY_TYPE_TAG,
                Sets.immutableEnumSet(MobCategory.CREATURE,
                        MobCategory.AMBIENT,
                        MobCategory.WATER_AMBIENT,
                        MobCategory.WATER_CREATURE,
                        MobCategory.UNDERGROUND_WATER_CREATURE));
        this.addAll(ModRegistry.CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG)
                .add(EntityTypeIds.VILLAGER, EntityTypeIds.WANDERING_TRADER);
        this.addAll(context,
                ModRegistry.CAPTURED_BY_HOSTILE_LASSO_ENTITY_TYPE_TAG,
                Sets.immutableEnumSet(MobCategory.MONSTER));
        this.tag(ModRegistry.NOT_CAPTURED_BY_CREATIVE_LASSO_ENTITY_TYPE_TAG)
                .addOptionalTag("c:capturing_not_supported", "c:bosses");
    }

    private AbstractTagAppender<EntityType<?>> addAll(HolderLookup.Provider context, TagKey<EntityType<?>> key, Collection<MobCategory> categories) {
        return this.addAll(context,
                key,
                (AbstractTagAppender<EntityType<?>> tag, Holder.Reference<EntityType<?>> holder) -> {
                    if (categories.contains(holder.value().getCategory())) {
                        tag.add(holder);
                    }
                });
    }

    private AbstractTagAppender<EntityType<?>> addAll(HolderLookup.Provider context, TagKey<EntityType<?>> key, BiConsumer<AbstractTagAppender<EntityType<?>>, Holder.Reference<EntityType<?>>> consumer) {
        AbstractTagAppender<EntityType<?>> tag = this.addAll(key);
        context.lookupOrThrow(Registries.ENTITY_TYPE)
                .listElements()
                .forEach((Holder.Reference<EntityType<?>> holder) -> {
                    consumer.accept(tag, holder);
                });
        return tag;
    }

    private AbstractTagAppender<EntityType<?>> addAll(TagKey<EntityType<?>> key) {
        return this.tag(key).removeOptionalTag("c:capturing_not_supported", "c:bosses");
    }
}
