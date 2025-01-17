package tfar.craftingstation.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import tfar.craftingstation.CraftingStation;
import tfar.craftingstation.CraftingStationContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;

public class TabButton extends Button{

  public final int index;
  public final ItemStack stack;
  public TabButton(int x, int y, int widthIn, int heightIn, Button.OnPress callback,OnTooltip tooltip, int index, ItemStack stack) {
    super(x, y, widthIn, heightIn,new TextComponent(""), callback,tooltip);
    this.index = index;
    this.stack = stack;
  }
  public static final ResourceLocation TAB = new ResourceLocation(CraftingStation.MODID,"textures/gui/tabs.png");


  @Override
  public void renderButton(PoseStack matrices,int mouseX, int mouseY, float partialTicks) {
      RenderSystem.setShaderTexture(0,TAB);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.enableDepthTest();
      if (((CraftingStationContainer) ClientStuffs.mc.player.containerMenu).getCurrentContainer() == index) {
        blit(matrices,x, y, 0, height, width, height,width,height * 2);
      } else {
        blit(matrices,x, y, 0, 0, width, height,width,height * 2);
      }
      if (!stack.isEmpty()) {
        ClientStuffs.mc.getItemRenderer().renderAndDecorateFakeItem(stack, x+3, y+3);
      }
      if (isHoveredOrFocused()) {
        this.renderToolTip(matrices,mouseX,mouseY);
      }
  }
}

