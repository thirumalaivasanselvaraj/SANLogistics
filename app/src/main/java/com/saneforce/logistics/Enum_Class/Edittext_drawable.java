package com.saneforce.logistics.Enum_Class;

public enum Edittext_drawable {

    PENNY(0), NICKLE(1), DIME(2), QUARTER(3);
    private int value;

    private Edittext_drawable(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        switch (this) {
            case PENNY:
                System.out.println("Penny: " + value);
                break;
            case NICKLE:
                System.out.println("Nickle: " + value);
                break;
            case DIME:
                System.out.println("Dime: " + value);
                break;
            case QUARTER:
                System.out.println("Quarter: " + value);
        }
        return super.toString();
    }


}
