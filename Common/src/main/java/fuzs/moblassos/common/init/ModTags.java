package fuzs.moblassos.common.init;

import fuzs.moblassos.common.MobLassos;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class ModTags {
    static final TagFactory TAGS = TagFactory.make(MobLassos.MOD_ID);
    public static final TagKey<EntityType<?>> CAPTURED_BY_GOLDEN_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "captured_by_golden_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_GOLDEN_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_golden_lasso");
    public static final TagKey<EntityType<?>> CAPTURED_BY_AQUATIC_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "captured_by_aqua_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_AQUATIC_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_aqua_lasso");
    public static final TagKey<EntityType<?>> CAPTURED_BY_DIAMOND_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "captured_by_diamond_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_DIAMOND_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_diamond_lasso");
    public static final TagKey<EntityType<?>> CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "captured_by_emerald_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_emerald_lasso");
    public static final TagKey<EntityType<?>> CAPTURED_BY_HOSTILE_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "captured_by_hostile_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_HOSTILE_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_hostile_lasso");
    public static final TagKey<EntityType<?>> NOT_CAPTURED_BY_CREATIVE_LASSO_ENTITY_TYPE_TAG = TAGS.registerEntityTypeTag(
            "not_captured_by_creative_lasso");
    public static final TagKey<Item> LASSO_ENCHANTABLE_ITEM_TAG = TAGS.registerItemTag("enchantable/lasso");
    public static final TagKey<Item> LASSOS_ITEM_TAG = TAGS.registerItemTag("lassos");

    public static void bootstrap() {
        // NO-OP
    }
}
