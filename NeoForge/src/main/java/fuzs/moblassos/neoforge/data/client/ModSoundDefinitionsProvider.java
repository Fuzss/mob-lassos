package fuzs.moblassos.neoforge.data.client;

import fuzs.moblassos.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.neoforge.api.client.data.v2.AbstractSoundProvider;
import net.minecraft.sounds.SoundEvents;

public class ModSoundDefinitionsProvider extends AbstractSoundProvider {

    public ModSoundDefinitionsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addSounds() {
        this.add(ModRegistry.LASSO_PICK_UP_SOUND_EVENT.value(), sound(SoundEvents.BEEHIVE_ENTER));
        this.add(ModRegistry.LASSO_RELEASE_SOUND_EVENT.value(), sound(SoundEvents.BEEHIVE_EXIT));
    }
}
