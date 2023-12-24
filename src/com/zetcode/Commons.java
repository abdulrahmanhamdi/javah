package com.zetcode;

public interface Commons {

    int WIDTH = 540;
    int HEIGHT = 600;
    int BOTTOM_EDGE = 595;

    int BALLS_NUM = 20;
    int FEATURE_WIDTH = 15;
    int INIT_PADDLE_X = 300;
    int INIT_PADDLE_Y = 560;
    int INIT_BALL_X = 230;
    int INIT_BALL_Y = 355;
    int PERIOD = 10;
    final int[][] LEVEL1 = {
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
            {2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}
    };
    final int[][] LEVEL2 = {
            {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2},
            {2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1},
            {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2},
            {2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1},
            {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2},
            {2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1},
            {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2},
            {2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1},
            {1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2},
            {2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1}
    };
    final int[][] LEVEL3 = {
            {1, 0, 0, 2, 0, 3, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 4, 0, 0, 1, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 4, 0, 0, 0},
            {4, 0, 0, 1, 2, 0, 0, 0, 0, 3, 0, 0},
            {0, 0, 3, 0, 0, 4, 0, 0, 0, 0, 1, 2},
            {0, 1, 2, 0, 0, 0, 3, 0, 0, 0, 0, 4},
            {0, 0, 0, 3, 0, 0, 0, 1, 2, 0, 4, 0},
            {2, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 3},
            {0, 3, 0, 0, 0, 1, 2, 0, 0, 4, 0, 0},
            {0, 4, 0, 1, 0, 0, 0, 3, 0, 0, 2, 0}
    };
    final int[][] LEVEL4 = {
            {1, 0, 0, 0, 4, 0, 0, 0, 2, 0, 0, 3},
            {0, 1, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0},
            {0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 2},
            {0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 3},
            {3, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0},
            {0, 3, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0},
            {0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 1},
            {1, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 2, 0, 0, 3, 0, 0, 0, 0, 1, 0, 0}
    };
    final int[][] LEVEL5 = {
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}
    };
    final int[][] LEVEL6 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0},
            {0, 1, 0, 12, 13, 14, 15, 16, 17, 0, 18, 0},
            {0, 1, 0, 19, 0, 0, 0, 0, 20, 0, 21, 0},
            {0, 1, 0, 22, 0, 0, 0, 0, 23, 0, 24, 0},
            {0, 1, 25, 26, 27, 28, 29, 30, 31, 32, 33, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    int[][] LEVEL7 = {
            {-1, -1, 5, 5, -1, -1, -1, 5, 5, -1, -1, -1},
            {-1, 5, 5, 5, 5, -1, 5, 5, 5, 5, -1, -1},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, -1},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, -1},
            {-1, 5, 5, 5, 5, 5, 5, 5, 5, 5, -1, -1},
            {-1, -1, 5, 5, 5, 5, 5, 5, 5, -1, -1, -1},
            {-1, -1, -1, 5, 5, 5, 5, 5, -1, -1, -1, -1},
            {-1, -1, -1, -1, 5, 5, 5, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
    };
     String[] BRICK_IMAGES = {
             "src/resources/brick5.png",
            "src/resources/brick.png",
            "src/resources/brick1.png",
            "src/resources/brick2.png",
            "src/resources/brick3.png",
            "src/resources/brick4.png"
    };


    int GAME_DURATION = 0;
}
