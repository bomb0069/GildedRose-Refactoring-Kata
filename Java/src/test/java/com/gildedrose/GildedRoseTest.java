package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normal_item_will_decrease_quality_and_sellIn_when_the_day_pass() {
        Item[] items = new Item[] { new Item("Normal Item", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

}
