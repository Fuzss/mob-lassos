package fuzs.moblassos.fabric.client;

import fuzs.moblassos.common.MobLassos;
import fuzs.moblassos.common.client.MobLassosClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class MobLassosFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(MobLassos.MOD_ID, MobLassosClient::new);
    }
}
