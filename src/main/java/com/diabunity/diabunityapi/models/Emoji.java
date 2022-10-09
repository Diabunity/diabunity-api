package com.diabunity.diabunityapi.models;

public class Emoji {

    private Object data;

    private String emoji;

    private String name;

    private int index;

    private boolean isSelected;

    public Emoji(Object data, String emoji, String name, int index, boolean isSelected) {
        this.data = data;
        this.emoji = emoji;
        this.name = name;
        this.index = index;
        this.isSelected = isSelected;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
