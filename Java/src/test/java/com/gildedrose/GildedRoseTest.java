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

    @Test
    void when_Quality_increased_The_Quality_of_an_item_is_never_more_than_50() {
        final GildedRose app = CreateGildedRoseWithItem("Aged Brie", 10, 50);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(9, 50, app.items[0]);
    }

    @Test
    void item_Sulfuras_being_a_legendary_item_never_has_to_be_sold_or_decreases_in_Quality() {
        final GildedRose app = CreateGildedRoseWithItem("Sulfuras, Hand of Ragnaros", 10, 20);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(10, 20, app.items[0]);
    }

    @Test
    void item_Backstage_passes_should_be_increases_in_Quality_as_its_SellIn_value_approaches () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 30, 20);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(29, 21, app.items[0]);
    }

    @Test
    void item_Backstage_passes_Quality_should_be_increases_by_1_when_there_are_11_days () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 30, 11);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(29, 12, app.items[0]);
    }

    @Test
    void item_Backstage_passes_Quality_should_be_increases_by_2_when_there_are_10_days () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 10, 11);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(9, 13, app.items[0]);
    }
    
    @Test
    void item_Backstage_passes_Quality_should_be_increases_by_2_when_there_are_less_than_10_days () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 9, 11);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(8, 13, app.items[0]);
    }
    
    @Test
    void item_Backstage_passes_Quality_should_be_increases_by_3_when_there_are_5_days () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 5, 11);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(4, 14, app.items[0]);
    }

    @Test
    void item_Backstage_passes_Quality_should_be_increases_by_3_when_there_are_less_than_5_days () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 2, 20);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(1, 23, app.items[0]);
    }
    
    @Test
    void item_Backstage_passes_when_Quality_increased_The_Quality_of_an_item_is_never_more_than_50() {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 3, 50);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(2, 50, app.items[0]);
    }

    @Test
    void item_Backstage_passes_when_the_concert_pass_Quality_should_drops_to_0 () {
        final GildedRose app = CreateGildedRoseWithItem("Backstage passes to a TAFKAL80ETC concert", 0, 49);

        app.updateQuality();
        
        assertItemWithSellInDaysAndQuality(-1, 0, app.items[0]);
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
