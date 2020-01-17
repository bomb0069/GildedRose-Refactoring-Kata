package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normal_item_will_decrease_quality_and_sellIn_when_the_day_pass() {
        final GildedRose app = CreateGildedRoseWithItem("Normal Item", 1, 1);

        app.updateQuality();

        assertItemWithSellInDaysAndQuality(0, 0, app.items[0]);
    }
    
    @Test
    void the_quality_of_an_item_is_never_negative() {
        final GildedRose app = CreateGildedRoseWithItem("Normal Item", 2, 0);

        app.updateQuality();

        assertItemWithSellInDaysAndQuality(1, 0, app.items[0]);
    }
    
    @Test
    void once_the_sell_by_date_has_passed_quality_degrades_twice_as_fast() {
        final GildedRose app = CreateGildedRoseWithItem("Normal Item", 0, 8);

        app.updateQuality();

        assertItemWithSellInDaysAndQuality(-1, 6, app.items[0]);
    }

    @Test
    void once_the_sell_by_date_has_passed_the_quality_of_an_item_is_never_negative() {
        final GildedRose app = CreateGildedRoseWithItem("Normal Item", 0, 1);

        app.updateQuality();

        assertItemWithSellInDaysAndQuality(-1, 0, app.items[0]);
    }

    @Test
    void item_Aged_Brie_actually_increases_in_Quality_the_older_it_gets() {
        final GildedRose app = CreateGildedRoseWithItem("Aged Brie", 1, 1);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(0, 2, app.items[0]);
    }

    private GildedRose CreateGildedRoseWithItem(final String name, final int sellIn, final int quality) {
        final Item[] items = new Item[] { new Item(name, sellIn, quality) };
        final GildedRose app = new GildedRose(items);
        return app;
    }

    private void assertItemWithSellInDaysAndQuality(final int expectedSellIn, final int expectedQuality, final Item item) {
        assertEquals(expectedSellIn, item.sellIn);
        assertEquals(expectedQuality, item.quality);
    }
    
}
