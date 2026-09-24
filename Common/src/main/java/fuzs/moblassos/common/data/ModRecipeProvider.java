package fuzs.moblassos.common.data;

import fuzs.moblassos.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModRegistry.GOLDEN_LASSO_ITEM.value())
                .define('#', Items.STRING)
                .define('X', Items.GOLD_NUGGET)
                .define('&', Items.ENDER_PEARL)
                .pattern("X#X")
                .pattern("#&#")
                .pattern("X#X")
                .unlockedBy(getHasName(Items.ENDER_PEARL), this.has(Items.ENDER_PEARL))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModRegistry.AQUATIC_LASSO_ITEM.value())
                .define('#', Ingredient.of(Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH))
                .define('X', Items.LAPIS_LAZULI)
                .define('&', ModRegistry.GOLDEN_LASSO_ITEM.value())
                .pattern(" X ")
                .pattern("#&#")
                .pattern(" X ")
                .unlockedBy(getHasName(ModRegistry.GOLDEN_LASSO_ITEM.value()),
                        this.has(ModRegistry.GOLDEN_LASSO_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModRegistry.DIAMOND_LASSO_ITEM.value())
                .define('#', Items.DIAMOND)
                .define('&', ModRegistry.GOLDEN_LASSO_ITEM.value())
                .define('X', ModRegistry.AQUATIC_LASSO_ITEM.value())
                .pattern(" # ")
                .pattern("X#&")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.AQUATIC_LASSO_ITEM.value()),
                        this.has(ModRegistry.AQUATIC_LASSO_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModRegistry.EMERALD_LASSO_ITEM.value())
                .define('#', Items.EMERALD)
                .define('&', ModRegistry.GOLDEN_LASSO_ITEM.value())
                .pattern(" # ")
                .pattern("#&#")
                .pattern(" # ")
                .unlockedBy(getHasName(ModRegistry.GOLDEN_LASSO_ITEM.value()),
                        this.has(ModRegistry.GOLDEN_LASSO_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModRegistry.HOSTILE_LASSO_ITEM.value())
                .define('#', Items.IRON_NUGGET)
                .define('+', Items.BLAZE_ROD)
                .define('X', Ingredient.of(Items.LEATHER, Items.RABBIT_HIDE))
                .define('&', ModRegistry.GOLDEN_LASSO_ITEM.value())
                .pattern("X+X")
                .pattern("#&#")
                .pattern("X+X")
                .unlockedBy(getHasName(ModRegistry.GOLDEN_LASSO_ITEM.value()),
                        this.has(ModRegistry.GOLDEN_LASSO_ITEM.value()))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.TOOLS, ModRegistry.CONTRACT_ITEM.value())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.FEATHER)
                .requires(Items.INK_SAC)
                .requires(Items.PAPER)
                .unlockedBy(getHasName(Items.PAPER), this.has(Items.PAPER))
                .save(this.output);
    }
}
