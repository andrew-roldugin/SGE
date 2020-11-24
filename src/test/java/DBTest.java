import cs.vsu.ru.group6.Shapes.Pentagon;
import cs.vsu.ru.group6.Shapes.Rectangle;
import cs.vsu.ru.group6.Shapes.Star;
import cs.vsu.ru.group6.Shapes.Triangle;
import cs.vsu.ru.group6.common.DBManager;
import javafx.scene.paint.Color;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class DBTest {
    DBManager dbManager = new DBManager();

    @Test
    public void testCreate(){
        dbManager.createNewTable();
    }

    @Test
    public void testAdd(){
        dbManager.createNewTable();
        Triangle t = new Triangle(150, 200, 300,400);
        t.setId(1L);
        dbManager.add(t);
        Assert.assertTrue(dbManager.search(t).contains(t));
    }
    @Test
    public void testRemove(){
        dbManager.createNewTable();
        Triangle t = new Triangle(150, 200, 300,400);
        t.setId(1L);
        Rectangle r = new Rectangle(15, 0, 30,40);
        r.setId(2L);
        Star s = new Star(1, 1, 1, 1,  Color.RED, Color.BLUE);
        s.setId(3L);
        dbManager.add(t);
        dbManager.add(r);
        dbManager.add(s);
        dbManager.remove(r);
        Assert.assertFalse(dbManager.search(t).contains(r));
    }
    @Test
    public void testUpdate(){
        dbManager.createNewTable();
        Pentagon p = new Pentagon(0, 0, 0,0);
        p.setId(1L);
        dbManager.add(p);
        Assert.assertTrue(dbManager.search(p).contains(p));
        Pentagon pNew = new Pentagon(1000, 80, 706,90, Color.GRAY, Color.GOLD);
        pNew.setId(1L);
        dbManager.update(pNew);
        Assert.assertFalse(dbManager.search(p).contains(p));
        Assert.assertTrue(dbManager.search(pNew).contains(pNew));
    }
}
