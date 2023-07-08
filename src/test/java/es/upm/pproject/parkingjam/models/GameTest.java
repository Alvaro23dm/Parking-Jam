package es.upm.pproject.parkingjam.models;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GameTest {
    Game g;
    Level level;

    boolean moveCar(Car car, char direction, int distance){
        return level.moveCar(car, direction, distance, false, false);
    }
    
    @BeforeEach
    void setUp(){
        g = new Game();
    }
    
    @Test
    void test1(){
        level = g.getLevel();
        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);
        level.moveCar(level.getRedCar(), 'D', 4, false, false);
        assertTrue(level.getRedCar().isOnGoal());
    }

    @Test
    void test2(){
        g.levelPathFormat = "src/main/resources/levels4GameTest/level_%d.txt";
        //g.path = "src/main/resources/levels4GameTest";
        g.newGame();
        assertEquals("Level 2", g.getLevelName());
    }

    @Test
    void test3(){
        String testPath = "src/main/resources/saveloadLevelTest/test3.txt";
        level = g.getLevel();

        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);
        
        int score = level.getScore();
        char[][] parking = level.getBoard().getTiles();
        
        g.saveGame(testPath);

        level.moveCar(level.getRedCar(), 'D', 3, false, false);

        g.loadGame(testPath);
        g.setScoreAndUndoMov(testPath);

        assertEquals(score, g.getLevel().getScore());
        assertArrayEquals(parking, level.getBoard().getTiles());
    }
    
    @Test
    void test4(){
        level = g.getLevel();

        char[][] parking= new char[level.getBoard().getNRows()][g.getLevel().getBoard().getNRows()];
        for(int i = 0; i< g.getLevel().getBoard().getNRows(); i++){
            System.arraycopy(g.getLevel().getBoard().getTiles()[i], 0, parking[i], 0, 8);
        }
        
        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);

        g.reset();

        assertArrayEquals(parking, g.getLevel().getBoard().getTiles());
    }

    @Test
    void test5(){
        level = g.getLevel();

        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);
        
        char[][] parking = level.getBoard().getTiles();
        
        level.moveCar(level.getRedCar(), 'D', 4, false, false);

        g.undo();

        assertArrayEquals(parking, level.getBoard().getTiles());
    }

    @Test
    void test6(){
        level = g.getLevel();

        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);
        
        char[][] parking = level.getBoard().getTiles();
        
        g.undo();
        g.redo();

        assertArrayEquals(parking, level.getBoard().getTiles());
    }

    @Test
    void test7(){
        level = g.getLevel();

        level.moveCar(level.getVehiclesMap().get('c'), 'D', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('b'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('a'), 'R', 1, false, false);
        level.moveCar(level.getVehiclesMap().get('d'), 'U', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('e'), 'U', 3, false, false);
        level.moveCar(level.getVehiclesMap().get('f'), 'L', 2, false, false);
        level.moveCar(level.getVehiclesMap().get('g'), 'L', 3, false, false);

        assertEquals(7,  g.getLevelScore());
        assertEquals(0,  g.getScore());
    }

}
