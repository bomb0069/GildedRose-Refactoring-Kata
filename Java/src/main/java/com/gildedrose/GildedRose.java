package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                if (isQualityLessThanMaximum(item))
                    item.quality = item.quality + 1;
            } else if (isBackstagePasses(item)) {
                if (isQualityLessThanMaximum(item)) {
                    item.quality = item.quality + 1;

                    if (item.sellIn < 11) {
                        if (isQualityLessThanMaximum(item)) {
                            item.quality = item.quality + 1;
                        }
                    }
                    
                    if (item.sellIn < 6) {
                        if (isQualityLessThanMaximum(item)) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            } else {
                if (item.quality > 0) {
                    if (!isSulfuras(item)) {
                        item.quality = item.quality - 1;
                    }
                }
            }
            
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }
            
            if (isAgedBrie(item)) {
                if (isPassedSellByDate(item))
                    if (isQualityLessThanMaximum(item)) {
                        item.quality = item.quality + 1;
                    }
            } else if (isBackstagePasses(item)) {
                if (isPassedSellByDate(item))
                    item.quality = item.quality - item.quality;
            } else {
                if (isPassedSellByDate(item))
                    if (item.quality > 0) {
                        if (!isSulfuras(item)) {
                            item.quality = item.quality - 1;
                        }
                    }
            }
        }
    }

    private boolean isPassedSellByDate(Item item) {
        return item.sellIn < 0;
    }

    private boolean isQualityLessThanMaximum(Item item) {
        return item.quality < 50;
    }

    private boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }

    private boolean isBackstagePasses(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.name);
    }
}