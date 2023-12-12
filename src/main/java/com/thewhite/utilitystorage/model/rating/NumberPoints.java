package com.thewhite.utilitystorage.model.rating;

public enum NumberPoints {
    ONE,
    TWO,
    THREE,
    FOUR,
    EXCELLENTLY;

//    ONE(1),
//    TWO(1),
//    THREE(2),
//    FOUR(3),
//    EXCELLENTLY(4);
//
//    private int value;
//
//    NumberPoints(int value) {
//        this.value = value;
//    }
//    NumberPoints(String str) {
//        this.value = Integer.parseInt(str);
//    }
//
//    public static NumberPoints fromString(String value) {
//        for (NumberPoints point : NumberPoints.values()) {
//            if (point.value.equalsIgnoreCase(value)) {
//                return point;
//            }
//        }
//        throw new IllegalArgumentException("No constant with value " + value + " found");
//    }
}
