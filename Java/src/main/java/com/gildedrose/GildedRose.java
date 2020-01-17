package com.gildedrose;

class GildedRose {
    private static final int MINIMUM_QUALITY = 0;
    private static final int MAXIMUM_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    
    public void updateQuality() {
        for (Item item : items) {
            
            if (isSulfuras(item)) {

            } else if (isAgedBrie(item)) {
                
                item.sellIn--;
                if (isPassedSellByDate(item)) {
                    addQualityWith(item, 2);
                } else {
                    addQualityWith(item, 1);
                }
                
            } else if (isBackstagePasses(item)) {
                    
                item.sellIn--;
                if (isPassedSellByDate(item)) {
                    item.quality = 0;
                } else if (item.sellIn <= 5) {
                    addQualityWith(item, 3);
                } else if (item.sellIn <= 10) {
                    addQualityWith(item, 2);
                } else {
                    addQualityWith(item, 1);
                }
                
            } else {
                item.sellIn--;
                if (isPassedSellByDate(item)) {
                    addQualityWith(item, -2);
                } else { 
                    addQualityWith(item, -1);
                }
            }            
        }
    }

    private void addQualityWith(Item item, int incrementVal) {
        item.quality = item.quality + incrementVal;
        if (item.quality > MAXIMUM_QUALITY) {
            item.quality = MAXIMUM_QUALITY;
        } else if (item.quality < MINIMUM_QUALITY) {
            item.quality = MINIMUM_QUALITY;
        }
    }
        
    private boolean isPassedSellByDate(Item item) {
        return item.sellIn < 0;
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