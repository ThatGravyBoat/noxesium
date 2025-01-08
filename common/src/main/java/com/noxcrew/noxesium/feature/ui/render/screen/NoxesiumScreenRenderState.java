package com.noxcrew.noxesium.feature.ui.render.screen;

import com.noxcrew.noxesium.feature.ui.BufferHelper;
import com.noxcrew.noxesium.feature.ui.render.DynamicElement;
import com.noxcrew.noxesium.feature.ui.render.SharedVertexBuffer;
import com.noxcrew.noxesium.feature.ui.render.api.NoxesiumRenderState;
import com.noxcrew.noxesium.feature.ui.render.buffer.BufferData;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

/**
 * Stores the render state for the on-screen UI element.
 */
public class NoxesiumScreenRenderState extends NoxesiumRenderState {

    private Screen lastScreen;
    private final DynamicElement dynamic = new DynamicElement();

    /**
     * Returns the dynamic element used for rendering the screen.
     */
    public DynamicElement dynamic() {
        return dynamic;
    }

    /**
     * Renders the given screen.
     */
    public boolean render(GuiGraphics guiGraphics, int width, int height, float deltaTime, Screen screen) {
        var nanoTime = System.nanoTime();

        // Try to update the buffer
        if (lastScreen != screen) {
            dynamic.redraw();
            lastScreen = screen;
        }

        // Update the buffer and redraw it if necessary
        dynamic.update(nanoTime, guiGraphics, () -> screen.renderWithTooltip(guiGraphics, width, height, deltaTime));
        BufferHelper.unbind();

        // If the buffer is invalid we draw directly instead of using it
        if (dynamic.isInvalid()) return false;

        // If the buffer is valid we use it to draw
        var ids = new ArrayList<BufferData>();
        dynamic.submitTextureIds(ids);
        SharedVertexBuffer.draw(ids);
        renders.increment();
        return true;
    }

    @Override
    public void requestCheck() {
        dynamic.requestCheck();
    }

    @Override
    public void updateRenderFramerate() {
        dynamic.resetToMax();
    }

    @Override
    public void tick() {
        dynamic.tick();
    }

    @Override
    public void close() {
        dynamic.close();
    }
}
