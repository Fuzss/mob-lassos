package fuzs.moblassos.common.world.item;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.Codec;
import fuzs.moblassos.common.MobLassos;
import fuzs.moblassos.common.config.ServerConfig;
import fuzs.moblassos.common.init.ModRegistry;
import fuzs.moblassos.common.init.ModTags;
import fuzs.puzzleslib.common.api.network.v4.codec.ExtraStreamCodecs;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

import java.util.*;

public enum LassoType implements StringRepresentable {
    GOLDEN(ModTags.CAPTURED_BY_GOLDEN_LASSO_ENTITY_TYPE_TAG, ModTags.NOT_CAPTURED_BY_GOLDEN_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return MobLassos.CONFIG.get(ServerConfig.class).goldenLasso.getMaxHoldingTime();
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return MobLassos.CONFIG.get(ServerConfig.class).goldenLasso.getAllMobs();
        }
    },
    AQUATIC(ModTags.CAPTURED_BY_AQUATIC_LASSO_ENTITY_TYPE_TAG, ModTags.NOT_CAPTURED_BY_AQUATIC_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return MobLassos.CONFIG.get(ServerConfig.class).aquaticLasso.getMaxHoldingTime();
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return MobLassos.CONFIG.get(ServerConfig.class).aquaticLasso.getAllMobs();
        }
    },
    DIAMOND(ModTags.CAPTURED_BY_DIAMOND_LASSO_ENTITY_TYPE_TAG, ModTags.NOT_CAPTURED_BY_DIAMOND_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return MobLassos.CONFIG.get(ServerConfig.class).diamondLasso.getMaxHoldingTime();
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return MobLassos.CONFIG.get(ServerConfig.class).diamondLasso.getAllMobs();
        }
    },
    EMERALD(ModTags.CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG, ModTags.NOT_CAPTURED_BY_EMERALD_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return MobLassos.CONFIG.get(ServerConfig.class).emeraldLasso.getMaxHoldingTime();
        }

        @Override
        Either<Component, Unit> canCaptureMob(Player player, Mob mob) {
            Either<Component, Unit> result = super.canCaptureMob(player, mob);
            if (!MobLassos.CONFIG.get(ServerConfig.class).emeraldLasso.villagersRequireContract) {
                return result;
            } else if (result.left().isEmpty() && !ModRegistry.VILLAGER_CONTRACT_ATTACHMENT_TYPE.has(mob)) {
                return Either.left(Component.translatable(MOB_LASSO_CONTRACT_KEY, mob.getDisplayName()));
            } else {
                return result;
            }
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return MobLassos.CONFIG.get(ServerConfig.class).emeraldLasso.getAllMobs();
        }
    },
    HOSTILE(ModTags.CAPTURED_BY_HOSTILE_LASSO_ENTITY_TYPE_TAG, ModTags.NOT_CAPTURED_BY_HOSTILE_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return MobLassos.CONFIG.get(ServerConfig.class).hostileLasso.getMaxHoldingTime();
        }

        @Override
        Either<Component, Unit> canCaptureMob(Player player, Mob mob) {
            Either<Component, Unit> result = super.canCaptureMob(player, mob);
            if (result.left().isEmpty()) {
                double hostileMobHealth = MobLassos.CONFIG.get(ServerConfig.class).hostileLasso.hostileMobHealth;
                if (mob.getHealth() / mob.getMaxHealth() >= hostileMobHealth) {
                    MutableComponent component = Component.translatable(MOB_LASSO_HEALTH_KEY,
                            mob.getDisplayName(),
                            String.format("%.0f", hostileMobHealth * mob.getMaxHealth()),
                            String.format("%.0f", mob.getHealth()));
                    return Either.left(component);
                }
            }
            return result;
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return MobLassos.CONFIG.get(ServerConfig.class).hostileLasso.getAllMobs();
        }
    },
    CREATIVE(null, ModTags.NOT_CAPTURED_BY_CREATIVE_LASSO_ENTITY_TYPE_TAG) {
        @Override
        OptionalInt getMaxHoldingTimeInSeconds() {
            return OptionalInt.empty();
        }

        @Override
        boolean canCaptureMob(Mob mob) {
            return !mob.is(ModTags.NOT_CAPTURED_BY_CREATIVE_LASSO_ENTITY_TYPE_TAG);
        }

        @Override
        Collection<MobCategory> getAllMobs() {
            return Collections.emptySet();
        }
    };

    public static final Collection<LassoType> VALUES = List.of(values());
    public static final Codec<MobCategory> CODEC = StringRepresentable.fromEnum(MobCategory::values);
    public static final StreamCodec<ByteBuf, MobCategory> STREAM_CODEC = ExtraStreamCodecs.fromEnum(MobCategory::values);
    public static final String MOB_LASSO_FAILURE_KEY = MobLassos.id("mob_lasso")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "failure");
    public static final String MOB_LASSO_CONTRACT_KEY = MobLassos.id("mob_lasso")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "contract");
    public static final String MOB_LASSO_HEALTH_KEY = MobLassos.id("mob_lasso")
            .toLanguageKey(Registries.elementsDirPath(Registries.ITEM), "health");

    @Nullable
    public final TagKey<EntityType<?>> capturedBy;
    @Nullable
    public final TagKey<EntityType<?>> notCapturedBy;

    LassoType(@Nullable TagKey<EntityType<?>> capturedBy, @Nullable TagKey<EntityType<?>> notCapturedBy) {
        this.capturedBy = capturedBy;
        this.notCapturedBy = notCapturedBy;
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public final boolean hasMaxHoldingTime() {
        return this.getMaxHoldingTimeInSeconds().isPresent();
    }

    public final int getMaxHoldingTime() {
        return this.getMaxHoldingTimeInSeconds().orElse(-1) * 20;
    }

    abstract OptionalInt getMaxHoldingTimeInSeconds();

    public final boolean canPlayerPickUp(Player player, Mob mob) {
        return this.canCaptureMob(player, mob).ifLeft((Component component) -> {
            player.sendOverlayMessage(component.copy().withStyle(ChatFormatting.RED));
        }).right().isPresent();
    }

    Either<Component, Unit> canCaptureMob(Player player, Mob mob) {
        if (this.canCaptureMob(mob) && this.isCorrectOwner(player, mob)) {
            return Either.right(Unit.INSTANCE);
        } else {
            return Either.left(Component.translatable(MOB_LASSO_FAILURE_KEY, mob.getDisplayName()));
        }
    }

    private boolean isCorrectOwner(Player player, Mob mob) {
        return !(mob instanceof OwnableEntity ownableEntity) || ownableEntity.getOwner() == null
                || ownableEntity.getOwner().is(player);
    }

    boolean canCaptureMob(Mob mob) {
        if (this.notCapturedBy != null && mob.is(this.notCapturedBy)) {
            return false;
        } else if (this.capturedBy != null && mob.is(this.capturedBy)) {
            return true;
        } else {
            return this.getAllMobs().contains(mob.getType().getCategory());
        }
    }

    abstract Collection<MobCategory> getAllMobs();
}
