package fans.umamusume.www.common.base;

import fans.umamusume.www.common.Test;

public class Random {

    private java.util.Random random = new java.util.Random();

    private static  int count = 0;

    public int nextInt(int bound) {
        if (++count == Test.times) {
            return 34 - Test.start;
        } else
            return random.nextInt(bound);
    }

}
