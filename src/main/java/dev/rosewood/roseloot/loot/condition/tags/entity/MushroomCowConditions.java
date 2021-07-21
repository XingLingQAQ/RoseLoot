package dev.rosewood.roseloot.loot.condition.tags.entity;

import dev.rosewood.rosegarden.utils.NMSUtil;
import dev.rosewood.roseloot.loot.LootContext;
import dev.rosewood.roseloot.loot.condition.EntityConditions;
import dev.rosewood.roseloot.loot.condition.LootCondition;
import dev.rosewood.roseloot.loot.condition.LootConditions;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MushroomCow;

public class MushroomCowConditions extends EntityConditions {

    public MushroomCowConditions() {
        if (NMSUtil.getVersionNumber() >= 14)
            LootConditions.registerTag("mooshroom-variant", MooshroomVariantCondition.class);
    }

    public static class MooshroomVariantCondition extends LootCondition {

        private List<MushroomCow.Variant> variants;

        public MooshroomVariantCondition(String tag) {
            super(tag);
        }

        @Override
        public boolean checkInternal(LootContext context) {
            LivingEntity looted = context.getLootedEntity();
            if (!(looted instanceof MushroomCow))
                return false;
            return this.variants.contains(((MushroomCow) looted).getVariant());
        }

        @Override
        public boolean parseValues(String[] values) {
            this.variants = new ArrayList<>();

            for (String value : values) {
                try {
                    MushroomCow.Variant variant = MushroomCow.Variant.valueOf(value.toUpperCase());
                    this.variants.add(variant);
                } catch (Exception ignored) { }
            }

            return !this.variants.isEmpty();
        }

    }

}