package com.example.racunzadatak1.entity;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String itemText;
    private boolean isPrintable;
    private boolean isBold;
    private int fontSize;

    public ListItem(String itemText, boolean isPrintable, boolean isBold, int fontSize) {
        this.itemText = itemText;
        this.isPrintable = isPrintable;
        this.isBold = isBold;
        this.fontSize = fontSize;
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
