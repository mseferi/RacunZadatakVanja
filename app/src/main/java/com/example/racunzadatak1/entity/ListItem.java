package com.example.racunzadatak1.entity;

import java.io.Serializable;
import java.util.Objects;

public class ListItem implements Serializable {
    private String itemText;
    private boolean isPrintable;
    private boolean isBold;
    private int fontSize;
    private int position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItem item = (ListItem) o;
        return isPrintable == item.isPrintable &&
                isBold == item.isBold &&
                fontSize == item.fontSize &&
                position == item.position &&
                itemText.equals(item.itemText);
    }

    @Override
    public int hashCode() {
        return itemText.hashCode();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private static int positionDelegate;

    public ListItem(String itemText, boolean isPrintable, boolean isBold, int fontSize) {
        this.itemText = itemText;
        this.isPrintable = isPrintable;
        this.isBold = isBold;
        this.fontSize = fontSize;
        position = positionDelegate;
        positionDelegate++;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }


}
