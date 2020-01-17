package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normal_item_will_decrease_quality_and_sellIn_when_the_day_pass() {
        Item[] items = new Item[] { new Item("Normal Item", 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItemWithSellInDaysAndQuality(0, 0, app.items[0]);
    }
    
    @Test
    void the_quality_of_an_item_is_never_negative() {
        Item[] items = new Item[] { new Item("Normal Item", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItemWithSellInDaysAndQuality(1, 0, app.items[0]);
    }
    
    @Test
    void once_the_sell_by_date_has_passed_quality_degrades_twice_as_fast() {
        Item[] items = new Item[] { new Item("Normal Item", 0, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertItemWithSellInDaysAndQuality(-1, 6, app.items[0]);
    }

    private void assertItemWithSellInDaysAndQuality(int expectedSellIn, int expectedQuality, Item item) {
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
    
}
