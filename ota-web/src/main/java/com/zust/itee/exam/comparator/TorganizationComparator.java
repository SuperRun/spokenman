package com.zust.itee.exam.comparator;

import java.util.Comparator;

import com.zust.itee.exam.entity.Torganization;

public class TorganizationComparator implements Comparator<Torganization> {

    public int compare(Torganization o1, Torganization o2) {
        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId().equals(o2.getId())) {
            return 0;
        } else {
            return -1;
        }
    }
}
